package com.example.alexl.bibliutez.model.carritos_libros

import com.example.alexl.bibliutez.model.carritos.CarritosBean
import com.example.alexl.bibliutez.model.libros.LibrosBean
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class CarritosLibrosBean(

    var id: Int,
    @SerializedName("carritos_id")
    var carritos: CarritosBean,
    @SerializedName("libros_id")
    var libros: LibrosBean,
    var cantidad: Int

): Serializable
