package com.example.alexl.bibliutez.model.libros

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT

interface LibrosJsonPlaceHolder {

    @GET("findAll")
    fun librosFindAll(): Call<List<LibrosBean>>

    @POST("add")
    fun librosAdd(@Body libro: LibrosBean):Call<Int>

    @PUT("update")
    fun librosUpdate(libro:LibrosBean):Call<Boolean>
}