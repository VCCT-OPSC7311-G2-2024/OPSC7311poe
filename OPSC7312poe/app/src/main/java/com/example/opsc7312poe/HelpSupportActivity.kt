// HelpSupportActivity.kt
package com.example.opsc7312poe

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class HelpSupportActivity : AppCompatActivity() {

    private lateinit var backButton: Button
    private lateinit var logoutButton: Button
    private lateinit var helpSupportContent: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_help_support)

        // Initialize views
        backButton = findViewById(R.id.backButton)
        logoutButton = findViewById(R.id.logoutButton)
        helpSupportContent = findViewById(R.id.helpSupportContent)

        // Set up click listener for the back button
        backButton.setOnClickListener {
            finish() // Close the current activity and return to the previous one
        }

        // Set up click listener for the logout button
        logoutButton.setOnClickListener {
            val logoutIntent = Intent(this, Login::class.java)
            logoutIntent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(logoutIntent) // Start the login activity
            finish() // Close HelpSupportActivity
        }
    }
}
