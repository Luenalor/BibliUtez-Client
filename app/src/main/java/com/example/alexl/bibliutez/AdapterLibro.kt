package com.example.alexl.bibliutez

import android.content.Context
import android.content.Intent
import android.support.v7.widget.AlertDialogLayout
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.example.alexl.bibliutez.R.styleable.AlertDialog
import com.example.alexl.bibliutez.model.libros.LibrosBean
import com.example.alexl.bibliutez.model.libros.LibrosJsonPlaceHolder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class AdapterLibro(var context: Context, var libros: List<LibrosBean>) :
    RecyclerView.Adapter<AdapterLibro.ViewHolder>() {

    // inflar = Crear elementos bajo un diseño predefinido
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        var libros_view = LayoutInflater.from(context).inflate(R.layout.item_libro, p0, false)
        return ViewHolder(libros_view)
    }

    // Delimitar el número de elementos a inflar (crear)
    override fun getItemCount(): Int {
        return libros.size
    }

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        p0.rellenarVista(libros[p1])
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun rellenarVista(libros: LibrosBean) {

            var img = itemView.findViewById(R.id.libroImg) as ImageView
            var nombre = itemView.findViewById(R.id.txtNombreView) as TextView
            var autor = itemView.findViewById(R.id.txtAutorLibro) as TextView
            var categoria = itemView.findViewById(R.id.txtCategoriaLibro) as TextView
            var numPag = itemView.findViewById(R.id.txtCantPaginasLibro) as TextView
            var precio = itemView.findViewById(R.id.txtPrecioLibro) as TextView
            var btn_Eliminar = itemView.findViewById(R.id.btnEliminar) as ImageButton


            nombre.text = libros.nombre
            autor.text = libros.autores
            categoria.text = libros.categoria.nombre
            numPag.text = libros.num_pag.toString()
            precio.text = libros.precio.toString()

            itemView.setOnClickListener{
                var modificarLibro = Intent(context, ModificarLibros::class.java)
                modificarLibro.putExtra("libroModificacion", libros)
                context.startActivity(modificarLibro)
            }
            btn_Eliminar.setOnClickListener{
                var ventana = android.app.AlertDialog.Builder(context)
                ventana.setTitle("Cuidado")
                ventana.setMessage("¿Estás seguro de eliminar el libro?")
                ventana.setPositiveButton("Si"){ventana, id->

                    //Retrofit builder
                    val retrofit = Retrofit.Builder()
                        .addConverterFactory(GsonConverterFactory.create())
                        .baseUrl("http://192.168.0.6:8080/BibliUtez_war/libros/")
                        .build()

                    //object to call methods
                    val jsonPlaceHolderApi = retrofit.create(LibrosJsonPlaceHolder::class.java)
                    val mycall: Call<Boolean> = jsonPlaceHolderApi.libroDelete(libros.id)
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
                ventana.setNegativeButton("No"){ventana, id->
                    ventana.dismiss()
                }
                ventana.show()
            }

        }
    }
}