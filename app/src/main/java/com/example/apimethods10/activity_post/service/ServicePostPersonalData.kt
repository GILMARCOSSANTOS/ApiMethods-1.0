package com.example.apimethods10.activity_post.service

import com.example.apimethods10.activity_post.model.ModelPostPersonalData
import retrofit2.Call
import retrofit2.http.GET

interface ServicePostPersonalData {
    @GET("posts")
    fun getServicePersonalData(): Call<List<ModelPostPersonalData>>
}