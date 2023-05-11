package com.example.apimethods10.activity_post.service

import com.example.apimethods10.activity_post.model.ModelPost
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface PostService {
    @POST("posts")
    fun createPost(@Body post: ModelPost): Call<ModelPost>
}