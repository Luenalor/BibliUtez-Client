package com.example.alexl.bibliutez

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.registro_libros.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnIniciarSesion.setOnClickListener{

           // if usuariorole = 1
            var login = Intent(this, GerenteMenuPrincipal::class.java)
            startActivity(login)
           /*else
            var login = Intent(this, GerenteMenuPrincipal::class.java)
            startActivity(login)*/
        }

        btnRegistroCliente.setOnClickListener{
            var registroCliente = Intent(this, RegistroCliente::class.java)
            startActivity(registroCliente)
        }

    }
}
