package com.example.apimethods10.activity_post.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.apimethods10.R
import com.example.apimethods10.activity_post.adapter.GetAdapterPersonalData
import com.example.apimethods10.activity_post.controller.GetControllerPersonalData
import com.example.apimethods10.activity_post.controller.PostController
import com.example.apimethods10.activity_post.model.ModelGetPersonalData
import com.example.apimethods10.activity_post.model.ModelPost
import com.example.apimethods10.activity_post.service.GetResponsePersonalData
import com.example.apimethods10.activity_post.service.PostResponse
import com.example.apimethods10.view.MainActivity
import com.google.android.material.button.MaterialButton
import com.google.android.material.textview.MaterialTextView
import retrofit2.Callback
import retrofit2.Response

class ActivityPost : AppCompatActivity() {

    private lateinit var enterChapter: EditText
    private lateinit var enterSubTitle: EditText
    private lateinit var enterText: EditText
    private lateinit var buttonSendData: MaterialButton
    private lateinit var fieldResponseChapter: MaterialTextView
    private lateinit var fieldResponseSubTitle: MaterialTextView
    private lateinit var fieldResponseText: MaterialTextView
    private lateinit var buttonBack: MaterialButton
    private lateinit var progressBarLoading: ProgressBar
    private lateinit var recyclerViewGetPersonalData: RecyclerView
    private var listPersonalData: List<ModelGetPersonalData>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post)

        /* Functions: */
        globalVariablesScope()
        backActivity()
        buttonPostData()
        settingRecyclerView()
        responseControllerPersonalData()
    }

    private fun buttonPostData() {

        buttonSendData.setOnClickListener {
            if (buttonSendData.isClickable) {

                val title = enterSubTitle.text.toString()
                val body = enterText.text.toString()

                if (title.isBlank() || body.isBlank()) {
                    Toast.makeText(this, "Por favor, preencha todos os campos.", Toast.LENGTH_SHORT)
                        .show()
                } else {
                    responseControllerPost()
                }
                responseControllerPersonalData()
                settingRecyclerView()
            }
        }
    }

    private fun responseControllerPost() {
        val userId = enterChapter.text.toString()
        val title = enterSubTitle.text.toString()
        val body = enterText.text.toString()

        val postController = PostController()
        postController.fetchPost(userId.toInt(), title, body, object : PostResponse {

            override fun successPostResponse(post: ModelPost) {
                progressBarLoading.visibility = View.INVISIBLE

                val newPersonalData = mutableListOf<ModelGetPersonalData>()

                // Buscar dados estáticos da API
                val staticDataController = GetControllerPersonalData()
                staticDataController.controllerGetPersonalData(object : GetResponsePersonalData {
                    override fun successResponsePersonalData(data: MutableList<ModelGetPersonalData>) {
                        // Adicionar dados estáticos à lista
                        newPersonalData.addAll(data)

                        // Adicionar dados enviados à lista
                        val personalData = ModelGetPersonalData(
                            userId = post.userId,
                            title = post.title,
                            body = post.body
                        )
                        newPersonalData.add(personalData)

                        // Atualizar a RecyclerView com os dados
                        val adapter = GetAdapterPersonalData(this@ActivityPost, newPersonalData)
                        recyclerViewGetPersonalData.adapter = adapter
                    }

                    override fun errorResponsePersonalData(errorPersonalData: String) {
                        progressBarLoading.visibility = View.VISIBLE
                    }
                })

                fieldResponseChapter.text = "▬ Capítulo = ${post.userId}"
                fieldResponseSubTitle.text = "▬ Sub - Título: ${post.title}"
                fieldResponseText.text = "▬ Texto: ${post.body}"
            }

            override fun errorPostResponse(errorPost: String) {
                progressBarLoading.visibility = View.VISIBLE
            }
        })
    }

    private fun responseControllerPersonalData() {
        val responseController = GetControllerPersonalData()
        responseController.controllerGetPersonalData(object : GetResponsePersonalData {

            override fun successResponsePersonalData(data: MutableList<ModelGetPersonalData>) {
                progressBarLoading.visibility = View.INVISIBLE
                listPersonalData = data
                recyclerViewGetPersonalData.adapter?.notifyDataSetChanged()
            }

            override fun errorResponsePersonalData(errorPersonalData: String) {
                progressBarLoading.visibility = View.VISIBLE
            }
        })
    }

    private fun settingRecyclerView() {
        recyclerViewGetPersonalData.setHasFixedSize(true)
        recyclerViewGetPersonalData.layoutManager = LinearLayoutManager(
            this, LinearLayoutManager.VERTICAL, false
        )
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
        fieldResponseChapter = findViewById(R.id.txtVw_responseChapter_actvtPost_id)
        fieldResponseSubTitle = findViewById(R.id.txtVw_responseSubTitle_actvtPost_id)
        fieldResponseText = findViewById(R.id.txtVw_responseText_actvtPost_id)
        progressBarLoading = findViewById(R.id.prgrssBar_actvtPost_id)
        recyclerViewGetPersonalData = findViewById(R.id.rcclerVw_actvtPost_id)
    }
}




