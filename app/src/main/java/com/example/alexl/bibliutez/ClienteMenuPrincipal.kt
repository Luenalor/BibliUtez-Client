package com.example.alexl.bibliutez

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import com.example.alexl.bibliutez.model.carritos.CarritosBean
import com.example.alexl.bibliutez.model.libros.LibrosBean
import com.example.alexl.bibliutez.model.libros.LibrosJsonPlaceHolder
import kotlinx.android.synthetic.main.cliente_menu_principal.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ClienteMenuPrincipal : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.cliente_menu_principal)
        val URL = "http://192.168.1.176:8080/BibliUtez_war/"
        //Traer object
      /*  val bundle = intent.extras
        val user:CarritosBean = bundle.getSerializable("user") as CarritosBean

        //
        cliente_menu_bienvenido.setText("Bienvenido "+user.usuarios.nombre!! +user.usuarios.apellido1!!)*/


        btnClientePerfil.setOnClickListener{
            var clientePerfil = Intent(this, ClientePerfil::class.java)
            startActivity(clientePerfil)
        }

        btnCarritoCompras.setOnClickListener{
            var CarritoCompras = Intent(this, CarritoCompras::class.java)
            startActivity(CarritoCompras)
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
       // val mycall: Call<List<LibrosBean>> = jsonPlaceHolderApi.librosFindAll()
        val mycall: Call<ArrayList<LibrosBean>> = jsonPlaceHolderApi.librosFindAll()

        mycall.enqueue(object : Callback<ArrayList<LibrosBean>> {
            override fun onFailure(call: Call<ArrayList<LibrosBean>>, t: Throwable) {
                Log.e("onFailure!!!!!",t.message.toString())
            }

            override fun onResponse(call: Call<ArrayList<LibrosBean>>, response: Response<ArrayList<LibrosBean>>) {
                listaLibros = response.body()!!
                cl_rcvLibros.layoutManager = manager
                llenar(listaLibros, cl_rcvLibros)
            }

        })

    }
    fun llenar(listaLibros:List<LibrosBean>, recyclerView: RecyclerView){
        var adapter = AdapterLibroCliente(this, listaLibros)
        cl_rcvLibros.adapter = adapter
        adapter.notifyDataSetChanged()

    }
}