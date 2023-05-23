package com.example.apimethods10.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.apimethods10.R
import com.example.apimethods10.activity_delete.ActivityDelete
import com.example.apimethods10.activity_post.view.ActivityPostGeneralData
import com.example.apimethods10.activity_get.view.ActivityGet
import com.example.apimethods10.activity_update.ActivityUpdate
import com.google.android.material.button.MaterialButton

class MainActivity : AppCompatActivity() {

    private lateinit var button_createUser: MaterialButton
    private lateinit var button_seeUser: MaterialButton
    private lateinit var button_update: MaterialButton
    private lateinit var button_deleteUser: MaterialButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /* Functions: */
        globalVariablesScope()
        createUser()
        seeUser()
        updateUser()
        deleteUser()
    }

        private fun createUser() {

            val myClass = ActivityPostGeneralData::class

            button_createUser.setOnClickListener {
                if (button_createUser.isClickable) {
                    val intent = Intent(this, myClass.java).apply {
                    }
                    startActivity(intent)
                    finish()
                }
            }
        }

        private fun seeUser() {
            button_seeUser.setOnClickListener {
                if (button_seeUser.isClickable) {
                    val intent = Intent(this, ActivityGet::class.java).apply {
                    };
                    startActivity(intent)
                    finish()
                }
            }
        }

        private fun updateUser() {
            button_update.setOnClickListener {
                if (button_update.isClickable) {
                    val intent = Intent(this, ActivityUpdate::class.java).apply {
                    }
                    startActivity(intent)
                    finish()
                }
            }
        }

        private fun deleteUser() {
            button_deleteUser.setOnClickListener {
                if (button_deleteUser.isClickable) {
                    val intent = Intent(this, ActivityDelete::class.java).apply {
                    }
                    startActivity(intent)
                    finish()
                }
            }
        }

        private fun globalVariablesScope() {
            button_createUser = findViewById(R.id.button_registerUser_id)
            button_seeUser = findViewById(R.id.button_seeUser_id)
            button_update = findViewById(R.id.button_updateUser_id)
            button_deleteUser = findViewById(R.id.button_deleteUser_id)
        }
    }