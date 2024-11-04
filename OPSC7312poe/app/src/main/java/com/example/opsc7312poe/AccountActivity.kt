package com.example.opsc7312poe

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class AccountActivity : AppCompatActivity() {

    lateinit var userNameTextView: TextView
    private lateinit var editProfileButton: Button
    private lateinit var securityButton: Button
    private lateinit var notificationsButton: Button
    private lateinit var privacyButton: Button
    private lateinit var helpSupportButton: Button
    private lateinit var termsPoliciesButton: Button
    lateinit var logoutButton: TextView
    lateinit var backButton: TextView // Renamed from settingsButton to backButton
    private lateinit var auth: FirebaseAuth

    // Request code to identify the result from EditProfileActivity
    private val EDIT_PROFILE_REQUEST_CODE = 1001

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_account)

        val navProfile: ImageButton = findViewById(R.id.nav_profile)
        val navJournal: ImageButton = findViewById(R.id.nav_journal)
        val navAudio: ImageButton = findViewById(R.id.nav_audio)
        val navHome: ImageButton = findViewById(R.id.nav_home)
        val navMood: ImageButton = findViewById(R.id.nav_mood)

        navProfile.setOnClickListener { startActivity(Intent(this, AccountActivity::class.java)) }
        navJournal.setOnClickListener { startActivity(Intent(this, JournalEntriesActivity::class.java)) }
        navAudio.setOnClickListener { startActivity(Intent(this, AudioActivity::class.java)) }
        navHome.setOnClickListener { startActivity(Intent(this, MainActivity::class.java)) }
        navMood.setOnClickListener { startActivity(Intent(this, MoodTrack::class.java)) }


        auth = FirebaseAuth.getInstance()
        userNameTextView = findViewById(R.id.userName)
        editProfileButton = findViewById(R.id.editProfileButton)
        privacyButton = findViewById(R.id.privacyButton)
        helpSupportButton = findViewById(R.id.helpSupportButton)
        termsPoliciesButton = findViewById(R.id.termsPoliciesButton)
        logoutButton = findViewById(R.id.logoutButton)
        backButton = findViewById(R.id.backButton) // Still referencing the same button but renaming it

        // Set username or email
        val user = auth.currentUser
        userNameTextView.text = user?.displayName ?: "Victoria Robertson"

        // Edit profile button click listener
        editProfileButton.setOnClickListener {
            val intent = Intent(this, EditProfileActivity::class.java)
            // Pass the current username to EditProfileActivity
            intent.putExtra("currentName", userNameTextView.text.toString())
            startActivityForResult(intent, EDIT_PROFILE_REQUEST_CODE)
        }


        privacyButton.setOnClickListener {
            val intent = Intent(this, PrivacyActivity::class.java)
            startActivity(intent)
        }

        helpSupportButton.setOnClickListener {
            val intent = Intent(this, HelpSupportActivity::class.java)
            startActivity(intent)
        }

        termsPoliciesButton.setOnClickListener {
            val intent = Intent(this, TermsPoliciesActivity::class.java)
            startActivity(intent)
        }

        logoutButton.setOnClickListener {
            auth.signOut()
            startActivity(Intent(this, Login::class.java))
            finish()
        }

        // Update this to finish the activity (back button functionality)
        backButton.setOnClickListener {
            finish() // This will close the current activity and return to the previous one
        }
    }

    // Handle the result from EditProfileActivity
    public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == EDIT_PROFILE_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            // Get the updated name from the result
            val updatedName = data?.getStringExtra("updatedName")
            if (updatedName != null) {
                userNameTextView.text = updatedName
            }
        }
    }

    companion object {
        val EDIT_PROFILE_REQUEST_CODE: Int
            get() {
                TODO()
            }
    }
}
