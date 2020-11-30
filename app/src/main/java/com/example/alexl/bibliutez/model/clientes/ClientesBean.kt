package com.example.alexl.bibliutez.model.clientes

import com.example.alexl.bibliutez.model.usuarios.UsuariosBean
import com.google.gson.annotations.SerializedName

class ClientesBean(

    val id: Int,
    val fecha_nacimiento: String,
    val telefono: String,
    @SerializedName("usuarios_id")
    val usuarios: UsuariosBean,
    val domicilio: String


)