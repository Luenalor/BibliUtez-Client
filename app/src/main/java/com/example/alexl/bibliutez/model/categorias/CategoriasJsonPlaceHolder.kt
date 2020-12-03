package com.example.alexl.bibliutez.model.categorias

import com.example.alexl.bibliutez.model.libros.LibrosBean
import retrofit2.Call
import retrofit2.http.*

interface CategoriasJsonPlaceHolder {
    @GET("findAll")
    fun categoriasFindAll(): Call<List<CategoriasBean>>

    @GET("findOne")
    fun categoriasFindOne(@Query("id") id: Int): Call<CategoriasBean>

    @POST("add")
    fun categoriasAdd(@Body libro: CategoriasBean): Call<CategoriasBean>

    @PUT("update")
    fun librosUpdate(libro: CategoriasBean): Call<Boolean>
}