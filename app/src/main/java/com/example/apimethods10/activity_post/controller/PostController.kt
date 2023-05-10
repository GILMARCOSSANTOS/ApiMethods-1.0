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

    fun fetchPost(modelPost: ModelPost, postResponse: PostResponse) {

        val postService = ApiConnection().createService(PostService::class.java)

        postService.createPost(modelPost).enqueue(object : Callback<ModelPost> {
            override fun onResponse(call: Call<ModelPost>, response: Response<ModelPost>) {
                if (response.isSuccessful) {
                    // A requisição foi bem sucedida (código de status entre 200 e 299)
                    postResponse.successPostResponse(response.body()!!)
                    val post = response.body()
                    println("Resposta da API 002" + post)
                    Log.d("API", "\"Resposta da API 003\" ${post?.title}")
                } else {
                    // A requisição não foi bem sucedida (código de status fora do intervalo 200-299)
                    val errorBody = response.errorBody()?.string()
                    Log.e("API", "Erro na criação do post: $errorBody")
                }
            }

            override fun onFailure(call: Call<ModelPost>, t: Throwable) {
                // Tratar falha na comunicação com a API aqui
                Log.e("API", "Falha na comunicação com a API", t)
            }
        })
    }
}