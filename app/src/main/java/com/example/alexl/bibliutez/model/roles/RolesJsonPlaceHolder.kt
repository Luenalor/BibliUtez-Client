package com.example.alexl.bibliutez.model.roles

import retrofit2.Call
import retrofit2.http.*

interface RolesJsonPlaceHolder {


    @GET("findAll")
    fun rolesFindAll(): Call<List<RolesBean>>

    @GET("findOne")
    fun rolesFindOne(@Query("id") id: Int): Call<RolesBean>

    @POST("add")
    fun rolesAdd(@Body roles: RolesBean): Call<Int>

    @PUT("update")
    fun rolesUpdate(@Body roles: RolesBean): Call<Boolean>

    @DELETE("delete/{id}")
    fun rolesDelete(@Path("id") id:Int): Call<Boolean>

}