package com.example.alexl.bibliutez

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import com.example.alexl.bibliutez.model.categorias.CategoriasBean
import com.example.alexl.bibliutez.model.categorias.CategoriasJsonPlaceHolder
import com.example.alexl.bibliutez.model.libros.LibrosBean
import com.example.alexl.bibliutez.model.libros.LibrosJsonPlaceHolder
import kotlinx.android.synthetic.main.modificar_libros.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ModificarLibros : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.modificar_libros)
        val URL = "http://192.168.1.176:8080/BibliUtez_war/"
        var libro: LibrosBean = intent.getSerializableExtra("libroModificacion") as LibrosBean

        //llenar libro
        txtModificarLibroTitulo.setText("${libro.nombre}")
        txtModificarLibroEditorial.setText("${libro.editorial}")
        txtModificarLibroAutor.setText("${libro.autores}")
        txtModificarLibroPrecio.setText("${libro.precio}")
        txtModificarLibroStock.setText("${libro.stock}")
        txtModificarLibroNumPag.setText("${libro.num_pag}")


        //Spinner
        var sp_categoria = findViewById<Spinner>(R.id.spnLibroCategoria)
        var sp_categoria_posicion: Int = 1
        var categoria: CategoriasBean = CategoriasBean(0, "")
        sp_categoria.onItemSelectedListener =
            object : AdapterView.OnItemClickListener, AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                    sp_categoria_posicion = position + 1

                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    TODO("Not yet implemented")
                }

                override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                    TODO("Not yet implemented")
                }

            }

        //Llenar categoria
        fun llenar(categoriasBean: CategoriasBean) {
            categoria = categoriasBean

        }
        //Retrofit builder
        val retrofit2 = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(URL+"categorias/")
            .build()

        //object to call methods
        val jsonPlaceHolderApi2 = retrofit2.create(CategoriasJsonPlaceHolder::class.java)
        val call: Call<CategoriasBean> = jsonPlaceHolderApi2.categoriasFindOne(sp_categoria_posicion)
        call.enqueue(object : Callback<CategoriasBean> {
            override fun onFailure(call: Call<CategoriasBean>, t: Throwable) {

            }

            override fun onResponse(call: Call<CategoriasBean>, response: Response<CategoriasBean>) {
                llenar(response.body()!!)
            }
        })

        btnModificarLibro.setOnClickListener {
            try {
                //Llenar libro
                val titulo = findViewById<EditText>(R.id.txtModificarLibroTitulo).text.toString()
                val editorial = findViewById<EditText>(R.id.txtModificarLibroEditorial).text.toString()
                val autor = findViewById<EditText>(R.id.txtModificarLibroAutor).text.toString()
                val input1 = findViewById(R.id.txtModificarLibroPrecio) as EditText
                val precio = ((input1.text.toString())).toDouble()
                val input2 = findViewById(R.id.txtModificarLibroNumPag) as EditText
                val num_pag = Integer.parseInt((input2.text.toString()))
                val input3 = findViewById(R.id.txtModificarLibroStock) as EditText
                val stock = Integer.parseInt((input3.text.toString()))

                val categoriaSpinner: CategoriasBean = CategoriasBean(sp_categoria_posicion)


                val newBook: LibrosBean = LibrosBean(
                    libro.id,
                    titulo,
                    editorial,
                    autor,
                    categoriaSpinner,
                    precio,
                    num_pag,
                    stock
                )
                //Retrofit builder
                val retrofit = Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(URL+"libros/")
                    .build()

                //object to call methods
                val jsonPlaceHolderApi = retrofit.create(LibrosJsonPlaceHolder::class.java)
                val mycall: Call<Boolean> = jsonPlaceHolderApi.librosUpdate(newBook)
                mycall.enqueue(object : Callback<Boolean> {
                    override fun onFailure(call: Call<Boolean>, t: Throwable) {
                        Toast.makeText(
                            this@ModificarLibros,
                            newBook.id, Toast.LENGTH_LONG
                        ).show()
                    }

                    override fun onResponse(call: Call<Boolean>, response: Response<Boolean>) {
                        Toast.makeText(
                            this@ModificarLibros,
                            "Modificación exitosa", Toast.LENGTH_LONG
                        ).show()
                    }

                })
            } catch (e: Exception) {
                Toast.makeText(
                    this@ModificarLibros,
                    "Ocurrió un error"+e, Toast.LENGTH_LONG
                ).show()
            }
        }


        btnConsultarLibros.setOnClickListener {
            var verLibros = Intent(this, ListaLibrosActivity::class.java)
            startActivity(verLibros)
        }


    }
}
