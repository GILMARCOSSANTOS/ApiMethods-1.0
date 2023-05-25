package com.example.apimethods10.activity_post.service

import com.example.apimethods10.activity_post.model.ModelPostApi
import retrofit2.Call
import retrofit2.http.GET

interface ServicePostGeneralData_GeneralData {
    @GET("posts")
    fun getServicePersonalData(): Call<List<ModelPostApi>>
}