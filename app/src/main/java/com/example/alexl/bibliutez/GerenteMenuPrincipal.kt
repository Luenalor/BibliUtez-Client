package com.example.alexl.bibliutez

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.gerente_menu_principal.*

class GerenteMenuPrincipal : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.gerente_menu_principal)

        btnRegistrarLibro.setOnClickListener{
            var registrolibros = Intent(this, RegistroLibros::class.java)
            startActivity(registrolibros)
        }

        btnGestionarLibros.setOnClickListener{
            var verLibros = Intent(this, ListaLibrosActivity::class.java)
            startActivity(verLibros)
        }

        btnClientePerfil.setOnClickListener{
            var verPerfil = Intent(this, ClientePerfil::class.java)
            startActivity(verPerfil)
        }
    }
}