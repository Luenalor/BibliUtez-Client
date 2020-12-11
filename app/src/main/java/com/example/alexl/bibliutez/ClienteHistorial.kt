package com.example.alexl.bibliutez

import android.content.Context
import android.content.SharedPreferences
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.LinearLayout
import android.widget.Toast
import com.example.alexl.bibliutez.model.libros.LibrosBean
import com.example.alexl.bibliutez.model.libros.LibrosJsonPlaceHolder
import com.example.alexl.bibliutez.model.transacciones.TransaccionesBean
import com.example.alexl.bibliutez.model.transacciones.TransaccionesJsonPlaceHolder
import kotlinx.android.synthetic.main.cliente_historial.*
import kotlinx.android.synthetic.main.gerente_lista_libros.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.Exception


class ClienteHistorial : AppCompatActivity() {
    lateinit var sharedPreferences: SharedPreferences
    var adapterHistorial: AdapterHistorial? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.cliente_historial)

        val recyclerView:RecyclerView = findViewById(R.id.rclv_historial)
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)
        var historialIdk = ArrayList<TransaccionesBean>()


        val URL = "http://192.168.1.176:8080/BibliUtez_war/"
        sharedPreferences = getSharedPreferences("SHARED_PREF", Context.MODE_PRIVATE)

        val idCarrito: Int = sharedPreferences.getInt("idCarrito", 1);


        //var manager = LinearLayoutManager(this)


        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(URL + "transacciones/")
            .build()

        val jsonPlaceHolderApi = retrofit.create(TransaccionesJsonPlaceHolder::class.java)
        val mycall: Call<ArrayList<TransaccionesBean>> = jsonPlaceHolderApi.findHistory(idCarrito)

        mycall.enqueue(object : Callback<ArrayList<TransaccionesBean>> {
            override fun onResponse(
                call: Call<ArrayList<TransaccionesBean>>,
                response: Response<ArrayList<TransaccionesBean>>
            ) {
                historialIdk = response.body()!!
                    rclv_historial.layoutManager = recyclerView.layoutManager
                    llenar(historialIdk, rclv_historial)
                Toast.makeText(
                    this@ClienteHistorial,
                    "Ha ocurrido uasdasfn error.", Toast.LENGTH_LONG
                ).show()
            }

            override fun onFailure(call: Call<ArrayList<TransaccionesBean>>, t: Throwable) {
                Toast.makeText(
                    this@ClienteHistorial,
                    "Ha ocurrido un error.", Toast.LENGTH_LONG
                ).show()
            }

        })

    }

    fun llenar(historialIdk: ArrayList<TransaccionesBean>, recyclerView: RecyclerView) {

        val adapter = AdapterHistorial(historialIdk)
        recyclerView.adapter = adapter


    }

}