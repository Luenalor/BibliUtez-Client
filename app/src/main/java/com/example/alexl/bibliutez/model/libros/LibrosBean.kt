package com.example.alexl.bibliutez.model.libros

import com.example.alexl.bibliutez.model.categorias.CategoriasBean
import com.google.gson.annotations.SerializedName
import java.io.Serializable


class LibrosBean(

    var id: Int,
    var nombre: String,
    @SerializedName("editoriales_id")
    var editorial: String,
    @SerializedName("autores_id")
    var autores: String,
    @SerializedName("categorias_id")
    var categoria: CategoriasBean,
    var precio: Double,
    var num_pag: Int,
    var stock: Int


)