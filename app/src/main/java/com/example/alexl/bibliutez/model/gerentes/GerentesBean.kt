package com.example.alexl.bibliutez.model.gerentes

import com.example.alexl.bibliutez.model.usuarios.UsuariosBean
import com.google.gson.annotations.SerializedName

class GerentesBean(
    var id: Int,
    @SerializedName("usuarios_id")
    var usuarios: UsuariosBean
)
