package com.example.alexl.bibliutez.model.clientes

import com.example.alexl.bibliutez.model.usuarios.UsuariosBean
import com.google.gson.annotations.SerializedName
import java.util.*

class ClientesBean(

    val id: Int,
    val fecha_nacimiento: Date,
    val telefono: String,
    @SerializedName("usuarios_id")
    val usuarios: UsuariosBean,
    val domicilio: String


)