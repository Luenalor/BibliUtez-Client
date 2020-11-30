package com.example.alexl.bibliutez.model.usuarios

import com.example.alexl.bibliutez.model.roles.RolesBean
import com.google.gson.annotations.SerializedName

class UsuariosBean(

    val id: Int,
    val nombre: String,
    val apellido1: String,
    val apellido2: String,
    val email: String,
    val status: Boolean,
    val sexo: Char,
    @SerializedName("roles_id")
    val rol: RolesBean,
    val password: String

)
