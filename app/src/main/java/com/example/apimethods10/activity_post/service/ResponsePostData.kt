package com.example.apimethods10.activity_post.service

import com.example.apimethods10.activity_post.model.ModelPostData

interface ResponsePostData {
    fun errorPostResponse(errorPost: String)
    fun successPostResponse(successPost: ModelPostData)
}