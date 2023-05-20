package com.example.apimethods10.activity_post.service

import com.example.apimethods10.activity_post.model.ModelGetPersonalData
import com.example.apimethods10.activity_post.model.ModelPost

interface GetResponsePersonalData {

    fun successResponsePersonalData(responsePersonalData: List<ModelGetPersonalData>)

    fun errorResponsePersonalData(errorPersonalData: String = "Erro no Retorno de Dados do POST")

}