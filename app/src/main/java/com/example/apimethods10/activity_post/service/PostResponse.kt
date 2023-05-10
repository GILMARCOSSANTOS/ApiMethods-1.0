package com.example.apimethods10.activity_post.service

import com.example.apimethods10.activity_post.model.ModelPost

interface PostResponse {
    fun errorPostResponse(errorPost: String)
    fun successPostResponse(successPost: ModelPost)
}