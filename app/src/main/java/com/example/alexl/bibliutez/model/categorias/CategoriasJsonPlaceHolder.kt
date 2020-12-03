package com.example.alexl.bibliutez.model.categorias

import com.example.alexl.bibliutez.model.libros.LibrosBean
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT

interface CategoriasJsonPlaceHolder {
    @GET("findAll")
    fun categoriasFindAll(): Call<List<CategoriasBean>>

    @POST("add")
    fun categoriasAdd(@Body libro: CategoriasBean): Call<CategoriasBean>

    @PUT("update")
    fun librosUpdate(libro: CategoriasBean): Call<Boolean>
}