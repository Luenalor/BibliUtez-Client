package com.example.alexl.bibliutez

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.AdapterView
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import com.example.alexl.bibliutez.model.categorias.CategoriasBean
import com.example.alexl.bibliutez.model.categorias.CategoriasJsonPlaceHolder
import com.example.alexl.bibliutez.model.clientes.ClienteJsonPlaceHolder
import com.example.alexl.bibliutez.model.clientes.ClientesBean
import com.example.alexl.bibliutez.model.roles.RolesBean
import com.example.alexl.bibliutez.model.roles.RolesJsonPlaceHolder
import com.example.alexl.bibliutez.model.usuarios.UsuariosBean
import com.example.alexl.bibliutez.model.usuarios.UsuariosJsonPlaceHolder
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.cliente_perfil.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RegistroCliente : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.registro_cliente)
        val URL = "http://192.168.1.176:8080/BibliUtez_war/"

        var sp_option: String = "M"
        var sp_sexo = findViewById<Spinner>(R.id.sp_sexo)
        var id: Int = 0
        var rol: RolesBean = RolesBean(0, "")
        var nombre = ""
        var apellido1 = ""
        var apellido2 = ""
        var fecha_nacimiento = ""
        var email = ""
        var telefono = ""
        var domicilio = ""
        var password = ""
        var usuariosBean: UsuariosBean = UsuariosBean(0,  nombre, apellido1, apellido2, email, 1, sp_option, rol, password);

        sp_sexo.onItemSelectedListener =
                object : AdapterView.OnItemClickListener, AdapterView.OnItemSelectedListener {

                    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                        when (position) {
                            0 -> sp_option = "M"
                            1 -> sp_option = "F"
                            2 -> sp_option = "ND"
                        }
                    }

                    override fun onNothingSelected(parent: AdapterView<*>?) {
                        TODO("Not yet implemented")
                    }

                    override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                        TODO("Not yet implemented")
                    }
                }

        fun llenar(idUser: Int) {
            usuariosBean.id = idUser

            //Cliente Object


        }

        fun llenarRol(rolesBean: RolesBean) {
            rol = rolesBean
        }

        btnRegistroCliente.setOnClickListener {
            try {
                nombre = findViewById<EditText>(R.id.txtRegistroClienteNombre).text.toString()
                apellido1 = findViewById<EditText>(R.id.txtRegistroClienteApellido1).text.toString()
                apellido2 = findViewById<EditText>(R.id.txtRegistroClienteApellido2).text.toString()
                fecha_nacimiento = findViewById<EditText>(R.id.txtRegistroClienteNacimiento).text.toString()
                email = findViewById<EditText>(R.id.txtRegistroClienteEmail).text.toString()
                telefono = findViewById<EditText>(R.id.txtRegistroClienteCelular).text.toString()
                domicilio = findViewById<EditText>(R.id.txtRegistroClienteDomicilio).text.toString()
                password = findViewById<EditText>(R.id.txtRegistroClientePasseword).text.toString()


                //Retrofit builder
                val rolesFit = Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(URL + "roles/")
                    .build()


                //object to call methods
                val rolesJson = rolesFit.create(RolesJsonPlaceHolder::class.java)
                val mycall: Call<RolesBean> = rolesJson.rolesFindOne(2)
                mycall.enqueue(object : Callback<RolesBean> {
                    override fun onFailure(call: Call<RolesBean>, t: Throwable) {
                    }

                    override fun onResponse(call: Call<RolesBean>, response: Response<RolesBean>) {
                        llenarRol(response.body()!!)
                    }
                })





                //Llenar objetos
                var usuariosBean = UsuariosBean(
                    131,
                    nombre,
                    apellido1,
                    apellido2,
                    email,
                    1,
                    sp_option,
                    rol,
                    password
                )

                //Retrofit builder
                val clientesRetrofit = Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(URL + "usuarios/")
                    .build()


                //object to call methods
                val clientesJson = clientesRetrofit.create(UsuariosJsonPlaceHolder::class.java)
                val call: Call<Int> = clientesJson.usuariosAdd(usuariosBean)
                call.enqueue(object : Callback<Int> {
                    override fun onFailure(call: Call<Int>, t: Throwable) {

                    }

                    override fun onResponse(call: Call<Int>, response: Response<Int>) {
                        llenar(response.body()!!)
                        usuariosBean.id = response.body()!!
                    }
                })

                usuariosBean.id = id


                //retrofit
                val retrofit2 = Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(URL + "clientes/")
                    .build()

                //object to call methods
                var cliente = ClientesBean(
                    0,
                    fecha_nacimiento,
                    telefono,
                    usuariosBean,
                    domicilio
                )
                val jsonPlaceHolderApi2 = retrofit2.create(ClienteJsonPlaceHolder::class.java)
                val call2: Call<Int> = jsonPlaceHolderApi2.clienteAdd(cliente)
                call2.enqueue(object : Callback<Int> {
                    override fun onFailure(call: Call<Int>, t: Throwable) {
                    }

                    override fun onResponse(call: Call<Int>, response: Response<Int>) {
                        Toast.makeText(
                            this@RegistroCliente,
                            "¡Cliente registrado con éxito!", Toast.LENGTH_LONG
                        ).show()
                    }
                })
            } catch (e: Exception) {
                Toast.makeText(
                    this@RegistroCliente,
                    "Estamos teniendo problemas. Vuelve más tarde", Toast.LENGTH_LONG
                ).show()
            }


            var menuPrincipal = Intent(this, MainActivity::class.java)
            startActivity(menuPrincipal)
        }

    }

}

