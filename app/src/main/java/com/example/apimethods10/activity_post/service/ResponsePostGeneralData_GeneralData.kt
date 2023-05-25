package com.example.apimethods10.activity_post.service

import com.example.apimethods10.activity_post.model.ModelPostApi

interface ResponsePostGeneralData_GeneralData {

    fun successResponseGeneralData(data: MutableList<ModelPostApi>)
    fun errorResponseGeneralData(errorGeneralData: String = "Erro no Retorno de Dados do POST")
}