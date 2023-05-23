package com.example.apimethods10.activity_post.service

import com.example.apimethods10.activity_post.model.ModelPostGeneralData
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface PostServiceGeneralData {
    @POST("posts")
    fun createPost(@Body post: ModelPostGeneralData): Call<ModelPostGeneralData>
}