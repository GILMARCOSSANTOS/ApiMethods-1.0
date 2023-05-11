package com.example.apimethods10.activity_post.controller

import android.util.Log
import com.example.apimethods10.activity_post.model.ModelPost
import com.example.apimethods10.activity_post.service.PostResponse
import com.example.apimethods10.activity_post.service.PostService
import com.example.apimethods10.service.ApiConnection
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PostController {

    fun fetchPost(userId: Int, title: String, body: String, postResponse: PostResponse) {

        val modelPost = ModelPost(userId, title, body)
        val postService = ApiConnection().createService(PostService::class.java)

        if (modelPost != null) {
            postService.createPost(modelPost).enqueue(object : Callback<ModelPost> {
                override fun onResponse(call: Call<ModelPost>, response: Response<ModelPost>) {
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

                override fun onFailure(call: Call<ModelPost>, t: Throwable) {
                    Log.e("API", "Falha na comunicação com a API", t)
                }
            })
        }
    }
}