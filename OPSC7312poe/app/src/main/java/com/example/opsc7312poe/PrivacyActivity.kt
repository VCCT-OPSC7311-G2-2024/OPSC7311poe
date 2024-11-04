// PrivacyActivity.kt
package com.example.opsc7312poe

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class PrivacyActivity : AppCompatActivity() {

    private lateinit var backButton: Button
    private lateinit var logoutButton: Button
    private lateinit var privacyContent: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_privacy)

        // Initialize views
        backButton = findViewById(R.id.backButton)
        logoutButton = findViewById(R.id.logoutButton)
        privacyContent = findViewById(R.id.privacyContent)

        // Back button functionality
        backButton.setOnClickListener {
            finish() // Close and return to previous activity
        }

        // Logout button functionality
        logoutButton.setOnClickListener {
            val logoutIntent = Intent(this, Login::class.java)
            logoutIntent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(logoutIntent)
            finish() // Close PrivacyActivity
        }
    }
}
