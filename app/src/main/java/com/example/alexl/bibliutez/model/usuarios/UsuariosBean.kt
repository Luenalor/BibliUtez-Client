package com.example.alexl.bibliutez.model.usuarios

import com.example.alexl.bibliutez.model.roles.RolesBean
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class UsuariosBean(

    var id: Int,
    val nombre: String,
    val apellido1: String,
    val apellido2: String,
    val email: String,
    val estatus: Int,
    val sexo: String,
    @SerializedName("rolesid")
    val rol: RolesBean,
    val password: String

): Serializable
