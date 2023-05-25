package com.example.apimethods10.activity_post.service

import com.example.apimethods10.activity_post.model.ModelPostApi
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ServicePostPersonalData_GeneralData {
    @POST("posts")
    fun servicePostPersonalData(@Body post: ModelPostApi): Call<ModelPostApi>
}