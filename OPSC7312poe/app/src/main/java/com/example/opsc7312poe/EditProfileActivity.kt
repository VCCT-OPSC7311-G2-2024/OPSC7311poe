package com.example.opsc7312poe

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class EditProfileActivity : AppCompatActivity() {

    private lateinit var firstNameEditText: EditText
    private lateinit var lastNameEditText: EditText
    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var saveProfileButton: Button
    private lateinit var backButton: Button
    private lateinit var logoutButton: Button
    private lateinit var profileImageView: ImageView
    private lateinit var fullNameTextView: TextView // TextView to display the full name

    private val PICK_IMAGE_REQUEST = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile)

        // Initialize UI elements
        firstNameEditText = findViewById(R.id.editFirstName)
        lastNameEditText = findViewById(R.id.editLastName)
        emailEditText = findViewById(R.id.editEmail)
        passwordEditText = findViewById(R.id.editPassword)
        saveProfileButton = findViewById(R.id.saveProfileButton)
        backButton = findViewById(R.id.backButton)
        logoutButton = findViewById(R.id.logoutButton)
        fullNameTextView = findViewById(R.id.fullNameTextView) // Initialize the TextView

        // Get the current user details or set a default "Username" if none provided
        val currentName = intent.getStringExtra("currentName") ?: "Username"
        val nameParts = currentName.split(" ")

        // Set first and last names in EditText fields
        firstNameEditText.setText(nameParts.getOrNull(0)) // Get first name if available
        lastNameEditText.setText(nameParts.getOrNull(1)) // Get last name if available
        fullNameTextView.text = currentName // Display full name initially

        // Save profile button functionality
        saveProfileButton.setOnClickListener {
            val firstName = firstNameEditText.text.toString().trim()
            val lastName = lastNameEditText.text.toString().trim()
            val updatedName = "$firstName $lastName"

            // Show a successful save message
            Toast.makeText(this, "Profile saved successfully", Toast.LENGTH_SHORT).show()

            // Create an intent to hold the result
            val resultIntent = Intent()
            resultIntent.putExtra("updatedName", updatedName) // Pass the updated name
            setResult(Activity.RESULT_OK, resultIntent) // Set result with updated name
            finish() // Close EditProfileActivity
        }

        // Back button functionality
        backButton.setOnClickListener {
            finish() // Close and return to previous activity
        }

        // Logout button functionality
        logoutButton.setOnClickListener {
            val logoutIntent = Intent(this, Login::class.java)
            logoutIntent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(logoutIntent)
            finish() // Close EditProfileActivity
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK && data != null) {
            val selectedImageUri = data.data
            profileImageView.setImageURI(selectedImageUri) // Set selected image as profile picture
        }
    }
}
