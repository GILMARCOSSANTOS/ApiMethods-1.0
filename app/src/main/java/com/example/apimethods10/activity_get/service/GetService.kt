package com.example.apimethods10.activity_get.service

import com.example.apiregisteruser_10.activity_see.model.ModelGet
import retrofit2.Call
import retrofit2.http.GET

interface GetService {
    @GET("posts")
    fun getService(): Call<List<ModelGet>>
}