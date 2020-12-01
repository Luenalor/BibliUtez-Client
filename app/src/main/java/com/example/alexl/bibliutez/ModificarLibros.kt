package com.example.alexl.bibliutez

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.alexl.bibliutez.model.libros.LibrosBean
import kotlinx.android.synthetic.main.modificar_libros.*
import kotlinx.android.synthetic.main.modificar_libros.spnLibroCategoria
import kotlinx.android.synthetic.main.registro_libros.*
import org.json.JSONObject

class ModificarLibros : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.modificar_libros)
        /*
        var libro:LibrosBean = intent.getSerializableExtra("libroModificacion") as LibrosBean

        txtModificarLibroTitulo.setText("${libro.nombre}")
        txtModificarLibroEditorial.setText("${libro.editorial}")
        txtModificarLibroPrecio.setText("${libro.precio}")
        txtModificarLibroStock.setText("${libro.stock}")
        txtModificarLibroNumPag.setText("${libro.num_pag}")

        btnModificarLibro.setOnClickListener {
            val peticiones = Volley.newRequestQueue(this)
            var url:String = "loyaUrl"


            var libroJson = JSONObject()
            libroJson.put("id", libro.id)
            libroJson.put("nombre", txtModificarLibroTitulo.text.toString())
            libroJson.put("editorial", txtModificarLibroEditorial.text.toString())
            libroJson.put("nombre", spnLibroCategoria.toString())
            libroJson.put("precio", txtModificarLibroPrecio.text.toString().toDouble())
            libroJson.put("stock", txtModificarLibroStock.text.toString().toInt())
            libroJson.put("num_pag", txtModificarLibroNumPag.text.toString().toInt())

            var peticion = JsonObjectRequest(
                Request.Method.POST,url,libroJson,
                Response.Listener { response ->
                    if (response.getBoolean("nombreVarLoya")){
                        Toast.makeText(this, "Todo Bien", Toast.LENGTH_LONG).show()
                    }else{
                        Toast.makeText(this, "No sé modifico", Toast.LENGTH_LONG).show()
                    }
                },
                Response.ErrorListener { error ->
                    Toast.makeText(this, "Error -> $error", Toast.LENGTH_LONG).show()

                }

            )
            peticiones.add(peticion)
        }

        btnConsultarLibros.setOnClickListener {
            var consultar = Intent(this, ListaLibrosActivity::class.java)
            startActivity(consultar)
        }

        */
    }
}