package com.example.apimethods10.activity_get.service

import com.example.apiregisteruser_10.activity_see.model.ModelGetText
import retrofit2.Call
import retrofit2.http.GET

interface UrlRelativeGetText {
    @GET("posts")
    fun urlRelativeGetPersonalData(): Call<List<ModelGetText>>
}