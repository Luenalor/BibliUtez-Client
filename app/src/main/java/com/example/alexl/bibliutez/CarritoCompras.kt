package com.example.alexl.bibliutez

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import com.example.alexl.bibliutez.model.carritos_libros.CarritosLibrosBean
import com.example.alexl.bibliutez.model.carritos_libros.CarritosLibrosJsonPlaceHolder
import com.example.alexl.bibliutez.model.libros.LibrosBean
import com.example.alexl.bibliutez.model.libros.LibrosJsonPlaceHolder
import kotlinx.android.synthetic.main.carrito_compras.*
import kotlinx.android.synthetic.main.cliente_menu_principal.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CarritoCompras : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.carrito_compras)
        val URL = "http://192.168.0.8:8080/BibliUtez_war/"



        //lista de libros
        var listaLibros: List<CarritosLibrosBean> = arrayListOf<CarritosLibrosBean>()
        var manager = LinearLayoutManager(this)


        //Retrofit builder
        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(URL+"carritos_libros/")
            .build()

        //object to call methods
        val jsonPlaceHolderApi = retrofit.create(CarritosLibrosJsonPlaceHolder::class.java)
        val mycall: Call<List<CarritosLibrosBean>> = jsonPlaceHolderApi.carritosLibrosFindCarrito(1)

        mycall.enqueue(object : Callback<List<CarritosLibrosBean>> {
            override fun onFailure(call: Call<List<CarritosLibrosBean>>, t: Throwable) {
                Log.e("onFailure!!!!!",t.message.toString())
            }

            override fun onResponse(call: Call<List<CarritosLibrosBean>>, response: Response<List<CarritosLibrosBean>>) {
                listaLibros = response.body()!!
                rclv_carrito.layoutManager = manager
                llenar(listaLibros, rclv_carrito)
            }

        })

    }
    fun llenar(listaLibros:List<CarritosLibrosBean>, recyclerView: RecyclerView){
        var adapter = AdapterCarrito(this, listaLibros)
        rclv_carrito.adapter = adapter
        adapter.notifyDataSetChanged()

    }
}