package com.example.alexl.bibliutez.model.transacciones

import com.example.alexl.bibliutez.model.clientes.ClientesBean
import com.example.alexl.bibliutez.model.libros.LibrosBean
import com.google.gson.annotations.SerializedName
import java.io.Serializable
import java.sql.Timestamp
import java.util.*

class TransaccionesBean(

    val id: Int,
    val cliente: ClientesBean,
    val libro: LibrosBean,
    val monto_total: Double


)
