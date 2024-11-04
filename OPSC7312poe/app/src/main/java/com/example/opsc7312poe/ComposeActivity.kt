package com.example.opsc7312poe

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class ComposeActivity : AppCompatActivity() {

    private lateinit var bodyEditText: EditText
    private lateinit var saveEntryButton: Button
    private lateinit var viewEntriesButton: Button // Button for viewing entries
    private lateinit var moodIcon: ImageView
    private lateinit var backButton: TextView
    private lateinit var journalDateTextView: TextView
    private lateinit var databaseRef: DatabaseReference
    private lateinit var auth: FirebaseAuth
    private var selectedMood: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_compose)

        // Initialize Firebase components
        auth = FirebaseAuth.getInstance()
        databaseRef = FirebaseDatabase.getInstance("https://opsc7311poe-fd06a-default-rtdb.europe-west1.firebasedatabase.app")
            .getReference("journalEntries")

        // Get UI elements
        bodyEditText = findViewById(R.id.journalBody)
        saveEntryButton = findViewById(R.id.saveEntryButton)
        moodIcon = findViewById(R.id.moodIcon)
        backButton = findViewById(R.id.backButton)
        journalDateTextView = findViewById(R.id.journalDateTextView)

        // Set up the back button
        backButton.setOnClickListener {
            // Start the ComposeActivity
           finish()
        }

        // Set the journal date to the current date
        val currentDate = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(Calendar.getInstance().time)
        journalDateTextView.text = currentDate // Display current date
        selectedMood = "good" // Default mood can be set here if needed

        // Retrieve the selected mood from the intent
        val selectedMoodExtra = intent.getStringExtra("selectedMood")
        if (selectedMoodExtra != null) {
            setMoodIcon(selectedMoodExtra)
            this.selectedMood = selectedMoodExtra // Store the selected mood
        }

        // Set up save button listener
        saveEntryButton.setOnClickListener {
            saveEntry()
        }
    }

    private fun setMoodIcon(selectedMood: String) {
        when (selectedMood) {
            "rad" -> moodIcon.setImageResource(R.drawable.rad)
            "good" -> moodIcon.setImageResource(R.drawable.good)
            "meh" -> moodIcon.setImageResource(R.drawable.meh)
            "bad" -> moodIcon.setImageResource(R.drawable.bad)
            "awful" -> moodIcon.setImageResource(R.drawable.awful)
            else -> moodIcon.setImageResource(R.drawable.good)
        }
    }

    private fun saveEntry() {
        val body = bodyEditText.text.toString().trim()
        val userId = auth.currentUser?.uid ?: return

        // The date is now set to currentDate
        val currentDate = journalDateTextView.text.toString()

        if (body.isNotEmpty()) {
            val entryId = databaseRef.push().key ?: return

            // Create a new JournalEntry with the current date and selected mood
            val entry = JournalEntry(selectedMood, body, currentDate)

            databaseRef.child(userId).child(entryId).setValue(entry).addOnCompleteListener {
                if (it.isSuccessful) {
                    Toast.makeText(this, "Entry saved successfully", Toast.LENGTH_SHORT).show()
                    clearFields() // Optionally clear fields after saving

                    val intent = Intent(this, ComposeActivity::class.java)
                    startActivity(intent)

                } else {
                    Toast.makeText(this, "Failed to save entry", Toast.LENGTH_SHORT).show()
                }
            }
        } else {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
        }
    }

    private fun clearFields() {
        bodyEditText.text.clear()
        // No need to clear the date, as it's always the current date
    }
}

data class JournalEntry(
    val mood: String = "", // Default value for mood
    val body: String = "", // Default value for body
    val date: String = ""  // Default value for date
) {
    // No-argument constructor is provided by default with default parameter values
    constructor() : this("", "", "") // Explicit no-argument constructor
}