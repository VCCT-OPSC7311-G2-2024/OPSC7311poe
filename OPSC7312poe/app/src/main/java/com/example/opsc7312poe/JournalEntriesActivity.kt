package com.example.opsc7312poe

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class JournalEntriesActivity : AppCompatActivity() {

    private lateinit var entriesRecyclerView: RecyclerView
    private lateinit var databaseRef: DatabaseReference
    private lateinit var auth: FirebaseAuth
    private val entriesList = mutableListOf<JournalEntry>()
    private lateinit var adapter: JournalEntriesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_journal_entries)

        // Initialize RecyclerView and its properties
        entriesRecyclerView = findViewById(R.id.entriesRecyclerView)
        entriesRecyclerView.layoutManager = LinearLayoutManager(this)

        // Initialize adapter and set it to RecyclerView
        adapter = JournalEntriesAdapter(entriesList)
        entriesRecyclerView.adapter = adapter

        val composeButton: Button = findViewById(R.id.composeButton)
        composeButton.setOnClickListener { startActivity(Intent(this, ComposeActivity::class.java)) }

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

        // Initialize Firebase instances
        auth = FirebaseAuth.getInstance()
        databaseRef = FirebaseDatabase.getInstance("https://opsc7311poe-fd06a-default-rtdb.europe-west1.firebasedatabase.app")
            .getReference("journalEntries")

        // Fetch entries from Firebase
        fetchEntries()
    }

    private fun fetchEntries() {
        Log.d("JournalEntriesActivity", "Fetching entries from Firebase")

        databaseRef.child(auth.currentUser?.uid ?: "").addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                entriesList.clear() // Clear the list to avoid duplicates

                // Log the entire snapshot for debugging
                Log.d("JournalEntriesActivity", "Snapshot: $snapshot")

                // Loop through each child in the snapshot
                for (entrySnapshot in snapshot.children) {
                    val entry = entrySnapshot.getValue(JournalEntry::class.java)
                    entry?.let {
                        entriesList.add(it)
                        Log.d("JournalEntriesActivity", "Entry added: $it")
                    }
                }

                // Check if entries were fetched and update adapter
                if (entriesList.isNotEmpty()) {
                    Log.d("JournalEntriesActivity", "Total entries fetched: ${entriesList.size}")
                } else {
                    Log.d("JournalEntriesActivity", "No entries found")
                }

                adapter.notifyDataSetChanged() // Notify adapter about data change
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("JournalEntriesActivity", "Failed to load entries: ${error.message}")
                Toast.makeText(this@JournalEntriesActivity, "Failed to load entries", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
