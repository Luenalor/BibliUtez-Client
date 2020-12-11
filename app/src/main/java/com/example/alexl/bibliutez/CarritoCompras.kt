package com.example.alexl.bibliutez

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import com.example.alexl.bibliutez.model.carritos_libros.CarritosLibrosBean
import com.example.alexl.bibliutez.model.carritos_libros.CarritosLibrosJsonPlaceHolder
import kotlinx.android.synthetic.main.carrito_compras.*
import kotlinx.android.synthetic.main.cliente_menu_principal.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CarritoCompras : AppCompatActivity() {

    lateinit var sharedPreferences: SharedPreferences


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.carrito_compras)
        val URL = "http://192.168.1.176:8080/BibliUtez_war/"

        sharedPreferences = getSharedPreferences("SHARED_PREF", Context.MODE_PRIVATE)

        val idCarrito:Int = sharedPreferences.getInt("idCarrito", 0);




        btnHomeCliente.setOnClickListener{
            var home = Intent(this,ClienteMenuPrincipal::class.java )
            startActivity(home)
        }

        btnAccederPerfil.setOnClickListener{
            var perfil = Intent(this,ClientePerfil::class.java )
            startActivity(perfil)
        }


        btnHistorialCompras.setOnClickListener{
            var perfil = Intent(this,ClienteHistorial::class.java )
            startActivity(perfil)
        }


        var listaLibros: ArrayList<CarritosLibrosBean> = arrayListOf<CarritosLibrosBean>()
        var manager = LinearLayoutManager(this)


        //Retrofit builder
        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(URL+"carritos_libros/")
            .build()

        //object to call methods
        val jsonPlaceHolderApi = retrofit.create(CarritosLibrosJsonPlaceHolder::class.java)
        val mycall: Call<ArrayList<CarritosLibrosBean>> = jsonPlaceHolderApi.carritosLibrosFindCarrito(idCarrito)

        mycall.enqueue(object : Callback<ArrayList<CarritosLibrosBean>> {
            override fun onFailure(call: Call<ArrayList<CarritosLibrosBean>>, t: Throwable) {
                Log.e("onFailure!!!!!",t.message.toString())
            }

            override fun onResponse(call: Call<ArrayList<CarritosLibrosBean>>, response: Response<ArrayList<CarritosLibrosBean>>) {
                listaLibros = response.body()!!
                rclv_carrito.layoutManager = manager
                llenar(listaLibros, rclv_carrito)
            }

        })

    }
    fun llenar(listaLibros:ArrayList<CarritosLibrosBean>, recyclerView: RecyclerView){
        var adapter = AdapterCarrito(this, listaLibros)
        rclv_carrito.adapter = adapter
        adapter.notifyDataSetChanged()

    }
}