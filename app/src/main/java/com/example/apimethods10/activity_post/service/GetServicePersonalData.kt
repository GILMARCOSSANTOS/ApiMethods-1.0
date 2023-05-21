package com.example.apimethods10.activity_post.service

import com.example.apimethods10.activity_post.model.ModelGetPersonalData
import retrofit2.Call
import retrofit2.http.GET

interface GetServicePersonalData {
    @GET("posts")
    fun getServicePersonalData(): Call<List<ModelGetPersonalData>>
}