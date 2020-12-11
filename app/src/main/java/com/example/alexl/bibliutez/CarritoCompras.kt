package com.example.alexl.bibliutez

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import com.example.alexl.bibliutez.model.carritos_libros.CarritosLibrosBean
import com.example.alexl.bibliutez.model.carritos_libros.CarritosLibrosJsonPlaceHolder
import com.example.alexl.bibliutez.model.clientes.ClienteJsonPlaceHolder
import com.example.alexl.bibliutez.model.clientes.ClientesBean
import kotlinx.android.synthetic.main.carrito_compras.*
import kotlinx.android.synthetic.main.cliente_perfil.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.Exception

class CarritoCompras : AppCompatActivity() {

    lateinit var sharedPreferences: SharedPreferences
    var adapterCarrito: AdapterCarrito? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.carrito_compras)
        val URL = "http://192.168.1.176:8080/BibliUtez_war/"



        sharedPreferences = getSharedPreferences("SHARED_PREF", Context.MODE_PRIVATE)

        val idCarrito: Int = sharedPreferences.getInt("idCarrito", 0);
        val idUsuario: Int = sharedPreferences.getInt("idUsuario", 0);
        var domicilio: String = ""


        val retrofit2 = Retrofit.Builder()
            //Armar la URL
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(URL + "clientes/")
            //Cargamos y creamos nuestro objeto retrofit
            .build()

        val jsonPlaceHolderApi2 = retrofit2.create(ClienteJsonPlaceHolder::class.java)
        val mycall2: Call<ClientesBean> = jsonPlaceHolderApi2.clienteFindCliente(idUsuario)

        mycall2.enqueue(object : Callback<ClientesBean> {
            override fun onResponse(call: Call<ClientesBean>, response: Response<ClientesBean>) {
                try {
                    domicilio = response.body()!!.domicilio
                    domicilioCarrito.setText("Domicilio: \n" + domicilio)
                }catch (e: Exception){
                    Toast.makeText(
                        this@CarritoCompras,
                        "DOM." + domicilio , Toast.LENGTH_LONG
                    ).show()

                }

            }

            override fun onFailure(call: Call<ClientesBean>, t: Throwable) {
            }
        })



        btnHomeCliente.setOnClickListener {
            var home = Intent(this, ClienteMenuPrincipal::class.java)
            startActivity(home)
        }


        btnFinalizarCompra.setOnClickListener {


            val retrofitFinalizar = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(URL + "carritos_libros/")
                .build()

            //object to call methods
            val finalizarJson = retrofitFinalizar.create(CarritosLibrosJsonPlaceHolder::class.java)
            val mycall: Call<Boolean> =
                finalizarJson.deleteCarrito(idCarrito)

            mycall.enqueue(object : Callback<Boolean> {
                override fun onFailure(call: Call<Boolean>, t: Throwable) {
                    Log.e("onFailure!!!!!", t.message.toString())
                }

                override fun onResponse(
                    call: Call<Boolean>,
                    response: Response<Boolean>
                ) {
                    Toast.makeText(
                        this@CarritoCompras,
                        "¡Gracias por tu compra!", Toast.LENGTH_LONG
                    ).show()
                }

            })

            var home = Intent(this, ClienteMenuPrincipal::class.java)
            startActivity(home)
        }


        btnAccederPerfil.setOnClickListener {
            var perfil = Intent(this, ClientePerfil::class.java)
            startActivity(perfil)
        }


        btnHistorialCompras.setOnClickListener {
            var perfil = Intent(this, ClienteHistorial::class.java)
            startActivity(perfil)
        }


        var listaLibros: ArrayList<CarritosLibrosBean> = arrayListOf<CarritosLibrosBean>()
        var manager = LinearLayoutManager(this)


        //Retrofit builder
        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(URL + "carritos_libros/")
            .build()

        //object to call methods
        val jsonPlaceHolderApi = retrofit.create(CarritosLibrosJsonPlaceHolder::class.java)
        val mycall: Call<ArrayList<CarritosLibrosBean>> =
            jsonPlaceHolderApi.carritosLibrosFindCarrito(idCarrito)

        mycall.enqueue(object : Callback<ArrayList<CarritosLibrosBean>> {
            override fun onFailure(call: Call<ArrayList<CarritosLibrosBean>>, t: Throwable) {
                Log.e("onFailure!!!!!", t.message.toString())
            }

            override fun onResponse(
                call: Call<ArrayList<CarritosLibrosBean>>,
                response: Response<ArrayList<CarritosLibrosBean>>
            ) {
                listaLibros = response.body()!!
                rclv_carrito.layoutManager = manager
                llenar(listaLibros, rclv_carrito)

                rclv_carrito.adapter = adapterCarrito

                adapterCarrito!!.notifyDataSetChanged()

            }

        })
    }

    fun llenar(listaLibros: ArrayList<CarritosLibrosBean>, recyclerView: RecyclerView) {
        adapterCarrito = AdapterCarrito(this, listaLibros)
        rclv_carrito.adapter = adapterCarrito
        adapterCarrito!!.notifyDataSetChanged()

    }
}