package com.example.habbit_tracker

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class SignUpActivity : AppCompatActivity() {

    private lateinit var etNewUsername: EditText
    private lateinit var etNewPassword: EditText
    private lateinit var btnSignUp: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        etNewUsername = findViewById(R.id.etNewUsername)
        etNewPassword = findViewById(R.id.etNewPassword)
        btnSignUp = findViewById(R.id.btnSignup)

        btnSignUp.setOnClickListener {
            // Handle sign-up button click here
            val newUsername = etNewUsername.text.toString()
            val newPassword = etNewPassword.text.toString()

            // Add your sign-up logic here
            if (newUsername.isNotEmpty() && newPassword.isNotEmpty()) {
                // Successful sign-up
                showToast("Sign-up successful!")
                // You might want to save the new user information to your database or perform other actions here.
            } else {
                // Empty username or password
                showToast("Please enter both username and password for sign-up")
            }
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
