package com.example.apimethods10.activity_update

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.apimethods10.R
import com.example.apimethods10.view.MainActivity
import com.google.android.material.button.MaterialButton

class ActivityUpdate : AppCompatActivity() {

    private lateinit var buttonBack: MaterialButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update)

        /* Functions: */
        globalVariablesScope()
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

    private fun globalVariablesScope() {
        buttonBack = findViewById(R.id.button_back_actvtUpdate_id)
    }
}