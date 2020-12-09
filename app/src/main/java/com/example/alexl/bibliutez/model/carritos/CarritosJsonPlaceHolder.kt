package com.example.alexl.bibliutez.model.carritos

import retrofit2.Call
import retrofit2.http.*

interface CarritosJsonPlaceHolder {

    @GET("findAll")
    fun carritosLibrosFindAll(): Call<List<CarritosBean>>

    @GET("findOne")
    fun carritosFindOne(@Query("id") id: Int): Call<CarritosBean>

    @POST("add")
    fun carritosLibrosAdd(@Body usuario: CarritosBean): Call<Int>

    @PUT("update")
    fun carritosLibrosUpdate(@Body usuario: CarritosBean): Call<Boolean>

    @DELETE("delete/{id}")
    fun carritosLibrosDelete(@Path("id") id:Int): Call<Boolean>

    @GET("check")
    fun usuarioscheck(
        @Query("email") email: String,
        @Query("password") password: String
    ): Call<CarritosBean>
}