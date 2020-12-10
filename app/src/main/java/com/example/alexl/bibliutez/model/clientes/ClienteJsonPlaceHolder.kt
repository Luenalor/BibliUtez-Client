package com.example.alexl.bibliutez.model.clientes

import retrofit2.Call
import retrofit2.http.*

interface ClienteJsonPlaceHolder {

    @GET("findAll")
    fun clienteFindAll(): Call<List<ClientesBean>>

    @GET("findCliente")
    fun clienteFindCliente(@Query ("id")id: Int): Call<ClientesBean>

    @PUT("add")
    fun clienteAdd(@Body cliente: ClientesBean): Call<Int>

    @PUT("update")
    fun clienteUpdate(@Body cliente: ClientesBean): Call<Boolean>


}