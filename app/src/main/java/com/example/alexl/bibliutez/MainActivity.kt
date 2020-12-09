package com.example.alexl.bibliutez

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.Toast
import com.example.alexl.bibliutez.model.carritos_libros.CarritosLibrosJsonPlaceHolder
import com.example.alexl.bibliutez.model.libros.LibrosBean
import com.example.alexl.bibliutez.model.libros.LibrosJsonPlaceHolder
import com.example.alexl.bibliutez.model.usuarios.UsuariosJsonPlaceHolder
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val URL = "http://192.168.0.8:8080/BibliUtez_war/"

        btnIniciarSesion.setOnClickListener {
            var login = Intent(this, GerenteMenuPrincipal::class.java)
            startActivity(login)
            try {


                val email = findViewById<EditText>(R.id.correoElectronico).text.toString()
                val password = findViewById<EditText>(R.id.password).text.toString()

                //Retrofit builder
                val retrofit = Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(URL + "carritos/")
                    .build()

                //object to call methods
                val jsonPlaceHolderApi = retrofit.create(CarritosLibrosJsonPlaceHolder::class.java)
                val mycall: Call<Boolean> = jsonPlaceHolderApi.usuarioscheck(email, password)

                mycall.enqueue(object : Callback<Boolean> {
                    override fun onFailure(call: Call<Boolean>, t: Throwable) {
                        Toast.makeText(
                            this@MainActivity,
                            "!!!${t}", Toast.LENGTH_LONG
                        ).show()
                    }

                    override fun onResponse(call: Call<Boolean>, response: Response<Boolean>) {
                        checkAccess(response.body()!!)
                    }


                })
            } catch (e: Exception) {
                Toast.makeText(
                    this@MainActivity,
                    "Ingresa los campos ${e}", Toast.LENGTH_LONG
                ).show()

            }

            btnRegistroCliente.setOnClickListener {

                var registroCliente = Intent(this, RegistroCliente::class.java)
                startActivity(registroCliente)
            }

        }
    }
    fun checkAccess(access: Boolean) {
        if (access) {
            var login = Intent(this, GerenteMenuPrincipal::class.java)
            startActivity(login)
        } else {
            Toast.makeText(
                this@MainActivity,
                "Error al intentar ingresar", Toast.LENGTH_LONG
            ).show()
        }
    }

}
