package com.example.alexl.bibliutez.model.gerentes

import retrofit2.Call
import retrofit2.http.*

interface GerentesJsonPlaceHolder {

    @GET("findAll")
    fun gerentesFindAll(): Call<List<GerentesBean>>

    @POST("add")
    fun gerentesAdd(@Body usuario: GerentesBean): Call<Int>

    @PUT("update")
    fun gerentesUpdate(@Body usuario: GerentesBean): Call<Boolean>

    @DELETE("delete/{id}")
    fun gerentesDelete(@Path("id") id:Int): Call<Boolean>

}