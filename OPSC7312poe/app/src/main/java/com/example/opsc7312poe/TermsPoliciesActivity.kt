// TermsPoliciesActivity.kt
package com.example.opsc7312poe

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class TermsPoliciesActivity : AppCompatActivity() {

    private lateinit var backButton: Button
    private lateinit var logoutButton: Button
    private lateinit var termsPoliciesContent: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_terms_policies)

        // Initialize views
        backButton = findViewById(R.id.backButton)
        logoutButton = findViewById(R.id.logoutButton)
        termsPoliciesContent = findViewById(R.id.termsPoliciesContent)

        // Back button functionality
        backButton.setOnClickListener {
            finish() // Close and return to previous activity
        }

        // Logout button functionality
        logoutButton.setOnClickListener {
            val logoutIntent = Intent(this, Login::class.java)
            logoutIntent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(logoutIntent)
            finish() // Close TermsPoliciesActivity
        }
    }
}
