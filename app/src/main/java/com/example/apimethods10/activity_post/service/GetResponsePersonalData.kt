package com.example.apimethods10.activity_post.service

import com.example.apimethods10.activity_post.model.ModelGetPersonalData
import com.example.apimethods10.activity_post.model.ModelPost

interface GetResponsePersonalData {

    fun successResponsePersonalData(data: MutableList<ModelGetPersonalData>)
    fun errorResponsePersonalData(errorPersonalData: String = "Erro no Retorno de Dados do POST")

}