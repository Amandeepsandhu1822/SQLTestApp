package com.manmohan7.sqltestapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    var et_username: EditText? = null
    var et_password: EditText? = null
    var et_cpassword: EditText? = null
    var btn_register: Button? = null
    var btn_login: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val databaseClass = DatabaseClass(this)
        et_username = findViewById<View>(R.id.et_username) as EditText
        et_password = findViewById<View>(R.id.et_password) as EditText
        et_cpassword = findViewById<View>(R.id.et_cpassword) as EditText
        btn_register = findViewById<View>(R.id.btn_register) as Button
        btn_login = findViewById<View>(R.id.btn_login) as Button
        btn_login!!.setOnClickListener {
            val intent = Intent(this@MainActivity, Login::class.java)
            startActivity(intent)
        }
        btn_register!!.setOnClickListener {
            val username = et_username!!.text.toString()
            val password = et_password!!.text.toString()
            val confirm_password = et_cpassword!!.text.toString()
            if (username == "" || password == "" || confirm_password == "") {
                Toast.makeText(applicationContext, "Fields Required", Toast.LENGTH_SHORT).show()
            } else {
                if (password == confirm_password) {
                    val checkusername: Boolean = databaseClass.CheckUsername(username)
                    if (checkusername == true) {
                        val insert: Boolean = databaseClass.Insert(username, password)
                        if (insert == true) {
                            Toast.makeText(applicationContext, "Registered", Toast.LENGTH_SHORT)
                                .show()
                            et_username!!.setText("")
                            et_password!!.setText("")
                            et_cpassword!!.setText("")
                        }
                    } else {
                        Toast.makeText(
                            applicationContext,
                            "Username already taken",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                } else {
                    Toast.makeText(
                        applicationContext,
                        "Password does not match",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }
}