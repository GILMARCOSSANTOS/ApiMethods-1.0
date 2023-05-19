package com.example.apimethods10.activity_post.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.apimethods10.R
import com.example.apimethods10.activity_post.adapter.AdapterPost
import com.example.apimethods10.activity_post.controller.ControllerPost
import com.example.apimethods10.activity_post.model.ModelPost
import com.example.apimethods10.activity_post.service.ResponsePost
import com.example.apimethods10.view.MainActivity
import com.google.android.material.button.MaterialButton
import com.google.android.material.textview.MaterialTextView
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ActivityPost : AppCompatActivity() {

    private lateinit var enterSubTitle: EditText
    private lateinit var enterText: EditText
    private lateinit var buttonSendData: MaterialButton
    private lateinit var fieldResponseSubTitle: MaterialTextView
    private lateinit var fieldResponseText: MaterialTextView
    private lateinit var buttonBack: MaterialButton
    private lateinit var progressBarLoading: ProgressBar
    private lateinit var recyclerView: RecyclerView
    private var adapter: AdapterPost? = null
    private var controller: ControllerPost? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post)

        /* Functions: */
        globalVariablesScope()
        backActivity()
        buttonPostData()

        recyclerView = findViewById(R.id.rcclrVw_actvtPost_id)
        adapter = AdapterPost(emptyList()) // Inicializar o adapter

        recyclerView.adapter = adapter
        controller = ControllerPost()

    }

    private fun buttonPostData() {
        buttonSendData.setOnClickListener {
            if (buttonSendData.isClickable) {

                val id = 0 // Valor padrão para o parâmetro 'id'
                val title = enterSubTitle.text.toString()
                val body = enterText.text.toString()

                if (title.isBlank() || body.isBlank()) {
                    Toast.makeText(this, "Por favor, preencha todos os campos.", Toast.LENGTH_SHORT)
                        .show()
                } else {
                    GlobalScope.launch {
                        try {
                            val posts = controller?.fetchPost(title, body)
                            runOnUiThread {
                                if (posts != null && posts.isNotEmpty()) {
                                    fieldResponseSubTitle.text = "▬ Sub - Título: ${posts[0].title}"
                                    fieldResponseText.text = "▬ Texto: ${posts[0].body}"
                                } else {
                                    Toast.makeText(this@ActivityPost, "Erro ao obter os dados.", Toast.LENGTH_SHORT).show()
                                }
                            }
                        } catch (e: Exception) {
                            runOnUiThread {
                                Toast.makeText(this@ActivityPost, e.message, Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                }
            }
        }
    }

    private fun backActivity() {

        buttonBack.setOnClickListener {
            if (buttonBack.isClickable) {
                val intent = Intent(this, MainActivity::class.java).apply {}
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


