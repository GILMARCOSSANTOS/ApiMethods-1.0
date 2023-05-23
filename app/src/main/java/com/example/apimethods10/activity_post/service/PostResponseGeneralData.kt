package com.example.apimethods10.activity_post.service

import com.example.apimethods10.activity_post.model.ModelPostGeneralData

interface PostResponseGeneralData {
    fun errorPostResponse(errorPost: String)
    fun successPostResponse(successPost: ModelPostGeneralData)
}