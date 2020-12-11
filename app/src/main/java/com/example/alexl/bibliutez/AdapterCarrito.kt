package com.example.alexl.bibliutez

import android.content.Context
import android.content.Intent
import android.preference.PreferenceManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.alexl.bibliutez.model.carritos_libros.CarritosLibrosBean
import com.example.alexl.bibliutez.model.carritos_libros.CarritosLibrosJsonPlaceHolder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class AdapterCarrito(var context: Context, var carrito: ArrayList<CarritosLibrosBean>) :
    RecyclerView.Adapter<AdapterCarrito.ViewHolder>() {




    // inflar = Crear elementos bajo un diseño predefinido
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        var carrito_view = LayoutInflater.from(context).inflate(R.layout.item_carrito, p0, false)
        return ViewHolder(carrito_view)
    }

    // Delimitar el número de elementos a inflar (crear)
    override fun getItemCount(): Int {
        return carrito.size
    }

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        p0.rellenarVista(carrito[p1])
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun rellenarVista(carrito: CarritosLibrosBean) {

            val preference = itemView.context.getSharedPreferences("SHARED_PREF", Context.MODE_PRIVATE)
            var idCarrito = preference.getInt("idCarrito", 0)
            Toast.makeText(
                context,
                "idRol" + idCarrito, Toast.LENGTH_LONG
            ).show()

            val URL = "http://192.168.1.176:8080/BibliUtez_war/"

            var nombre = itemView.findViewById(R.id.historialTitulo) as TextView
            var precio = itemView.findViewById(R.id.historialPrecio) as TextView
            var btn_Eliminar = itemView.findViewById(R.id.btn_eliminar_libro) as Button

            nombre.text = carrito.libros.nombre
            precio.text = carrito.libros.precio.toString()


            itemView.setOnClickListener{
                var verLibro = Intent(context, ModificarLibros::class.java)
                verLibro.putExtra("verLibro", verLibro)
                context.startActivity(verLibro)
            }

            btn_Eliminar.setOnClickListener{
                var ventana = android.app.AlertDialog.Builder(context)
                ventana.setTitle("Carrito")
                ventana.setMessage("¿Estás seguro de eliminar el libro?")
                ventana.setPositiveButton("Si"){ ventana, id->

                    //Retrofit builder
                    val retrofit = Retrofit.Builder()
                        .addConverterFactory(GsonConverterFactory.create())
                        .baseUrl(URL + "carritos_libros/")
                        .build()

                    //object to call methods
                    val jsonPlaceHolderApi = retrofit.create(CarritosLibrosJsonPlaceHolder::class.java)
                    val mycall: Call<Boolean> = jsonPlaceHolderApi.carritosLibrosDelete(carrito.id)
                    mycall.enqueue(object : Callback<Boolean> {
                        override fun onFailure(call: Call<Boolean>, t: Throwable) {

                        }

                        override fun onResponse(call: Call<Boolean>, response: Response<Boolean>) {
                            Toast.makeText(
                                context,
                                "El libro se eliminó", Toast.LENGTH_LONG
                            ).show()
                            notifyDataSetChanged()
                        }

                    })
                }
                ventana.setNegativeButton("No"){ ventana, id->
                    ventana.dismiss()
                }
                ventana.show()
            }

        }
    }
}