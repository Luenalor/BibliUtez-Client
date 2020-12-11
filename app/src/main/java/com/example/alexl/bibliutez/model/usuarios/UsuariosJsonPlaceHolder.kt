package com.example.alexl.bibliutez.model.usuarios

import retrofit2.Call
import retrofit2.http.*

interface UsuariosJsonPlaceHolder {


    @GET("findAll")
    fun usuariosFindAll(): Call<List<UsuariosBean>>

    @POST("add")
    fun usuariosAdd(@Body usuario:UsuariosBean ): Call<Int>

    @PUT("update")
    fun usuariosUpdate(@Body usuario: UsuariosBean): Call<Boolean>

    @DELETE("delete/{id}")
    fun usuariosDelete(@Path("id") id:Int): Call<Boolean>

    @PUT("updatePassword")
    fun updatePassword(@Body usuario: UsuariosBean): Call<Boolean>


}