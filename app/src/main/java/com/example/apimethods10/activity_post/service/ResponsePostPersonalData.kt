package com.example.apimethods10.activity_post.service

import com.example.apimethods10.activity_post.model.ModelPostPersonalData

interface ResponsePostPersonalData {

    fun successResponsePersonalData(data: MutableList<ModelPostPersonalData>)
    fun errorResponsePersonalData(errorPersonalData: String = "Erro no Retorno de Dados do POST")

}