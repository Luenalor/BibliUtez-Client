package com.example.alexl.bibliutez

import android.support.v7.app.AppCompatActivity
import android.os.Bundle

class ClienteMenuPrincipal : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.cliente_menu_principal)
        /*var listaLibros: ArrayList<LibrosBean> = arrayListOf<LibrosBean>()


        btnConsultarLibrosTest.setOnClickListener {
            var peticiones = Volley.newRequestQueue(this)
            var url: String = "http://b3b364169b9f.ngrok.io/BibliUtez_war/libros/findAll"
            var peticion = JsonObjectRequest(Request.Method.GET, url, null,
                { listaLibrosJson ->
                    var json:JSONArray = listaLibrosJson.getJSONArray("libros")
                    for (index in 0 until json.length()) {
                        var objetoLibroJSON:JSONObject = json.getJSONObject(index)
                        var libro: LibrosBean = LibrosBean()
                        libro.nombre = objetoLibroJSON.getString("nombre")
                        libro.autores = objetoLibroJSON.getString("autores_id")
                        libro.stock = objetoLibroJSON.getInt("stock")
                        libro.num_pag = objetoLibroJSON.getInt("num_pag")
                        libro.nombre.nombre = objetoLibroJSON.getString("categorias_id")
                        libro.nombre.id = objetoLibroJSON.getInt("id")
                        libro.editorial = objetoLibroJSON.getString("editoriales_id")
                        libro.precio = objetoLibroJSON.getDouble("precio")


                        listaLibros.add(libro)

                        var manager = LinearLayout(this)
                        var adapterLibros = AdapterLibro(this,listaLibros)


                    }

                },
                { error: VolleyError? ->
                    Toast.makeText(this, "Error $error", Toast.LENGTH_LONG).show()
                }
            )
        }*/
    }
}