package com.example.alexl.bibliutez

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.Spinner
import com.example.alexl.bibliutez.model.categorias.CategoriasBean
import com.example.alexl.bibliutez.model.categorias.CategoriasJsonPlaceHolder
import com.example.alexl.bibliutez.model.libros.LibrosJsonPlaceHolder
import com.example.alexl.bibliutez.model.libros.LibrosBean
import kotlinx.android.synthetic.main.registro_libros.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import android.widget.Toast
import android.widget.EditText


class RegistroLibros : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.registro_libros)
        val URL = "http://192.168.0.8:8080/BibliUtez_war/"

        btnRegistroConsultarLibros.setOnClickListener {
            var consulta = Intent(this, ListaLibrosActivity::class.java)
            startActivity(consulta)
        }

        //---Mostrar Categorias----


        //Spinner
        var sp_categoria_posicion: Int = 1
        var categoria: CategoriasBean = CategoriasBean(0, "")
        var sp_categoria = findViewById<Spinner>(R.id.spnLibroCategoria)
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


        fun llenar(categoriasBean: CategoriasBean) {
            categoria = categoriasBean

        }



        btnRegistroLibro.setOnClickListener {
            try {
                val titulo = findViewById<EditText>(R.id.txtRegistroLibroTitulo).text.toString()
                val editorial = findViewById<EditText>(R.id.txtRegistroLibroEditorial).text.toString()
                val autor = findViewById<EditText>(R.id.txtRegistroAutor).text.toString()
                val input1 = findViewById(R.id.txtRegistroLibroPrecio) as EditText
                val precio = ((input1.text.toString())).toDouble()
                val input2 = findViewById(R.id.txtRegistroLibroNumPag) as EditText
                val num_pag = Integer.parseInt((input2.text.toString()))
                val input3 = findViewById(R.id.txtRegistroLibroStock) as EditText
                val stock = Integer.parseInt((input3.text.toString()))

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
                //Llenar object libroBean
                val libro: LibrosBean = LibrosBean(
                    0,
                    titulo,
                    editorial,
                    autor,
                    categoria,
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
                val mycall: Call<Int> = jsonPlaceHolderApi.librosAdd(libro)
                mycall.enqueue(object : Callback<Int> {
                    override fun onFailure(call: Call<Int>, t: Throwable) {
                        Toast.makeText(
                            this@RegistroLibros,
                            "!!!${t}", Toast.LENGTH_LONG
                        ).show()
                    }

                    override fun onResponse(call: Call<Int>, response: Response<Int>) {
                        Toast.makeText(
                            this@RegistroLibros,
                            "Agregado correctamente"+response.body(), Toast.LENGTH_LONG
                        ).show()
                    }
                })
            } catch (e: Exception) {
                Toast.makeText(
                    this@RegistroLibros,
                    "Ocurrió un error ${e}", Toast.LENGTH_LONG
                ).show()
            }

        }


    }

}



