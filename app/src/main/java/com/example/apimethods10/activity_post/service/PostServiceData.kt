package com.example.apimethods10.activity_post.service

import com.example.apimethods10.activity_post.model.ModelPostData
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface PostServiceData {
    @POST("posts")
    fun createPostData(@Body post: ModelPostData): Call<ModelPostData>
}