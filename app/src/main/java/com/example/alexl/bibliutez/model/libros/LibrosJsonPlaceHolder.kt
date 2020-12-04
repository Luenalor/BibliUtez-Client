package com.example.alexl.bibliutez.model.libros

import retrofit2.Call
import retrofit2.http.*

interface LibrosJsonPlaceHolder {

    @GET("findAll")
    fun librosFindAll(): Call<List<LibrosBean>>

    @POST("add")
    fun librosAdd(@Body libro: LibrosBean):Call<Int>

    @PUT("update")
    fun librosUpdate(@Body libro:LibrosBean):Call<Boolean>

    @DELETE("delete/{id}")
    fun libroDelete(@Path("id") id:Int):Call<Boolean>

}