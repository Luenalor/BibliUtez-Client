package com.example.alexl.bibliutez.model.transacciones_detalles


import retrofit2.Call
import retrofit2.http.*

interface DetallesJsonPlaceHolder {

    @GET("findAll")
    fun detallesFindAll(): Call<List<TransaccionesDetallesBean>>

    @POST("add")
    fun detallesAdd(@Body usuario: TransaccionesDetallesBean): Call<Int>

    @PUT("update")
    fun detallesUpdate(@Body usuario: TransaccionesDetallesBean): Call<Boolean>

    @DELETE("delete/{id}")
    fun detallesDelete(@Path("id") id:Int): Call<Boolean>
}