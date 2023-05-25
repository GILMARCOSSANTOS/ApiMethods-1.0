package com.example.apimethods10.activity_post.service

import com.example.apimethods10.activity_post.model.ModelPostApi

interface ResponsePostPersonalData_GeneralData {
    fun errorPostResponse(errorPost: String)
    fun successPostResponse(successPost: ModelPostApi)
}