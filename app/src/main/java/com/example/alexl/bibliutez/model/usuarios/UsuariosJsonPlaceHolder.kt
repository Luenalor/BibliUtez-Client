package com.example.alexl.bibliutez.model.usuarios

import retrofit2.Call
import retrofit2.http.*

interface UsuariosJsonPlaceHolder {


    @GET("findAll")
    fun usuariosFindAll(): Call<List<UsuariosBean>>

    @POST("add")
    fun usuariosAdd(@Body usuario:UsuariosBean ): Call<Int>

    @PUT("update")
    fun usuariosUpdate(usuario: UsuariosBean): Call<Boolean>

    @GET("check")
    fun usuarioscheck(
        @Query("email") email:String,
        @Query("password") password:String): Call<Boolean>


}