package com.example.apimethods10.activity_post.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.apimethods10.R
import com.example.apimethods10.activity_post.controller.PostController
import com.example.apimethods10.activity_post.model.ModelPost
import com.example.apimethods10.activity_post.service.PostResponse
import com.example.apimethods10.activity_post.service.PostService
import com.example.apimethods10.service.ApiConnection
import com.example.apimethods10.view.MainActivity
import com.google.android.material.button.MaterialButton
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ActivityPost : AppCompatActivity() {

    private lateinit var buttonBack: MaterialButton
    private val postController = PostController()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post)

        /* Functions: */
        globalVariablesScope()
        backActivity()
        handlePost()
    }

    private fun handlePost() {

        val modelPost = ModelPost(1, 0, "Título do Post", "xxxConteúdo do Post")
        val postResponse = object : PostResponse {

            override fun successPostResponse(modelPost: ModelPost) {
                println("Resposta da API 001" + modelPost.body)


            }

            override fun errorPostResponse(errorPost: String) {
                TODO("Not yet implemented")
            }
        }
        postController.fetchPost(modelPost, postResponse)
    }

    private fun backActivity() {

        buttonBack.setOnClickListener {
            if (buttonBack.isClickable) {
                val intent = Intent(this, MainActivity::class.java).apply {
                }
                startActivity(intent)
                finish()
            }
        }
    }

    private fun globalVariablesScope() {
        buttonBack = findViewById(R.id.button_back_actvtRegister_id)
    }
}