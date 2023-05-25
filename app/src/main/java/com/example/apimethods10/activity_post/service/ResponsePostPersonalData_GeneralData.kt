package com.example.apimethods10.activity_post.service

import com.example.apimethods10.activity_post.model.ModelPostApi

interface ResponsePostPersonalData_GeneralData {

    fun successResponsePostPersonalData(successPostPersonalData: ModelPostApi)
    fun errorResponsePostPersonalData(errorPostPersonalData: String)

}