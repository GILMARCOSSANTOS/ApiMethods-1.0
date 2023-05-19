package com.example.apimethods10.activity_post.controller

import android.util.Log
import com.example.apimethods10.activity_post.model.ModelPost
import com.example.apimethods10.activity_post.service.ResponsePost
import com.example.apimethods10.activity_post.service.ServicePost
import com.example.apimethods10.service.ApiConnection


class ControllerPost {
    suspend fun fetchPost(
        title: String,
        body: String
    ): List<ModelPost> {
        val modelPost = ModelPost(title, body)
        val postService = ApiConnection().createService(ServicePost::class.java)

        try {
            val response = postService.createPost(modelPost)
            if (response != null) {
                val post = response
                val resultList = mutableListOf(post)
                return resultList
            } else {
                throw Exception("Erro ao obter os dados.")
            }
        } catch (e: Exception) {
            throw Exception("Falha na comunicação com a API.")
        }
    }
}
