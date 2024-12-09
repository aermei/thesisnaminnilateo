package com.example.demo1

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import android.content.Intent
import com.example.glbviewer.R

class MainActivity : AppCompatActivity() {

    private lateinit var editTextStudentId: EditText
    private lateinit var editTextPassword: EditText
    private lateinit var button: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editTextStudentId = findViewById(R.id.editTextStudentId)
        editTextPassword = findViewById(R.id.editTextPassword)
        button = findViewById(R.id.button)

        button.setOnClickListener {
            handleLogin()
        }
    }

    private fun handleLogin() {
        val studentId = editTextStudentId.text.toString()
        val password = editTextPassword.text.toString()

        if (studentId.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Please enter both Student ID and Password", Toast.LENGTH_SHORT)
                .show()
        } else {
            // Logic for successful login or additional validations
            Toast.makeText(this, "Login successful", Toast.LENGTH_SHORT).show()

            // Save login state in SharedPreferences
            val sharedPref = getSharedPreferences("user_prefs", MODE_PRIVATE)
            with(sharedPref.edit()) {
                putBoolean("is_logged_in", true)  // User is logged in
                apply()
            }

            // Navigate to MainActivity2 after successful login
            val intent = Intent(this, MainActivity2::class.java)
            startActivity(intent)

            // Optionally finish the current activity to prevent returning back to login
            finish()
        }
    }
}