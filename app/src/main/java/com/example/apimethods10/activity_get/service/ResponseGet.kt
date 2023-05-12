package com.example.apiregisteruser_10.activity_see.response

import com.example.apiregisteruser_10.activity_see.model.ModelGet

interface ResponseGet {

    fun successResponse(response: List<ModelGet>)

    fun errorResponse(erro: String = "Erro de Resposta da API")
}

