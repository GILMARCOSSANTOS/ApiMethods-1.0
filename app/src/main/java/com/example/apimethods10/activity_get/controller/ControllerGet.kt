package com.example.apiregisteruser_10.activity_see.controller

import android.content.ContentValues.TAG
import android.util.Log
import com.example.apimethods10.service.ApiConnection
import com.example.apiregisteruser_10.activity_see.model.ModelGet
import com.example.apiregisteruser_10.activity_see.response.ResponseGet
import com.example.apimethods10.activity_get.service.GetService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ControllerGet() {

    private val apiConnection = ApiConnection()

    fun controllerget(returnReponse: ResponseGet) {
        val service = apiConnection.createService(GetService::class.java)
        val call: Call<List<ModelGet>> = service.getService()

        call.enqueue(object : Callback<List<ModelGet>>{
            override fun onResponse(
                call: Call<List<ModelGet>>,
                response: Response<List<ModelGet>>
            ) {
                response.body()?.let {
                    val dados = response.body()
                    returnReponse.successResponse(it)
                    println("Resposta de Sucesso (PrintLn) ControllerGET = $response")
                    Log.d(TAG, "Resposta de Sucesso ControllerGET: $dados")
                }
            }

            override fun onFailure(call: Call<List<ModelGet>>, t: Throwable) {
                println("Resposta de Erro ControllerGet = ${t.message}")
            }
        })
    }
}