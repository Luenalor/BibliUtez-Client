package com.example.alexl.bibliutez

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import com.example.alexl.bibliutez.model.carritos.CarritosBean
import com.example.alexl.bibliutez.model.carritos.CarritosJsonPlaceHolder
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {



    lateinit var sharePreferences: SharedPreferences
    var recordar = false

    val b : Bundle = Bundle()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sharePreferences = getSharedPreferences("SHARED_PREF", Context.MODE_PRIVATE)
        recordar = sharePreferences.getBoolean("recordar", false)

        var idRol = sharePreferences.getInt("idRol", 0)
        if (recordar){
            when(idRol){
                1 -> {
                    var intent = Intent(this, GerenteMenuPrincipal::class.java)

                    startActivity(intent)
                    finish()
                }
                2 -> {
                    var intent = Intent(this, ClienteMenuPrincipal::class.java)




                    startActivity(intent)
                    finish()
                }
                else -> {
                    Toast.makeText(
                        this@MainActivity,
                        "Error en el inicio de sesión", Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
        val URL = "http://192.168.1.176:8080/BibliUtez_war/"

        btnIniciarSesion.setOnClickListener {

            var checkBox = findViewById<CheckBox>(R.id.checkRecordarDatos).isChecked

            try {

                val email = findViewById<EditText>(R.id.correoElectronico).text.toString()
                val password = findViewById<EditText>(R.id.password).text.toString()

                //Retrofit builder
                val retrofit = Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(URL + "carritos/")
                    .build()

                //object to call methods
                val jsonPlaceHolderApi = retrofit.create(CarritosJsonPlaceHolder::class.java)
                val mycall: Call<CarritosBean> = jsonPlaceHolderApi.usuarioscheck(email, password)

                mycall.enqueue(object : Callback<CarritosBean> {
                    override fun onFailure(call: Call<CarritosBean>, t: Throwable) {
                        Toast.makeText(
                            this@MainActivity,
                            "!!LLEGA THO", Toast.LENGTH_LONG
                        ).show()
                    }

                    override fun onResponse(
                        call: Call<CarritosBean>,
                        response: Response<CarritosBean>
                    ) {

                        var idCarrito = response.body()!!.id
                        var idUsuario = response.body()!!.usuarios?.id
                        var nombre = response.body()!!.usuarios?.nombre
                        var apellido1 = response.body()!!.usuarios?.apellido1
                        var apellido2 = response.body()!!.usuarios?.apellido2
                        var emailDos = response.body()!!.usuarios?.email
                        var estatus = response.body()!!.usuarios?.estatus
                        var sexo = response.body()!!.usuarios?.sexo
                        var idRol = response.body()!!.usuarios?.rol?.id


                        b.putString("nombre", nombre)
                        intent.putExtras(b)
                        startActivity(intent)

                        Log.e("nombre", nombre)
                        Log.e("putExtras", b.getString("nombre"))



                        checkAccess(
                            idCarrito,
                            idUsuario,
                            nombre,
                            apellido1,
                            apellido2,
                            emailDos,
                            estatus,
                            sexo,
                            idRol,
                            email,
                            password,
                            checkBox
                        )

                        //checkAccess(response.body()!!)
                    }
                })
            } catch (e: Exception) {
                Toast.makeText(
                    this@MainActivity,
                    "Ingresa los campos ${e}", Toast.LENGTH_LONG
                ).show()

            }
        }



        btnRegistroCliente.setOnClickListener {

            var registroCliente = Intent(this, RegistroCliente::class.java)
            startActivity(registroCliente)
        }
    }

    fun checkAccess(
        idCarrito: Int,
        idUsuario: Int,
        nombre: String?,
        apellido1: String?,
        apellido2:
        String,
        emailDos: String,
        estatus: Int,
        sexo: String,
        idRol: Int,
        email: String,
        password: String,
        recordar: Boolean
    ) {


        if (email == emailDos){






            when(idRol){
                1 -> {
                    var editor: SharedPreferences.Editor = sharePreferences.edit()
                    editor.putBoolean("recordar", recordar)
                    editor.putInt("idCarrito", idCarrito)
                    editor.putInt("idUsuario", idUsuario)
                    editor.putInt("idRol", idRol)
                    editor.putString("nombre", nombre)
                    editor.putString("apellido1", apellido1)
                    editor.putString("apellido2", apellido2)
                    editor.putString("emailDos", emailDos)
                    editor.putInt("estatus", estatus)
                    editor.putString("sexo", sexo)



                    editor.apply()
                    var gerente = Intent(this, GerenteMenuPrincipal::class.java)
                    startActivity(gerente)
                    finish()
                }
                2 -> {

                    var editor: SharedPreferences.Editor = sharePreferences.edit()
                    editor.putBoolean("recordar", recordar)
                    editor.putInt("idCarrito", idCarrito)
                    editor.putInt("idUsuario", idUsuario)
                    editor.putInt("idRol", idRol)
                    editor.putString("nombre", nombre)
                    editor.putString("apellido1", apellido1)
                    editor.putString("apellido2", apellido2)
                    editor.putString("emailDos", emailDos)
                    editor.putInt("estatus", estatus)
                    editor.putString("sexo", sexo)




                    editor.apply()
                    var cliente = Intent(this, ClienteMenuPrincipal::class.java)
                    startActivity(cliente)
                    finish()
                }
                else -> {
                    Toast.makeText(
                        this@MainActivity,
                        "Error en el inicio de sesión", Toast.LENGTH_LONG
                    ).show()
                }
            }
        }else{

        }


       /* try {
            val b: Bundle = Bundle();
            if (access.usuarios.rol.id == 1) {
                var login = Intent(this, GerenteMenuPrincipal::class.java)
                b.putSerializable("user", access)
                startActivity(login)
            } else if (access.usuarios.rol.id == 2) {
                var login = Intent(this, ClienteMenuPrincipal::class.java)
                b.putSerializable("user", access)
                startActivity(login)
            } else {
                Toast.makeText(
                    this@MainActivity,
                    "rol"+access.usuarios.rol.id, Toast.LENGTH_LONG
                ).show()
            }
        } catch (e: Exception) {
            Toast.makeText(
                this@MainActivity,
                ""+e, Toast.LENGTH_LONG
            ).show()
        }*/
    }




}

