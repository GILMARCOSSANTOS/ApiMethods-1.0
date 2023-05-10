package com.example.apiregisteruser_10.activity_see.response

import com.example.apiregisteruser_10.activity_see.model.ModelGetText

interface ResponseGetText {

    fun successResponse(response: List<ModelGetText>)

    fun errorResponse(erro: String = "Erro de Resposta da API")
}

