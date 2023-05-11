package com.example.apimethods10.activity_post.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.Toast
import com.example.apimethods10.R
import com.example.apimethods10.activity_post.controller.PostController
import com.example.apimethods10.activity_post.model.ModelPost
import com.example.apimethods10.activity_post.service.PostResponse
import com.example.apimethods10.activity_post.service.PostService
import com.example.apimethods10.service.ApiConnection
import com.example.apimethods10.view.MainActivity
import com.google.android.material.button.MaterialButton
import com.google.android.material.textview.MaterialTextView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ActivityPost : AppCompatActivity() {

    private lateinit var enterChapter: EditText
    private lateinit var enterSubTitle: EditText
    private lateinit var enterText: EditText
    private lateinit var buttonSendData: MaterialButton
    private lateinit var fieldResponse: MaterialTextView
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
        buttonBack = findViewById(R.id.button_back_actvtPost_id)
        enterChapter = findViewById(R.id.edtTxt_chapter_actvtPost_id)
        enterSubTitle = findViewById(R.id.edtTxt_subTitle_actvtPost_id)
        enterText = findViewById(R.id.edtTxt_text_actvtPost_id)
        buttonSendData = findViewById(R.id.bttn_postData_actvtPost_id)
        fieldResponse = findViewById(R.id.txtVw_response_actvtPost_id)
    }
}