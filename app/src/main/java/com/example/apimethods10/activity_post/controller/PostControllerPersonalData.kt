package com.example.apimethods10.activity_post.controller

import android.content.ContentValues.TAG
import android.util.Log
import com.example.apimethods10.activity_post.model.ModelPostApi
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
        val call: Call<List<ModelPostApi>> = service.getServicePersonalData()

        call.enqueue(object : Callback<List<ModelPostApi>> {

            override fun onResponse(
                call: Call<List<ModelPostApi>>,
                response: Response<List<ModelPostApi>>
            ) {
                response.body()?.let {
                    val apiData = response.body()
                    returnResponse.successResponsePersonalData(it as MutableList<ModelPostApi>)
                    println("Resposta de Sucesso (PrintLn) PostControllerPersonalData = $response")
                    Log.d(TAG, "Resposta de Sucesso (Log.D) PostControllerPersonalData = $apiData")
                }
            }

            override fun onFailure(call: Call<List<ModelPostApi>>, t: Throwable) {
                println("Resposta de Erro (PrintLn) PostControllerPersonalData = ${t.message}")
            }
        })
    }
}