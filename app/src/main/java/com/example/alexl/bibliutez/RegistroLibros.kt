package com.example.alexl.bibliutez

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.gerente_gestion_libros.*
import kotlinx.android.synthetic.main.registro_libros.*
import org.json.JSONObject

class RegistroLibros : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.registro_libros)

        btnRegistroConsultarLibros.setOnClickListener{
            var consulta = Intent(this, ListaLibrosActivity::class.java)
            startActivity(consulta)
        }

        /*btnRegistroLibro.setOnClickListener{
            val peticiones = Volley.newRequestQueue(this)
            var url:String = "loyaUrl"


            var libroJson = JSONObject()
            libroJson.put("nombre", txtRegistroLibroTitulo.text.toString())
            libroJson.put("editorial", txtRegistroLibroEditorial.text.toString())
            libroJson.put("categoria", spnLibroCategoria.toString())
            libroJson.put("precio", txtRegistroLibroPrecio.text.toString().toDouble())
            libroJson.put("stock", txtRegistroLibroStock.text.toString().toInt())
            libroJson.put("num_pag", txtRegistroLibroNumPag.text.toString().toInt())

            var peticion = JsonObjectRequest(Request.Method.POST,url,libroJson,
                Response.Listener { response ->
                    if (response.getBoolean("nombreVarLoya")){
                        Toast.makeText(this, "Todo Bien", Toast.LENGTH_LONG).show()
                    }else{
                        Toast.makeText(this, "Todo MAL", Toast.LENGTH_LONG).show()
                    }
                },
                Response.ErrorListener {error ->
                    Toast.makeText(this, "Error -> $error", Toast.LENGTH_LONG).show()

                }

            )
            peticiones.add(peticion)*/
        }
    }
