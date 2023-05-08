package com.example.apiregisteruser_10.activity_see.controller

import android.content.ContentValues.TAG
import android.util.Log
import com.example.apimethods10.service.ApiConnection
import com.example.apiregisteruser_10.activity_see.model.ModelGetText
import com.example.apiregisteruser_10.activity_see.response.ResponseGetText
import com.example.apiregisteruser_10.activity_see.url_relative.UrlRelativeGetText
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ControllerGetText() {

    private val apiConnection = ApiConnection()

    fun controllergetPersonalData(returnReponse: ResponseGetText) {
        val service = apiConnection.createService(UrlRelativeGetText::class.java)
        val call: Call<List<ModelGetText>> = service.urlRelativeGetPersonalData()

        call.enqueue(object : Callback<List<ModelGetText>>{
            override fun onResponse(
                call: Call<List<ModelGetText>>,
                response: Response<List<ModelGetText>>
            ) {
                response.body()?.let {
                    val dados = response.body()
                    returnReponse.successResponse(it)
                    println("Response API GetPersonalData = $response")
                    Log.d(TAG, "Resposta da API: $dados")
                }
            }

            override fun onFailure(call: Call<List<ModelGetText>>, t: Throwable) {
                println("Erro API GetPersonalData = ${t.message}")
            }
        })
    }
}