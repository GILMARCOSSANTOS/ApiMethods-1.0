package com.example.apimethods10.activity_post.service

import com.example.apimethods10.activity_post.model.ModelPostPersonalData
import retrofit2.http.GET

interface PostServicePersonalData {

    @GET("posts")
    suspend fun createPostPersonalData(modelPostPersonalData: ModelPostPersonalData): List<ModelPostPersonalData>

}