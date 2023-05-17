package com.example.apimethods10.activity_post.controller

import android.util.Log
import com.example.apimethods10.activity_post.model.ModelPostData
import com.example.apimethods10.activity_post.service.PostServiceData
import com.example.apimethods10.activity_post.service.ResponsePostData
import com.example.apimethods10.service.ApiConnection
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ControllerPostData {

    fun fetchPost(title: String, body: String, postResponse: ResponsePostData) {

        val modelPost = ModelPostData(title, body)
        val postService = ApiConnection().createService(PostServiceData::class.java)

        if (modelPost != null) {
            postService.createPostData(modelPost).enqueue(object : Callback<ModelPostData> {
                override fun onResponse(call: Call<ModelPostData>, response: Response<ModelPostData>) {
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

                override fun onFailure(call: Call<ModelPostData>, t: Throwable) {
                    Log.e("API", "Falha na comunicação com a API", t)
                }
            })
        }
    }
}