package com.example.alexl.bibliutez

import android.content.Context
import android.content.SharedPreferences
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.alexl.bibliutez.model.carritos.CarritosBean
import com.example.alexl.bibliutez.model.carritos.CarritosJsonPlaceHolder
import com.example.alexl.bibliutez.model.clientes.ClienteJsonPlaceHolder
import com.example.alexl.bibliutez.model.clientes.ClientesBean
import com.example.alexl.bibliutez.model.roles.RolesBean
import com.example.alexl.bibliutez.model.usuarios.UsuariosBean
import kotlinx.android.synthetic.main.cliente_menu_principal.*
import kotlinx.android.synthetic.main.cliente_perfil.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.Exception
import java.util.*


class ClientePerfil : AppCompatActivity() {


    lateinit var sharedPreferences: SharedPreferences


    var rol : RolesBean = RolesBean(0, "")
    var usuarios: UsuariosBean = UsuariosBean(0, "", "", "", "", 0, "", rol, "")
    var cliente: ClientesBean = ClientesBean(0, "", "", usuarios, "")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.cliente_perfil)

        sharedPreferences = getSharedPreferences("SHARED_PREF", Context.MODE_PRIVATE)

        val URL = "http://192.168.1.176:8080/BibliUtez_war/"



        val nombre:String = sharedPreferences.getString("nombre", "No Aplica.");
        val apellido1:String = sharedPreferences.getString("apellido1", "No Aplica.");
        val apellido2:String = sharedPreferences.getString("apellido2", "No Aplica.");
        val emailDos:String = sharedPreferences.getString("emailDos", "No Aplica.");
        val idUsuario:Int = sharedPreferences.getInt("idUsuario", 0);
        var clienteFechaNacimiento: String = ""
        var clienteDomicilio: String = ""
        var clienteCelular: String = ""

        nombrePerfil.setText(nombre + " " + apellido1 + " " + apellido2 )
        emailPerfil.setText(emailDos)




        val retrofit = Retrofit.Builder()
            //Armar la URL
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(URL + "clientes/")
            //Cargamos y creamos nuestro objeto retrofit
            .build()

        val jsonPlaceHolderApi = retrofit.create(ClienteJsonPlaceHolder::class.java)
        val mycall: Call<ClientesBean> = jsonPlaceHolderApi.clienteFindCliente(idUsuario)

        mycall.enqueue(object : Callback <ClientesBean>{
            override fun onResponse(call: Call<ClientesBean>, response: Response<ClientesBean>) {
                try {
                    fillCliente(response.body()!!)
                    clienteFechaNacimiento = response.body()!!.fecha_nacimiento
                    clienteDomicilio = response.body()!!.domicilio
                    clienteCelular = response.body()!!.telefono

                    fechaNacimientoPerfil.setText(clienteFechaNacimiento)
                    celularPerfil.setText(clienteCelular)
                    domicilioPerfil.setText(clienteDomicilio)
                }catch (e: Exception){
                    fechaNacimientoPerfil.setText("No Aplica.")
                    celularPerfil.setText("No Aplica.")
                    domicilioPerfil.setText("No Aplica.")
                }


            }

            override fun onFailure(call: Call<ClientesBean>, t: Throwable) {

            }
        })
    }

    fun fillCliente(clienteFill: ClientesBean){
        cliente = clienteFill;
    }

}