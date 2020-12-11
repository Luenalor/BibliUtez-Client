package com.example.alexl.bibliutez.model.carritos_libros

import retrofit2.Call
import retrofit2.http.*

interface CarritosLibrosJsonPlaceHolder {


    @GET("findAll")
    fun carritosLibrosFindAll(): Call<List<CarritosLibrosBean>>

    @GET("findOne")
    fun carritosLibrosFindOne(@Query("id") id: Int): Call<CarritosLibrosBean>

    @GET("findCarrito")
    fun carritosLibrosFindCarrito(@Query("id") id: Int): Call<ArrayList<CarritosLibrosBean>>

    @POST("add")
    fun carritosLibrosAdd(@Body usuario: CarritosLibrosBean): Call<Int>

    @PUT("update")
    fun carritosLibrosUpdate(@Body usuario: CarritosLibrosBean): Call<Boolean>

    @DELETE("delete/{id}")
    fun carritosLibrosDelete(@Path("id") id: Int): Call<Boolean>


}