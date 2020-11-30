package com.example.alexl.bibliutez.model.carritos

import com.example.alexl.bibliutez.model.usuarios.UsuariosBean
import com.google.gson.annotations.SerializedName

class CarritosBean (
    val id: Int,
    @SerializedName("usuarios_id")
    val usuarios: UsuariosBean
)