package com.example.apimethods10.activity_post.service

import com.example.apimethods10.activity_post.model.ModelPostApi

interface ResponsePostGeneralData_GeneralData {

    fun successResponsePersonalData(data: MutableList<ModelPostApi>)
    fun errorResponsePersonalData(errorPersonalData: String = "Erro no Retorno de Dados do POST")

}