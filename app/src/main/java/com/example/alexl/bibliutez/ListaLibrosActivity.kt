package com.example.alexl.bibliutez

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import com.example.alexl.bibliutez.model.categorias.CategoriasBean
import com.example.alexl.bibliutez.model.libros.JsonPlaceHolderApi
import com.example.alexl.bibliutez.model.libros.LibrosBean
import kotlinx.android.synthetic.main.gerente_gestion_libros.*
import kotlinx.android.synthetic.main.gerente_gestion_libros.btnClientePerfil
import kotlinx.android.synthetic.main.gerente_lista_libros.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class ListaLibrosActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.gerente_lista_libros)

        btnClientePerfil.setOnClickListener{
            var clientePerfil = Intent(this, ClientePerfil::class.java)
            startActivity(clientePerfil)
        }


        //lista de libros
        var listaLibros: List<LibrosBean> = arrayListOf<LibrosBean>()
        var manager = LinearLayoutManager(this)



        //Retrofit builder
        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://eee72448d1a5.ngrok.io/BibliUtez_war/libros/")
            .build()

        //object to call methods
        val jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi::class.java)
        val mycall: Call<List<LibrosBean>> = jsonPlaceHolderApi.librosFindAll()

        mycall.enqueue(object : Callback<List<LibrosBean>> {
            override fun onFailure(call: Call<List<LibrosBean>>, t: Throwable) {
                Log.e("onFailure!!!!!",t.message.toString())
            }

            override fun onResponse(call: Call<List<LibrosBean>>, response: Response<List<LibrosBean>>) {
                listaLibros = response.body()!!
                rcvLibros.layoutManager = manager
                llenar(listaLibros, rcvLibros)

            }

        })


        /*var peticiones = Volley.newRequestQueue(this)
        var url: String = "urlLoya"
        var peticion = JsonObjectRequest(Request.Method.GET, url, null,
            Response.Listener { listaLibrosJson ->
                listaLibros = arrayListOf(LibrosBean())
                var json: JSONArray =
                    listaLibrosJson.getJSONArray("//checar array o objet + nombre: 31:39")
                for (index in 0 until json.length()) {

                    var objetoLibroJSON: JSONObject = json.getJSONObject(index)
                    var libros: LibrosBean = LibrosBean()
                    libros.id = objetoLibroJSON.getInt("id")
                    libros.nombre = objetoLibroJSON.getString("nombre")
                    libros.autores = objetoLibroJSON.getString("autores")
                    libros.editorial = objetoLibroJSON.getString("editoriales")
                    libros.num_pag = objetoLibroJSON.getInt("num_pag")
                    libros.precio = objetoLibroJSON.getDouble("precio")
                    libros.stock = objetoLibroJSON.getInt("stock")
                    libros.nombre = CategoriasBean(id = 0, nombre = "")
                    listaLibros.add(libros)

                    var manager = LinearLayoutManager(this)
                    var adapterLibros = AdapterLibro(this, listaLibros)
                    rcvLibros.layoutManager = manager
                    rcvLibros.adapter = adapterLibros

                    adapterLibros!!.notifyDataSetChanged()
                }
            },
            Response.ErrorListener { errorVolley ->
                Toast.makeText(this, "Error -> $errorVolley", Toast.LENGTH_LONG).show()

            }
        )
        peticiones.add(peticion)*/

        /*listaLibros.add(
            LibrosBean(
                R.drawable.libro1,
                "nombreLibro",
                "editorialLibro",
                "autorLibro",
                CategoriasBean(1, "Renacuo"),
                15.00,
                105,
                5
            )
        )
        listaLibros.add(
            LibrosBean(
                R.drawable.libro1,
                "nombreDos",
                "editorialDos",
                "autorDos",
                CategoriasBean(2, "Nigga"),
                20.00,
                195,
                4
            )
        )
        listaLibros.add(
            LibrosBean(
                R.drawable.libro1,
                "nombreTres",
                "editorialTres",
                "autorTres",
                CategoriasBean(1, "Renacuo"),
                52.00,
                425,
                14
            )
        )


        var manager = LinearLayoutManager(this)
        var adapterLibros = AdapterLibro(this, listaLibros)
        rcvLibros.layoutManager = manager
        rcvLibros.adapter = adapterLibros

        adapterLibros!!.notifyDataSetChanged()*/


    }
    fun llenar(listaLibros:List<LibrosBean>, recyclerView: RecyclerView){
        var adapter = AdapterLibro(this, listaLibros)
        rcvLibros.adapter = adapter

    }
}