package com.example.alexl.bibliutez.model.transacciones_detalles

import com.example.alexl.bibliutez.model.libros.LibrosBean
import com.example.alexl.bibliutez.model.transacciones.TransaccionesBean
import com.google.gson.annotations.SerializedName

class TransaccionesDetallesBean(

    val id: Int,
    @SerializedName("transacciones_id")
    val transacciones: TransaccionesBean,
    @SerializedName("libros_id")
    val libros: LibrosBean,
    val monto: Double

)