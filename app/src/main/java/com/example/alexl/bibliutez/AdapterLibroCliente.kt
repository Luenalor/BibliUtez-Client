package com.example.alexl.bibliutez

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.example.alexl.bibliutez.model.carritos.CarritosBean
import com.example.alexl.bibliutez.model.carritos.CarritosJsonPlaceHolder
import com.example.alexl.bibliutez.model.libros.LibrosBean
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class AdapterLibroCliente(var context: Context, var libros: ArrayList<LibrosBean>) :

    RecyclerView.Adapter<AdapterLibroCliente.ViewHolder>(), Filterable {

    var librosFiltrerCliente: ArrayList<LibrosBean> = ArrayList<LibrosBean>()

    init {
        librosFiltrerCliente = libros
    }


    override fun getFilter(): Filter {
        return object : Filter(){
            // Filter??? para ejecutar el filtro y hacer la búsqueda de atributos.
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                var query2:String = constraint.toString()

                if(query2.isEmpty()){
                    librosFiltrerCliente = libros
                }else{
                    var resultadoLibros = ArrayList<LibrosBean>()
                    for (libros in libros){
                        if (libros.nombre.toLowerCase().contains(query2.toLowerCase()) || libros.categoria.nombre.toLowerCase().contains(query2.toLowerCase()) || libros.autores.toLowerCase().contains(query2.toLowerCase())){
                            resultadoLibros.add(libros)
                        }
                    }
                    librosFiltrerCliente = resultadoLibros
                }
                var results2 = FilterResults()
                results2.values = librosFiltrerCliente

                return results2
            }

            // Actuailizar los datos en el recycler.
            override fun publishResults(constraint: CharSequence?, results2: FilterResults?) {
                librosFiltrerCliente = results2?.values as ArrayList<LibrosBean>
                notifyDataSetChanged()
            }

        }
    }

    // inflar = Crear elementos bajo un diseño predefinido
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        var libros_view = LayoutInflater.from(p0.context).inflate(R.layout.item_libro_cliente, p0, false)
        return ViewHolder(libros_view)
    }

    // Delimitar el número de elementos a inflar (crear)
    override fun getItemCount(): Int {
        return librosFiltrerCliente.size
    }

    override fun onBindViewHolder(p0: AdapterLibroCliente.ViewHolder, p1: Int) {
        p0.rellenarVista(librosFiltrerCliente[p1])
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun rellenarVista(libros: LibrosBean) {

            val URL = "http://192.168.1.176:8080/BibliUtez_war/"

            var nombre = itemView.findViewById(R.id.cl_txtNombreView) as TextView
            var autor = itemView.findViewById(R.id.cl_txtAutorLibro) as TextView
            var categoria = itemView.findViewById(R.id.cl_txtCategoriaLibro) as TextView
            var numPag = itemView.findViewById(R.id.cl_txtCantPaginasLibro) as TextView
            var precio = itemView.findViewById(R.id.cl_txtPrecioLibro) as TextView
            var btnAgregar = itemView.findViewById(R.id.btnAgregar) as ImageButton


            nombre.text = libros.nombre
            autor.text = libros.autores
            categoria.text = libros.categoria.nombre
            numPag.text = libros.num_pag.toString()
            precio.text = libros.precio.toString()

            /*
            itemView.setOnClickListener {
                var modificarLibro = Intent(context, ModificarLibros::class.java)
                modificarLibro.putExtra("libroModificacion", libros)
                context.startActivity(modificarLibro)
            }
            */
            btnAgregar.setOnClickListener {
                var ventana = android.app.AlertDialog.Builder(context)
                ventana.setTitle("Agregar")
                ventana.setMessage("¿Estás seguro de agregar al carriro este libro?")
                ventana.setPositiveButton("Si") { ventana, id ->

                    //Retrofit builder
                    val retrofit = Retrofit.Builder()
                        .addConverterFactory(GsonConverterFactory.create())
                        .baseUrl(URL + "carritos/")
                        .build()

                    //object to call methods
                    val jsonPlaceHolderApi = retrofit.create(CarritosJsonPlaceHolder::class.java)
                    val mycall: Call<CarritosBean> = jsonPlaceHolderApi.carritosFindOne(1)
                    mycall.enqueue(object : Callback<CarritosBean> {
                        override fun onFailure(call: Call<CarritosBean>, t: Throwable) {
                            Toast.makeText(
                                context,
                                "Estamos teniendo problemas para conectarte", Toast.LENGTH_LONG
                            ).show()
                        }

                        override fun onResponse(call: Call<CarritosBean>, response: Response<CarritosBean>) {
                            Toast.makeText(
                                context,
                                "El libro se agregó al carrito"+response.body()!!, Toast.LENGTH_LONG
                            ).show()
                            notifyDataSetChanged()
                        }

                    })
                }
                ventana.setNegativeButton("No") { ventana, id ->
                    ventana.dismiss()
                }
                ventana.show()
            }

        }
    }
}