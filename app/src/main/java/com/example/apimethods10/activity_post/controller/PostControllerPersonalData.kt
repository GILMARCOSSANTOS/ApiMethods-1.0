package com.example.apimethods10.activity_post.controller

import android.content.ContentValues.TAG
import android.util.Log
import com.example.apimethods10.activity_post.model.ModelPostPersonalData
import com.example.apimethods10.activity_post.service.ResponsePostPersonalData
import com.example.apimethods10.activity_post.service.ServicePostPersonalData
import com.example.apimethods10.service.ApiConnection
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PostControllerPersonalData() {

    private val apiConnection = ApiConnection()

    fun controllerPersonalData(returnResponse: ResponsePostPersonalData) {

        val service = apiConnection.createService(ServicePostPersonalData::class.java)
        val call: Call<List<ModelPostPersonalData>> = service.getServicePersonalData()

        call.enqueue(object : Callback<List<ModelPostPersonalData>> {

            override fun onResponse(
                call: Call<List<ModelPostPersonalData>>,
                response: Response<List<ModelPostPersonalData>>
            ) {
                response.body()?.let {
                    val apiData = response.body()
                    returnResponse.successResponsePersonalData(it as MutableList<ModelPostPersonalData>)
                    println("Resposta de Sucesso (PrintLn) PostControllerPersonalData = $response")
                    Log.d(TAG, "Resposta de Sucesso (Log.D) PostControllerPersonalData = $apiData")
                }
            }

            override fun onFailure(call: Call<List<ModelPostPersonalData>>, t: Throwable) {
                println("Resposta de Erro (PrintLn) PostControllerPersonalData = ${t.message}")
            }
        })
    }
}