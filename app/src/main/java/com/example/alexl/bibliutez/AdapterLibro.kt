package com.example.alexl.bibliutez

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.alexl.bibliutez.model.libros.LibrosBean

class AdapterLibro(var context: Context, var libros: ArrayList<LibrosBean>) :
    RecyclerView.Adapter<AdapterLibro.ViewHolder>() {

    // inflar = Crear elementos bajo un diseño predefinido
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        var libros_view =
            LayoutInflater.from(context).inflate(R.layout.cliente_menu_principal, p0, false)
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

            var nombre = itemView.findViewById(R.id.txtNombreView) as TextView
            var autor = itemView.findViewById(R.id.txtAutorLibro) as TextView
            var categoria = itemView.findViewById(R.id.txtCategoriaLibro) as TextView
            var cantPages = itemView.findViewById(R.id.txtCantPaginasLibro) as TextView
            var precio = itemView.findViewById(R.id.txtPrecioLibro) as TextView



            nombre.text = libros.nombre
            autor.text = libros.autores
            categoria.text = libros.categoria.categoria
            cantPages.text = libros.categoria.toString()
            precio.text = libros.categoria.toString()
        }
    }
}