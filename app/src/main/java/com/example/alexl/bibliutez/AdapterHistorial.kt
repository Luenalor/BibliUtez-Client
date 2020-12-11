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

class AdapterHistorial(var listHistorial: ArrayList<TransaccionesBean>): RecyclerView.Adapter<AdapterHistorial.ViewHolder>(){
    class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        fun bindItems(historial:TransaccionesBean){
            var historialTitulo = itemView.findViewById<TextView>(R.id.historialTitulo) as TextView
            var historialAutor = itemView.findViewById<TextView>(R.id.historialAutor) as TextView
            var historialPrecio = itemView.findViewById<TextView>(R.id.historialPrecio) as TextView

            historialTitulo.text = historial.libro.nombre
            historialAutor.text = historial.libro.autores
            historialPrecio.text = historial.libro.precio.toString()



        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ViewHolder {
        val v = LayoutInflater.from(parent?.context).inflate(R.layout.item_historial,parent, false)

        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: AdapterHistorial.ViewHolder, position: Int) {
        holder.bindItems(listHistorial[position])
    }

    override fun getItemCount(): Int {
        return listHistorial.size
    }
}

   /* RecyclerView.Adapter<AdapterHistorial.ViewHolder>() {

    lateinit var sharedPreferences: SharedPreferences



    // inflar = Crear elementos bajo un diseño predefinido
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        var historial_view = LayoutInflater.from(context).inflate(R.layout.item_historial, p0, false)
        return ViewHolder(historial_view)
    }

    // Delimitar el número de elementos a inflar (crear)
    override fun getItemCount(): Int {
        return historial.size
    }

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        p0.rellenarVista(historial[p1])
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun rellenarVista(historial: TransaccionesBean) {


            val URL = "http://192.168.1.176:8080/BibliUtez_war/"



            var historialTitulo = itemView.findViewById<TextView>(R.id.historialTitulo) as TextView
            var historialAutor = itemView.findViewById<TextView>(R.id.historialAutor) as TextView
            var historialPrecio = itemView.findViewById<TextView>(R.id.historialPrecio) as TextView

            Log.e("nombre:", historial.libro.nombre)
            Log.e("autores:", historial.libro.autores)
            Log.e("precio:", historial.libro.precio.toString())
            historialTitulo.text = historial.libro.nombre
            historialAutor.text = historial.libro.autores
            historialPrecio.text = historial.libro.precio.toString()



        }
    }
}*/