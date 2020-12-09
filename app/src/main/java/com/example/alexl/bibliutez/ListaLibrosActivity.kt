package com.example.alexl.bibliutez

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import com.example.alexl.bibliutez.model.libros.LibrosJsonPlaceHolder
import com.example.alexl.bibliutez.model.libros.LibrosBean
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
        val URL = "http://192.168.1.176:8080/BibliUtez_war/"
        btnClientePerfil.setOnClickListener{
            var clientePerfil = Intent(this, ClientePerfil::class.java)
            startActivity(clientePerfil)
        }

        btnHomeGerente.setOnClickListener{
            var home = Intent(this,GerenteMenuPrincipal::class.java )
            startActivity(home)
        }

        //lista de libros
        var listaLibros: List<LibrosBean> = arrayListOf<LibrosBean>()
        var manager = LinearLayoutManager(this)



        //Retrofit builder
        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(URL+"libros/")
            .build()

        //object to call methods
        val jsonPlaceHolderApi = retrofit.create(LibrosJsonPlaceHolder::class.java)
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





    }
    fun llenar(listaLibros:List<LibrosBean>, recyclerView: RecyclerView){
        var adapter = AdapterLibro(this, listaLibros)
        rcvLibros.adapter = adapter
        adapter.notifyDataSetChanged()

    }
}