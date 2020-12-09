package com.example.alexl.bibliutez.model.clientes

import com.example.alexl.bibliutez.model.usuarios.UsuariosBean
import com.google.gson.annotations.SerializedName
import java.io.Serializable
import java.util.*

class ClientesBean(

    val id: Int,
    val fecha_nacimiento: String,
    val telefono: String,
    @SerializedName("usuariosBean")
    val usuarios: UsuariosBean,
    val domicilio: String


): Serializable