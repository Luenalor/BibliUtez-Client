package com.example.alexl.bibliutez.model.transacciones

import com.example.alexl.bibliutez.model.roles.RolesBean
import retrofit2.Call
import retrofit2.http.*

interface TransaccionesJsonPlaceHolder {

    @GET("findAll")
    fun transaccionesFindAll(): Call<List<TransaccionesBean>>

    @GET("findHistory")
    fun findHistory(@Query("id") id: Int): Call<ArrayList<TransaccionesBean>>

    @POST("add")
    fun transaccionesAdd(@Body usuario: TransaccionesBean): Call<Int>

    @PUT("update")
    fun transaccionesUpdate(@Body usuario: TransaccionesBean): Call<Boolean>

    @DELETE("delete/{id}")
    fun transaccionesDelete(@Path("id") id:Int): Call<Boolean>
}