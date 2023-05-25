package com.example.apimethods10.activity_post.service

import com.example.apimethods10.activity_post.model.ModelPostApi
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ServicePostGeneralData_Primary {
    @POST("posts")
    fun createPost(@Body post: ModelPostApi): Call<ModelPostApi>
}