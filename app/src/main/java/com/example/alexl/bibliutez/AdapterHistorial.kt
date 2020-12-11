package com.example.alexl.bibliutez

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.alexl.bibliutez.model.carritos_libros.CarritosLibrosBean
import com.example.alexl.bibliutez.model.carritos_libros.CarritosLibrosJsonPlaceHolder
import com.example.alexl.bibliutez.model.transacciones.TransaccionesBean
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class AdapterHistorial(var context: Context, var historial: ArrayList<TransaccionesBean>) :
    RecyclerView.Adapter<AdapterHistorial.ViewHolder>() {


    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        var historial_view =
            LayoutInflater.from(p0.context).inflate(R.layout.item_historial, p0, false)
        return ViewHolder(historial_view)
    }

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        p0.rellenarVista(historial[p1])
    }

    override fun getItemCount(): Int {
        return historial.size
    }


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun rellenarVista(transaccionesBean: TransaccionesBean) {

            val preference =
                itemView.context.getSharedPreferences("SHARED_PREF", Context.MODE_PRIVATE)
            var idCarrito = preference.getInt("idCarrito", 0)
            val URL = "http://192.168.1.176:8080/BibliUtez_war/"

            var titulo = itemView.findViewById(R.id.historialTitulo) as TextView
            var autor = itemView.findViewById(R.id.historialAutor) as TextView
            var precio = itemView.findViewById(R.id.historialPrecio) as TextView

            titulo.text = transaccionesBean.libro.nombre
            autor.text = transaccionesBean.libro.autores
            precio.text = transaccionesBean.libro.precio.toString()

        }
    }
}