package com.example.apimethods10.activity_post.controller

import android.util.Log
import com.example.apimethods10.activity_post.model.ModelPostApi
import com.example.apimethods10.activity_post.service.ResponsePostPersonalData_GeneralData
import com.example.apimethods10.activity_post.service.ServicePostPersonalData_GeneralData
import com.example.apimethods10.service.ApiConnection
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ControllerPostPersonalData_GeneralData {

    fun controllerPostPersonalData(id: Int, title: String, body: String, responsePostPersonalData: ResponsePostPersonalData_GeneralData) {

        val modelPersonalData = ModelPostApi(id, title, body)
        val postService = ApiConnection().createService(ServicePostPersonalData_GeneralData::class.java)

        if (modelPersonalData != null) {
            postService.servicePostPersonalData(modelPersonalData).enqueue(object : Callback<ModelPostApi> {
                override fun onResponse(call: Call<ModelPostApi>, response: Response<ModelPostApi>) {
                    if (response.isSuccessful) {
                        responsePostPersonalData.successResponsePostPersonalData(response.body()!!)
                        val post = response.body()
                        println("Resposta da API 002" + post)
                        Log.d("API", "\"Resposta da API 003\" ${post?.title}")
                    } else {
                        val errorBody = response.errorBody()?.string()
                        Log.d("API", "Erro na criação do post: $errorBody")
                    }
                }

                override fun onFailure(call: Call<ModelPostApi>, t: Throwable) {
                    Log.d("API", "Falha na comunicação com a API", t)
                }
            })
        }
    }
}