package com.manmohan7.sqltestapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class Login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val databaseClass = DatabaseClass(this)
        val et_lusername = findViewById<View>(R.id.et_username) as EditText
        val et_lpassword = findViewById<View>(R.id.et_password) as EditText
        val btn_llogin = findViewById<View>(R.id.btn_login) as Button
        val btn_lregister = findViewById<View>(R.id.btn_register) as Button
        btn_lregister!!.setOnClickListener {
            val intent = Intent(this@Login, MainActivity::class.java)
            startActivity(intent)
        }
        btn_llogin.setOnClickListener(View.OnClickListener {
            val username = et_lusername!!.text.toString()
            val password: String = et_lpassword.getText().toString()
            val checklogin = databaseClass!!.CheckLogin(username, password)
            if (checklogin) {
                //Toast.makeText(getApplicationContext(), "Login Successful", Toast.LENGTH_SHORT).show();
                val i = Intent(applicationContext, Welcome::class.java)
                startActivity(i)
            } else {
                Toast.makeText(
                    applicationContext,
                    "Invalid username or password",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
    }
}