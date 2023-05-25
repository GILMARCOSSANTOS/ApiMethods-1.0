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
    private lateinit var recyclerViewPrimary: RecyclerView
    private var listPersonalData: List<ModelPostApi>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post_general_data)

        /* Functions: */
        globalVariablesScope()
        backActivity()
        buttonPostData()
        settingRecyclerView()
//        goToActivity()
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
                  responsePostGeneralData()
                }
              responseGeneralData()
                settingRecyclerView()
            }
        }
    }

    private fun responsePostGeneralData() {
        val idEditText = enterChapter.text.toString()
        val titleEditText = enterSubTitle.text.toString()
        val bodyEditText = enterText.text.toString()

        val postController = ControllerPostPersonalData_GeneralData()
        postController.controllerPostGeneralData_Primaryt(idEditText.toInt(), titleEditText, bodyEditText, object : ResponsePostPersonalData_GeneralData {

            override fun successResponsePostPersonalData(successPostPersonalData: ModelPostApi) {
                progressBarLoading.visibility = View.INVISIBLE

                val newPersonalData = mutableListOf<ModelPostApi>()

                // Buscar dados estáticos da API
                val staticDataController = ControllerPostGeneralData_GeneralData()
                staticDataController.controllerGeneralData_primary(object : ResponsePostGeneralData_GeneralData {

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
                        val adapter = AdapterPostGeneralData_GeneralData(this@ActivityPostGeneralData, newPersonalData)
                        recyclerViewPrimary.adapter = adapter
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

    private fun responseGeneralData() {
        val responseController = ControllerPostGeneralData_GeneralData()
        responseController.controllerGeneralData_primary(object : ResponsePostGeneralData_GeneralData {

            override fun successResponseGeneralData(data: MutableList<ModelPostApi>) {
                progressBarLoading.visibility = View.INVISIBLE
                listPersonalData = data
                recyclerViewPrimary.adapter?.notifyDataSetChanged()
            }

            override fun errorResponseGeneralData(errorPersonalData: String) {
                progressBarLoading.visibility = View.VISIBLE
            }
        })
    }

    private fun settingRecyclerView() {
        recyclerViewPrimary.setHasFixedSize(true)
       recyclerViewPrimary.layoutManager = LinearLayoutManager(
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

//    private fun goToActivity() {
//
//        buttonGoToActivity.setOnClickListener {
//            if (buttonGoToActivity.isClickable) {
//                val intent = Intent(this, ActivityPostPersonalData::class.java).apply {
//                }
//                startActivity(intent)
//                finish()
//            }
//        }
//    }

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
        recyclerViewPrimary = findViewById(R.id.rcclerVw_actvtPostGeneralData_id)
    }
}
