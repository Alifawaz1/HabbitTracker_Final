package com.example.habbit_tracker

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {

    private lateinit var etUsername: EditText
    private lateinit var etPassword: EditText
    private lateinit var btnLogin: Button
    private lateinit var tvSignUp: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        etUsername = findViewById(R.id.etUsername)
        etPassword = findViewById(R.id.etPassword)
        btnLogin = findViewById(R.id.btnLogin)
        tvSignUp = findViewById(R.id.tvSignUp)

        btnLogin.setOnClickListener {
            // Handle login button click here
            val username = etUsername.text.toString()
            val password = etPassword.text.toString()

            // Replace this with your authentication logic
            if (isValidCredentials(username, password)) {
                // Successful login
                showToast("Login successful!")

                // Navigate to the DashboardActivity after successful login
                val intent = Intent(this, DashboardActivity::class.java)
                startActivity(intent)

                // Finish the LoginActivity to prevent the user from going back
                finish()
            } else {
                // Invalid credentials
                showToast("Please enter valid username and password")
            }
        }

        tvSignUp.setOnClickListener {
            // Navigate to the SignUpActivity
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun isValidCredentials(username: String, password: String): Boolean {
        return username == "example" && password == "password"
    }
}
