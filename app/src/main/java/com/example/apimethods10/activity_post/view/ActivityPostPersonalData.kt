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
import com.example.apimethods10.activity_post.adapter.AdapterPostGeneralData_GeneralData
import com.example.apimethods10.activity_post.controller.ControllerPostGeneralData_GeneralData
import com.example.apimethods10.activity_post.controller.ControllerPostPersonalData_GeneralData
import com.example.apimethods10.activity_post.model.ModelPostApi
import com.example.apimethods10.activity_post.service.ResponsePostGeneralData_GeneralData
import com.example.apimethods10.activity_post.service.ResponsePostPersonalData_GeneralData
import com.example.apimethods10.view.MainActivity
import com.google.android.material.button.MaterialButton
import com.google.android.material.textview.MaterialTextView

class ActivityPostPersonalData : AppCompatActivity() {

    private lateinit var enterChapter: EditText
    private lateinit var enterSubTitle: EditText
    private lateinit var enterText: EditText
    private lateinit var buttonSendData: MaterialButton
    private lateinit var fieldResponseChapter: MaterialTextView
    private lateinit var fieldResponseSubTitle: MaterialTextView
    private lateinit var fieldResponseText: MaterialTextView
    private lateinit var buttonBack: MaterialButton
    private lateinit var progressBarLoading: ProgressBar
    private lateinit var recyclerViewPersonalData: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post_personal_data)

        /* Call Functions: */
        globalVariablesScope()
        backActivity()
        buttonPostData()

    }

    private fun buttonPostData() {

        buttonSendData.setOnClickListener {
            if (buttonSendData.isClickable) {

                val titleEditText = enterSubTitle.text.toString()
                val bodyEditText = enterText.text.toString()

                if (titleEditText.isBlank() || bodyEditText.isBlank()) {
                    Toast.makeText(this, "Por favor, preencha todos os campos.", Toast.LENGTH_SHORT)
                        .show()
                } else {
                    responsePostPersonalData()
                }
                settingRecyclerView()
            }
        }
    }

    private fun responsePostPersonalData() {
        val idEditText = enterChapter.text.toString()
        val titleEditText = enterSubTitle.text.toString()
        val bodyEditText = enterText.text.toString()

        val controllerPersonalData = ControllerPostPersonalData_GeneralData()
        controllerPersonalData.controllerPostPersonalData(
            idEditText.toInt(),
            titleEditText,
            bodyEditText,
            object : ResponsePostPersonalData_GeneralData {

                override fun successResponsePostPersonalData(successPostPersonalData: ModelPostApi) {
                    progressBarLoading.visibility = View.INVISIBLE

                    val newPersonalData = mutableListOf<ModelPostApi>()
//                    newPersonalData.add(personalData)
                    newPersonalData

                    // Buscar dados estáticos da API
                    val staticDataController = ControllerPostGeneralData_GeneralData()
                    staticDataController.controllerGeneralData_primary(object :
                        ResponsePostGeneralData_GeneralData {

                        override fun successResponseGeneralData(data: MutableList<ModelPostApi>) {
                            // Adicionar dados estáticos à lista
                           newPersonalData.addAll(data)

                            // Adicionar dados enviados à lista
                            val personalData = ModelPostApi(
                                id = successPostPersonalData.id,
                                title = successPostPersonalData.title,
                                body = successPostPersonalData.body
                            )
                            newPersonalData.add(personalData)

                            // Atualizar a RecyclerView com os dados
                            val adapter = AdapterPostGeneralData_GeneralData(
                                this@ActivityPostPersonalData,
                                newPersonalData
                            )
                           recyclerViewPersonalData.adapter = adapter
                        }

                        override fun errorResponseGeneralData(errorPersonalData: String) {
                            progressBarLoading.visibility = View.VISIBLE
                        }
                    })

                    fieldResponseChapter.text = "▬ CAPÍTULO = ${successPostPersonalData.id}"
                    fieldResponseSubTitle.text = "▬ TÍTULO: ${successPostPersonalData.title}"
                    fieldResponseText.text = "▬ PARÁGRAFO: ${successPostPersonalData.body}"
                }

                override fun errorResponsePostPersonalData(errorPostPersonalData: String) {
                    progressBarLoading.visibility = View.VISIBLE
                }
            })
    }

    private fun settingRecyclerView() {
        recyclerViewPersonalData.setHasFixedSize(true)
        recyclerViewPersonalData.layoutManager = LinearLayoutManager(
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
        buttonBack = findViewById(R.id.bttn_back_actvtPostPersonalData_id)
        enterChapter = findViewById(R.id.edtTxt_chapter_actvtPostPersonalData_id)
        enterSubTitle = findViewById(R.id.edtTxt_subTitle_actvtPostPersonalData_id)
        enterText = findViewById(R.id.edtTxt_text_actvtPostPersonalData_id)
        buttonSendData = findViewById(R.id.bttn_postData_actvtPostPersonalData_id)
        fieldResponseChapter = findViewById(R.id.txtVw_responseChapter_actvtPostPersonalData_id)
        fieldResponseSubTitle = findViewById(R.id.txtVw_responseSubTitle_actvtPostPersonalData_id)
        fieldResponseText = findViewById(R.id.txtVw_responseText_actvtPostPersonalData_id)
        progressBarLoading = findViewById(R.id.prgrssBar_actvtPostPersonalData_id)
        recyclerViewPersonalData = findViewById(R.id.rcclerVw_actvtPostPersonalData_id)
    }
}
