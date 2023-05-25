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
import com.example.apimethods10.activity_post.adapter.AdapterPostGeneralData
import com.example.apimethods10.activity_post.controller.PostControllerPersonalData
import com.example.apimethods10.activity_post.controller.PostControllerGeneralData
import com.example.apimethods10.activity_post.model.ModelPostPersonalData
import com.example.apimethods10.activity_post.model.ModelPostGeneralData
import com.example.apimethods10.activity_post.service.ResponsePostPersonalData
import com.example.apimethods10.activity_post.service.PostResponseGeneralData
import com.example.apimethods10.view.MainActivity
import com.google.android.material.button.MaterialButton
import com.google.android.material.textview.MaterialTextView

class ActivityPostGeneralData : AppCompatActivity() {

    private lateinit var buttonGoToActivity: MaterialButton
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
    private var listPersonalData: List<ModelPostPersonalData>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post_general_data)

        /* Functions: */
        globalVariablesScope()
        backActivity()
        buttonPostData()
        settingRecyclerView()
        responseControllerPersonalData()
        goToActivity()
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

        val postController = PostControllerGeneralData()
        postController.fetchPost(userId.toInt(), title, body, object : PostResponseGeneralData {

            override fun successPostResponse(post: ModelPostGeneralData) {
                progressBarLoading.visibility = View.INVISIBLE

                val newPersonalData = mutableListOf<ModelPostPersonalData>()

                // Buscar dados estáticos da API
                val staticDataController = PostControllerPersonalData()
                staticDataController.controllerPersonalData(object : ResponsePostPersonalData {
                    override fun successResponsePersonalData(data: MutableList<ModelPostPersonalData>) {
                        // Adicionar dados estáticos à lista
                        newPersonalData.addAll(data)

                        // Adicionar dados enviados à lista
                        val personalData = ModelPostPersonalData(
                            id = post.id,
                            title = post.title,
                            body = post.body
                        )
                        newPersonalData.add(personalData)

                        // Atualizar a RecyclerView com os dados
                        val adapter = AdapterPostGeneralData(this@ActivityPostGeneralData, newPersonalData)
                        recyclerViewGetPersonalData.adapter = adapter
                    }

                    override fun errorResponsePersonalData(errorPersonalData: String) {
                        progressBarLoading.visibility = View.VISIBLE
                    }
                })

                fieldResponseChapter.text = "▬ CAPÍTULO = ${post.id}"
                fieldResponseSubTitle.text = "▬ TÍTULO: ${post.title}"
                fieldResponseText.text = "▬ PARÁGRAFO: ${post.body}"
            }

            override fun errorPostResponse(errorPost: String) {
                progressBarLoading.visibility = View.VISIBLE
            }
        })
    }

    private fun responseControllerPersonalData() {
        val responseController = PostControllerPersonalData()
        responseController.controllerPersonalData(object : ResponsePostPersonalData {

            override fun successResponsePersonalData(data: MutableList<ModelPostPersonalData>) {
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

    private fun goToActivity() {

        buttonGoToActivity.setOnClickListener {
            if (buttonGoToActivity.isClickable) {
                val intent = Intent(this, ActivityPostPersonalData::class.java).apply {
                }
                startActivity(intent)
                finish()
            }
        }
    }

    private fun globalVariablesScope() {
        buttonGoToActivity = findViewById(R.id.bttn_goTo_actvtPostGeneralData_id)
        buttonBack = findViewById(R.id.bttn_back_actvtPostGeneralData_id)
        enterChapter = findViewById(R.id.edtTxt_chapter_actvtPostGeneralData_id)
        enterSubTitle = findViewById(R.id.edtTxt_subTitle_actvtPostGeneralData_id)
        enterText = findViewById(R.id.edtTxt_text_actvtPostGeneralData_id)
        buttonSendData = findViewById(R.id.bttn_postData_actvtPostGeneralData_id)
        fieldResponseChapter = findViewById(R.id.txtVw_responseChapter_actvtPostGeneralData_id)
        fieldResponseSubTitle = findViewById(R.id.txtVw_responseSubTitle_actvtPostGeneralData_id)
        fieldResponseText = findViewById(R.id.txtVw_responseText_actvtPostGeneralData_id)
        progressBarLoading = findViewById(R.id.prgrssBar_actvtPostGeneralData_id)
        recyclerViewGetPersonalData = findViewById(R.id.rcclerVw_actvtPostGeneralData_id)
    }

    /*sd*/
}
