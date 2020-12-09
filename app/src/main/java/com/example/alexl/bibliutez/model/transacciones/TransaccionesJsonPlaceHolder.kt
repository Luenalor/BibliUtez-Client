package com.example.alexl.bibliutez.model.transacciones

import retrofit2.Call
import retrofit2.http.*

interface TransaccionesJsonPlaceHolder {

    @GET("findAll")
    fun transaccionesFindAll(): Call<List<TransaccionesBean>>

    @POST("add")
    fun transaccionesAdd(@Body usuario: TransaccionesBean): Call<Int>

    @PUT("update")
    fun transaccionesUpdate(@Body usuario: TransaccionesBean): Call<Boolean>

    @DELETE("delete/{id}")
    fun transaccionesDelete(@Path("id") id:Int): Call<Boolean>
}