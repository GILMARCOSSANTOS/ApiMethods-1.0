package com.example.apimethods10.activity_get.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.apimethods10.R
import com.example.apimethods10.view.MainActivity
import com.example.apiregisteruser_10.activity_see.adapter.AdapterGet
import com.example.apiregisteruser_10.activity_see.controller.ControllerGet
import com.example.apiregisteruser_10.activity_see.model.ModelGet
import com.example.apiregisteruser_10.activity_see.response.ResponseGet
import com.google.android.material.button.MaterialButton

class ActivityGet : AppCompatActivity() {

    private lateinit var recyclerViewGet: RecyclerView
    private var listDataUser: List<ModelGet>? = null
    private lateinit var buttonBack: MaterialButton
    private lateinit var progressBarLoading: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_get)

        /* Functions call: */
        globalScopeVariables()
        settingRecyclerView()
        responseController()
        backActivity()
    }

    private fun responseController() {

        var responseController = ControllerGet()
        responseController.controllerget(object : ResponseGet {

            override fun successResponse(response: List<ModelGet>) {
                progressBarLoading.visibility = View.INVISIBLE
                listDataUser = response
                instantiateUsers(listDataUser!!)
            }

            override fun errorResponse(erro: String) {
                progressBarLoading.visibility = View.VISIBLE
            }
        })
    }

    private fun instantiateUsers(list: List<ModelGet>) {
        val adapterUser = AdapterGet(this, list)
        recyclerViewGet.adapter = adapterUser
    }

    private fun settingRecyclerView() {
        recyclerViewGet.setHasFixedSize(true)
        recyclerViewGet.layoutManager = LinearLayoutManager(
            this, LinearLayoutManager.VERTICAL, false
        )
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

    private fun globalScopeVariables() {
        recyclerViewGet = findViewById(R.id.rcclrVw_userDataGet)
        buttonBack = findViewById(R.id.button_back_actvtSee_id)
        progressBarLoading = findViewById(R.id.prgrssBar_actvtGet_id)
    }
}
