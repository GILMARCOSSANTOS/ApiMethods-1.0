package com.example.apimethods10.activity_post.service

import com.example.apimethods10.activity_post.model.ModelPost
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ServicePost {
    @GET("posts")
    suspend fun getPosts(): List<ModelPost>

    @POST("posts")
    suspend fun createPost(@Body post: ModelPost): ModelPost
}









