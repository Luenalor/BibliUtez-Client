package com.example.alexl.bibliutez

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.example.alexl.bibliutez.model.carritos.CarritosBean
import com.example.alexl.bibliutez.model.carritos.CarritosJsonPlaceHolder
import com.example.alexl.bibliutez.model.carritos_libros.CarritosLibrosBean
import com.example.alexl.bibliutez.model.carritos_libros.CarritosLibrosJsonPlaceHolder
import com.example.alexl.bibliutez.model.libros.LibrosBean
import com.example.alexl.bibliutez.model.roles.RolesBean
import com.example.alexl.bibliutez.model.usuarios.UsuariosBean
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

            val preference = itemView.context.getSharedPreferences("SHARED_PREF", Context.MODE_PRIVATE)
            var idCarrito = preference.getInt("idCarrito", 0)

            val nombreCliente:String = preference.getString("nombre", "No Aplica.");
            val apellido1:String = preference.getString("apellido1", "No Aplica.");
            val apellido2:String = preference.getString("apellido2", "No Aplica.");
            val emailDos:String = preference.getString("emailDos", "No Aplica.");
            val sexo:String = preference.getString("sexo", "No Aplica.");
            val idUsuario:Int = preference.getInt("idUsuario", 0);
            val idRol:Int = preference.getInt("idRol", 0);
            var clienteFechaNacimiento: String = ""
            var clienteDomicilio: String = ""
            var clienteCelular: String = ""


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
                ventana.setMessage("¿Estás seguro de agregar al carrito este libro?")
                ventana.setPositiveButton("Si") { ventana, id ->

                    //Retrofit builder
                    val retrofit = Retrofit.Builder()
                        .addConverterFactory(GsonConverterFactory.create())
                        .baseUrl(URL + "carritos_libros/")
                        .build()

                    var roles: RolesBean = RolesBean(idRol, "")
                    var usuario: UsuariosBean = UsuariosBean(idUsuario, nombreCliente, apellido1, apellido2, emailDos, 1, sexo,  roles,
                        "")
                    var carrito : CarritosBean = CarritosBean(idCarrito, usuario)
                    var carritoLibros : CarritosLibrosBean = CarritosLibrosBean(0, carrito, libros, 1 )

                    //object to call methods
                    val jsonPlaceHolderApi = retrofit.create(CarritosLibrosJsonPlaceHolder::class.java)
                    val mycall: Call<Int> = jsonPlaceHolderApi.carritosLibrosAdd(carritoLibros)
                    mycall.enqueue(object : Callback<Int> {
                        override fun onFailure(call: Call<Int>, t: Throwable) {
                            Toast.makeText(
                                context,
                                "Estamos teniendo problemas para conectarte", Toast.LENGTH_LONG
                            ).show()
                        }

                        override fun onResponse(call: Call<Int>, response: Response<Int>) {
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