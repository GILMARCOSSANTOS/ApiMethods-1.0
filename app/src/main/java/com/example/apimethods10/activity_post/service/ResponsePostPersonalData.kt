package com.example.apimethods10.activity_post.service

import com.example.apimethods10.activity_post.model.ModelPostPersonalData

interface ResponsePostPersonalData {

    fun successPostPersonalData(sucessAPI: ModelPostPersonalData)
    fun errorPostPersonalData(errorAPI: String)
}