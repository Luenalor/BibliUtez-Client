package com.example.alexl.bibliutez

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.SearchView
import android.util.Log
import android.view.Menu
import android.widget.Toast
import com.example.alexl.bibliutez.model.libros.LibrosBean
import com.example.alexl.bibliutez.model.libros.LibrosJsonPlaceHolder
import kotlinx.android.synthetic.main.cliente_menu_principal.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ClienteMenuPrincipal : AppCompatActivity() {

    lateinit var sharedPreferences: SharedPreferences
    var adapterCliente:AdapterLibroCliente? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.cliente_menu_principal)
        val URL = "http://192.168.1.176:8080/BibliUtez_war/"
        //------------------------------------------

        sharedPreferences = getSharedPreferences("SHARED_PREF", Context.MODE_PRIVATE)
        //------------------------------------------


        val nombre:String = sharedPreferences.getString("nombre", "test");
        val apellido1:String = sharedPreferences.getString("apellido1", "test");
        val apellido2:String = sharedPreferences.getString("apellido2", "test");

        menuNombreCliente.setText("Bienvenido " + nombre + " " + apellido1 + " " + apellido2 )

        btnSalirCliente.setOnClickListener {
            val editor : SharedPreferences.Editor = sharedPreferences.edit()
            editor.clear()
            editor.apply()

            //------------------------------------------


            var logOut = Intent(this, MainActivity::class.java)
            startActivity(logOut)
            finish()

        }


        btnClientePerfil.setOnClickListener{
            var clientePerfil = Intent(this, ClientePerfil::class.java)
            startActivity(clientePerfil)
        }

        btnCarritoCompras.setOnClickListener{
            var CarritoCompras = Intent(this, CarritoCompras::class.java)
            startActivity(CarritoCompras)
        }


        //lista de libros
        var listaLibros: ArrayList<LibrosBean> = arrayListOf<LibrosBean>()
        var manager = LinearLayoutManager(this)



        //Retrofit builder
        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(URL + "libros/")
            .build()

        //object to call methods
        val jsonPlaceHolderApi = retrofit.create(LibrosJsonPlaceHolder::class.java)
       // val mycall: Call<List<LibrosBean>> = jsonPlaceHolderApi.librosFindAll()
        val mycall: Call<ArrayList<LibrosBean>> = jsonPlaceHolderApi.librosFindAll()

        mycall.enqueue(object : Callback<ArrayList<LibrosBean>> {
            override fun onFailure(call: Call<ArrayList<LibrosBean>>, t: Throwable) {
                Log.e("onFailure!!!!!", t.message.toString())
            }

            override fun onResponse(
                call: Call<ArrayList<LibrosBean>>,
                response: Response<ArrayList<LibrosBean>>
            ) {
                listaLibros = response.body()!!
                cl_rcvLibros.layoutManager = manager
                llenar(listaLibros, cl_rcvLibros)
            }

        })

    }
    fun llenar(listaLibros: ArrayList<LibrosBean>, recyclerView: RecyclerView){
        adapterCliente = AdapterLibroCliente(this, listaLibros)
        cl_rcvLibros.adapter = adapterCliente
        adapterCliente!!.notifyDataSetChanged()

    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_filter2, menu)
        var service = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        var txtSearch = menu?.findItem(R.id.txtBuscarLibroCliente)?.actionView as SearchView
        txtSearch.setSearchableInfo(service.getSearchableInfo(componentName))
        txtSearch.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            //Ejecuta la busqueda al momento de dar ENTER
            override fun onQueryTextSubmit(query: String?): Boolean {
                adapterCliente?.filter?.filter(query)
                return true
            }

            //Ejecuta la busqueda al ir agregando caracteres.
            override fun onQueryTextChange(query: String?): Boolean {
                adapterCliente?.filter?.filter(query)
                return true
            }

        })

        return true
    }
}