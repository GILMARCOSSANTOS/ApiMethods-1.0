package com.example.apimethods10.activity_post.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import com.example.apimethods10.R
import com.example.apimethods10.activity_post.controller.ControllerPostData
import com.example.apimethods10.activity_post.model.ModelPostData
import com.example.apimethods10.activity_post.service.ResponsePostData
import com.example.apimethods10.view.MainActivity
import com.google.android.material.button.MaterialButton
import com.google.android.material.textview.MaterialTextView

class ActivityPost : AppCompatActivity() {

    private lateinit var enterSubTitle: EditText
    private lateinit var enterText: EditText
    private lateinit var buttonSendData: MaterialButton
    private lateinit var fieldResponseSubTitle: MaterialTextView
    private lateinit var fieldResponseText: MaterialTextView
    private lateinit var buttonBack: MaterialButton
    private lateinit var progressBarLoading: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post)

        /* Functions: */
        globalVariablesScope()
        backActivity()
        buttonPostData()
    }

    private fun buttonPostData() {

        buttonSendData.setOnClickListener {
            if (buttonSendData.isClickable) {

                val title = enterSubTitle.text.toString()
                val body = enterText.text.toString()

                if ( title.isBlank() || body.isBlank()) {
                    Toast.makeText(this, "Por favor, preencha todos os campos.", Toast.LENGTH_SHORT)
                        .show()
                } else {
                    val postController = ControllerPostData()
                    postController.fetchPost(title, body, object : ResponsePostData {

                        override fun successPostResponse(post: ModelPostData) {

                            progressBarLoading.visibility = View.INVISIBLE

                            val responseSubTitle = post.title
                            fieldResponseSubTitle.text = "▬ Sub - Título: " + responseSubTitle

                            val responseText = post.body
                            fieldResponseText.text = "▬ Texto: " + responseText
                        }

                        override fun errorPostResponse(errorPost: String) {
                            progressBarLoading.visibility = View.VISIBLE
                        }
                    })
                }
            }
        }
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
        enterSubTitle = findViewById(R.id.edtTxt_subTitle_actvtPost_id)
        enterText = findViewById(R.id.edtTxt_text_actvtPost_id)
        buttonSendData = findViewById(R.id.bttn_postData_actvtPost_id)
        fieldResponseSubTitle = findViewById(R.id.txtVw_responseSubTitle_actvtPost_id)
        fieldResponseText = findViewById(R.id.txtVw_responseText_actvtPost_id)
        progressBarLoading = findViewById(R.id.prgrssBar_actvtPost_id)
    }
}