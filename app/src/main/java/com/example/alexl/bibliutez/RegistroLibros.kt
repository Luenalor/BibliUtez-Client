package com.example.alexl.bibliutez

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.text.Editable
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.Spinner
import com.example.alexl.bibliutez.model.categorias.CategoriasBean
import com.example.alexl.bibliutez.model.categorias.CategoriasJsonPlaceHolder
import com.example.alexl.bibliutez.model.libros.LibrosJsonPlaceHolder
import com.example.alexl.bibliutez.model.libros.LibrosBean
import kotlinx.android.synthetic.main.gerente_lista_libros.*
import kotlinx.android.synthetic.main.registro_libros.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import android.widget.ArrayAdapter
import android.widget.Toast
import android.widget.EditText




class RegistroLibros : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.registro_libros)
        var m_categorias: List<CategoriasBean>

        btnRegistroConsultarLibros.setOnClickListener {
            var consulta = Intent(this, ListaLibrosActivity::class.java)
            startActivity(consulta)
        }

        //---Mostrar Categorias----

        //Retrofit builder
        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("http://192.168.0.6:8080/BibliUtez_war/categorias/")
            .build()

        //object to call methods
        val jsonPlaceHolderApi = retrofit.create(CategoriasJsonPlaceHolder::class.java)
        val call: Call<List<CategoriasBean>> = jsonPlaceHolderApi.categoriasFindAll()


        call.enqueue(object : Callback<List<CategoriasBean>> {
            override fun onFailure(call: Call<List<CategoriasBean>>, t: Throwable) {
                Log.e("onFailure!!!!!Categoria", t.message.toString())
            }

            override fun onResponse(call: Call<List<CategoriasBean>>, response: Response<List<CategoriasBean>>) {
                try {
                    m_categorias =  response.body()!!
                    llenar(m_categorias)
                }catch (e:Exception){

                }
            }

        })

        btnRegistroLibro.setOnClickListener{
            try{
                val titulo = findViewById<EditText>(R.id.txtRegistroLibroTitulo).text.toString()
                val editorial = findViewById<EditText>(R.id.txtRegistroLibroEditorial).text.toString()
                val autor = findViewById<EditText>(R.id.txtRegistroAutor).text.toString()
                val input1 = findViewById(R.id.txtRegistroLibroPrecio) as EditText
                val precio = Integer.parseInt((input1.text.toString()))
                val input2 = findViewById(R.id.txtRegistroLibroNumPag) as EditText
                val num_pag = Integer.parseInt((input2.text.toString()))
                val input3 = findViewById(R.id.txtRegistroLibroStock) as EditText
                val stock = Integer.parseInt((input3.text.toString()))
                //Spinner
                val sp_categoria = findViewById<Spinner>(R.id.spnLibroCategoria)
                sp_categoria.onItemSelectedListener = object : AdapterView.OnItemClickListener, AdapterView.OnItemSelectedListener {
                    override fun onNothingSelected(parent: AdapterView<*>?) {
                        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                    }

                    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                    }

                    override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                    }

                }

                //Llenar object libroBean
                val libro:LibrosBean = LibrosBean(
                    0,titulo.toString(),editorial.toString(),autor.toString(),CategoriasBean(0,"Novela de Aventura"),precio.toDouble(),num_pag.toInt(),stock.toInt())


                //Retrofit builder
                val retrofit = Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl("http://192.168.0.6:8080/BibliUtez_war/libros/")
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
                            "Agregado correctamente${libro.nombre}", Toast.LENGTH_LONG
                        ).show()
                    }
                })
            }catch (e:Exception){
                Toast.makeText(
                    this@RegistroLibros,
                    "Ocurrió un error ${e}", Toast.LENGTH_LONG
                ).show()
            }

        }


    }
    fun llenar(listaCategorias: List<CategoriasBean>){
        var adapter:ArrayAdapter<String>
            adapter = ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item)

        }

    }



