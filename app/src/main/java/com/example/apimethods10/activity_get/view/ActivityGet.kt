package com.example.apimethods10.activity_get.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.apimethods10.R
import com.example.apimethods10.view.MainActivity
import com.example.apiregisteruser_10.activity_see.adapter.AdapterGetText
import com.example.apiregisteruser_10.activity_see.controller.ControllerGetText
import com.example.apiregisteruser_10.activity_see.model.ModelGetText
import com.example.apiregisteruser_10.activity_see.response.ResponseGetText
import com.google.android.material.button.MaterialButton

class ActivityGet : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private var listDataUser: List<ModelGetText>? = null
    private lateinit var buttonBack: MaterialButton


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_get)

        /* Functions call: */
        globalScopeVariables()
        settingRecyclerView()
        responseController()
        backActivity()
    }

    private fun backActivity() {

        val myClass = MainActivity::class

        buttonBack.setOnClickListener {
            if (buttonBack.isClickable) {
                val intent = Intent(this, myClass.java).apply {
                }
                startActivity(intent)
                finish()
            }
        }
    }

    private fun responseController() {

        var responseController = ControllerGetText()
        responseController.controllergetPersonalData(object : ResponseGetText {

            override fun successResponse(response: List<ModelGetText>) {
                listDataUser = response
                instantiateUsers(listDataUser!!)
            }

            override fun errorResponse(erro: String) {
                // TODO:
            }
        })
    }

    private fun instantiateUsers(list: List<ModelGetText>) {
        val adapterUser = AdapterGetText(this, list)
        recyclerView.adapter = adapterUser
    }

    private fun settingRecyclerView() {
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(
            this, LinearLayoutManager.VERTICAL, false
        )
    }

    private fun globalScopeVariables() {
        recyclerView = findViewById(R.id.rcclrVw_userDataGet)
        buttonBack = findViewById(R.id.button_back_actvtSee_id)
    }
}
