package com.example.apiregisteruser_10.activity_see.controller

import android.content.ContentValues.TAG
import android.util.Log
import com.example.apimethods10.service.ApiConnection
import com.example.apiregisteruser_10.activity_see.model.ModelGet
import com.example.apiregisteruser_10.activity_see.response.ResponseGet
import com.example.apimethods10.activity_get.service.UrlRelativeGet
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ControllerGet() {

    private val apiConnection = ApiConnection()

    fun controllergetPersonalData(returnResponse: ResponseGet) {
        val service = apiConnection.createService(UrlRelativeGet::class.java)
        val call: Call<List<ModelGet>> = service.urlRelativeGetPersonalData()

        call.enqueue(object : Callback<List<ModelGet>>{
            override fun onResponse(
                call: Call<List<ModelGet>>,
                response: Response<List<ModelGet>>
            ) {
                response.body()?.let {
                    val dados = response.body()
                    returnResponse.successResponse(it)
                    println("Response API GetPersonalData = $response")
                    Log.d(TAG, "Resposta da API: $dados")
                }
            }

            override fun onFailure(call: Call<List<ModelGet>>, t: Throwable) {
                println("Erro API GetPersonalData = ${t.message}")
            }
        })
    }
}