package com.example.apimethods10.activity_post.controller

import android.content.ContentValues.TAG
import android.util.Log
import com.example.apimethods10.activity_post.model.ModelGetPersonalData
import com.example.apimethods10.activity_post.model.ModelPost
import com.example.apimethods10.activity_post.service.GetResponsePersonalData
import com.example.apimethods10.activity_post.service.GetServicePersonalData
import com.example.apimethods10.service.ApiConnection
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GetControllerPersonalData() {

    private val apiConnection = ApiConnection()

    fun controllerGetPersonalData(returnResponse: GetResponsePersonalData) {

        val service = apiConnection.createService(GetServicePersonalData::class.java)
        val call: Call<List<ModelGetPersonalData>> = service.getServicePersonalData()

        call.enqueue(object : Callback<List<ModelGetPersonalData>> {

            override fun onResponse(
                call: Call<List<ModelGetPersonalData>>,
                response: Response<List<ModelGetPersonalData>>
            ) {
                response.body()?.let {
                    val apiData = response.body()
                    returnResponse.successResponsePersonalData(it)
                    println("Resposta de Sucesso (PrintLn) GetControllerPersonalData = $response")
                    Log.d(TAG, "Resposta de Sucesso (Log.D) GetControllerPersonalData = $apiData")
                }
            }

            override fun onFailure(call: Call<List<ModelGetPersonalData>>, t: Throwable) {
                println("Resposta de Erro (PrintLn) GetControllerPersonalData = ${t.message}")
            }
        })
    }
}