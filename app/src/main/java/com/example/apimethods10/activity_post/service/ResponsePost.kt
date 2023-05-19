package com.example.apimethods10.activity_post.service

import com.example.apimethods10.activity_post.model.ModelPost


interface ResponsePost {
    fun successPostResponse(post: ModelPost)
    fun errorPostResponse(error: String)
}


