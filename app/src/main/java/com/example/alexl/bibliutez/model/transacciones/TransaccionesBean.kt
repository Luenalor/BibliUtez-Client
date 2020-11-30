package com.example.alexl.bibliutez.model.transacciones

import com.example.alexl.bibliutez.model.clientes.ClientesBean
import com.google.gson.annotations.SerializedName

class TransaccionesBean(

    val id: Int,
    @SerializedName("cliente_id")
    val cliente: ClientesBean,
    val monto_total: Double,
    val fecha: String


)
