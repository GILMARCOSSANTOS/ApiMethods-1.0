package com.example.apimethods10.activity_post.controller

import android.util.Log
import com.example.apimethods10.activity_post.model.ModelPostGeneralData
import com.example.apimethods10.activity_post.service.PostResponseGeneralData
import com.example.apimethods10.activity_post.service.PostServiceGeneralData
import com.example.apimethods10.service.ApiConnection
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PostControllerGeneralData {

    fun fetchPost(userId: Int, title: String, body: String, postResponse: PostResponseGeneralData) {

        val modelPost = ModelPostGeneralData(userId, title, body)
        val postService = ApiConnection().createService(PostServiceGeneralData::class.java)

        if (modelPost != null) {
            postService.createPost(modelPost).enqueue(object : Callback<ModelPostGeneralData> {
                override fun onResponse(call: Call<ModelPostGeneralData>, response: Response<ModelPostGeneralData>) {
                    if (response.isSuccessful) {
                        postResponse.successPostResponse(response.body()!!)
                        val post = response.body()
                        println("Resposta da API 002" + post)
                        Log.d("API", "\"Resposta da API 003\" ${post?.title}")
                    } else {
                        val errorBody = response.errorBody()?.string()
                        Log.e("API", "Erro na criação do post: $errorBody")
                    }
                }

                override fun onFailure(call: Call<ModelPostGeneralData>, t: Throwable) {
                    Log.e("API", "Falha na comunicação com a API", t)
                }
            })
        }
    }
}