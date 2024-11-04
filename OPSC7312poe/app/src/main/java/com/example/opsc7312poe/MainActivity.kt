package com.example.opsc7312poe

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.net.ConnectivityManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var userMoodsRef: DatabaseReference
    private var user: FirebaseUser? = null
    private var selectedMood: String? = null
    private lateinit var moodDatabase: MoodDatabase
    private lateinit var moodRepository: MoodRepository

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://zenquotes.io/api/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val quoteApi = retrofit.create(QuoteApi::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        FirebaseApp.initializeApp(this)
        auth = FirebaseAuth.getInstance()

        try {
            moodDatabase = MoodDatabase.getDatabase(this)
        } catch (e: Exception) {
            Log.e("MainActivity", "Database creation failed: ${e.message}")
        }

        moodRepository = MoodRepository(moodDatabase.moodDao())

        val radIcon: ImageView = findViewById(R.id.rad)
        val goodIcon: ImageView = findViewById(R.id.good)
        val mehIcon: ImageView = findViewById(R.id.meh)
        val badIcon: ImageView = findViewById(R.id.bad)
        val awfulIcon: ImageView = findViewById(R.id.awful)
        val saveMoodButton: Button = findViewById(R.id.save_mood)

        val navProfile: ImageButton = findViewById(R.id.nav_profile)
        val navJournal: ImageButton = findViewById(R.id.nav_journal)
        val navAudio: ImageButton = findViewById(R.id.nav_audio)
        val navHome: ImageButton = findViewById(R.id.nav_home)
        val navMood: ImageButton = findViewById(R.id.nav_mood)

        user = auth.currentUser

        if (user == null) {
            startActivity(Intent(this, Login::class.java))
            finish()
        } else {
            val userId = user!!.uid
            val database =
                FirebaseDatabase.getInstance("https://opsc7311poe-fd06a-default-rtdb.europe-west1.firebasedatabase.app")
            userMoodsRef = database.getReference("users").child(userId).child("moods")

            radIcon.setOnClickListener { onMoodSelected("rad") }
            goodIcon.setOnClickListener { onMoodSelected("good") }
            mehIcon.setOnClickListener { onMoodSelected("meh") }
            badIcon.setOnClickListener { onMoodSelected("bad") }
            awfulIcon.setOnClickListener { onMoodSelected("awful") }

            saveMoodButton.setOnClickListener {
                if (selectedMood != null) {
                    saveMood(selectedMood!!)
                } else {
                    Toast.makeText(this, "Please select a mood", Toast.LENGTH_SHORT).show()
                }
            }

            navProfile.setOnClickListener { startActivity(Intent(this, AccountActivity::class.java)) }
            navJournal.setOnClickListener { startActivity(Intent(this, JournalEntriesActivity::class.java)) }
            navAudio.setOnClickListener { startActivity(Intent(this, AudioActivity::class.java)) }
            navHome.setOnClickListener { startActivity(Intent(this, MainActivity::class.java)) }
            navMood.setOnClickListener { startActivity(Intent(this, MoodTrack::class.java)) }
        }
        // Call sync moods when connectivity is restored
        registerReceiver(networkChangeReceiver, IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION))
        createNotificationChannel()
        scheduleMoodWorker()
    }

    private fun onMoodSelected(mood: String) {
        selectedMood = mood
        Toast.makeText(this, "Mood selected: $mood", Toast.LENGTH_SHORT).show()
    }

    private fun saveMood(mood: String) {
        val currentDate = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())
        val newMood = Mood(mood = mood, date = currentDate)

        if (isNetworkAvailable()) {
            // Save directly to Firebase
            userMoodsRef.child(currentDate).child("mood").setValue(mood)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(this, "Mood saved to Firebase!", Toast.LENGTH_SHORT).show()
                        fetchMoodQuote(mood)
                        updateMoodStreak(currentDate)
                        startActivity(Intent(this, MoodTrack::class.java))
                    } else {
                        Toast.makeText(
                            this,
                            "Failed to save mood: ${task.exception?.message}",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
        } else {
            // Save locally
            GlobalScope.launch(Dispatchers.IO) {
                moodRepository.insertMood(newMood)
                withContext(Dispatchers.Main) {
                    Toast.makeText(this@MainActivity, "Mood saved locally!", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }
    }
    private fun fetchMoodQuote(mood: String) {
        quoteApi.getQuotesByMood(mood).enqueue(object : Callback<List<QuoteResponse>> {
            override fun onResponse(call: Call<List<QuoteResponse>>, response: Response<List<QuoteResponse>>) {
                if (response.isSuccessful) {
                    val randomQuote = response.body()?.randomOrNull()?.q ?: "Stay positive! No quotes found."
                    sendMoodQuoteNotification(randomQuote)
                } else {
                    sendMoodQuoteNotification("Error fetching quotes. Stay positive!")
                }
            }

            override fun onFailure(call: Call<List<QuoteResponse>>, t: Throwable) {
                sendMoodQuoteNotification("Network error. Stay positive!")
            }
        })
    }

    private fun sendMoodQuoteNotification(quote: String) {
        val notificationBuilder = NotificationCompat.Builder(this, "MoodQuoteChannel")
            .setSmallIcon(R.drawable.logo)
            .setContentTitle("Your Mood Quote")
            .setContentText(quote)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        val notificationManager = NotificationManagerCompat.from(this)
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.POST_NOTIFICATIONS), 1001)
            return
        }
        notificationManager.notify(1001, notificationBuilder.build())
    }

    // Method to sync local moods to Firebase when connected
    private fun syncMoodToFirebase(mood: Mood) {
        val currentDate = mood.date
        userMoodsRef.child(currentDate).child("mood").setValue(mood.mood)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    // Remove from local DB after successful sync
                    CoroutineScope(Dispatchers.IO).launch {
                        moodDatabase.moodDao().deleteMoodByDate(mood.date) // Corrected syntax
                    }
                    Toast.makeText(this, "Mood synced successfully!", Toast.LENGTH_SHORT).show()
                } else {
                    Log.e("MainActivity", "Failed to sync mood: ${task.exception?.message}")
                }
            }
    }


    // Check network connectivity
    private fun isNetworkAvailable(): Boolean {
        val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = connectivityManager.activeNetworkInfo
        return activeNetwork != null && activeNetwork.isConnected
    }

    // Register a broadcast receiver to listen for connectivity changes
    private val networkChangeReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            if (isNetworkAvailable()) {
                syncAllMoodsToFirebase()  // Sync local data with Firebase when connected
            }
        }
    }

    private fun syncAllMoodsToFirebase() {
        CoroutineScope(Dispatchers.IO).launch {
            val moods = moodDatabase.moodDao().getAllMoods() // Retrieve all moods from local DB
            for (mood in moods) {
                syncMoodToFirebase(mood) // Sync each mood
            }
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(networkChangeReceiver)
    }
    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                "MoodQuoteChannel",
                "Mood Quotes",
                NotificationManager.IMPORTANCE_DEFAULT
            ).apply { description = "Channel for mood quotes" }
            val notificationManager: NotificationManager = getSystemService(NotificationManager::class.java)
            notificationManager.createNotificationChannel(channel)
        }
    }

    private fun scheduleMoodWorker() {
        val workRequest = PeriodicWorkRequestBuilder<MoodWorker>(15, TimeUnit.MINUTES).build()
        WorkManager.getInstance(this).enqueue(workRequest)
    }

    private fun updateMoodStreak(date: String) {
        userMoodsRef.get().addOnSuccessListener { snapshot ->
            val streakCount = snapshot.childrenCount
            userMoodsRef.child(date).child("streak").setValue(streakCount + 1)
                .addOnSuccessListener { Toast.makeText(this, "Streak updated!", Toast.LENGTH_SHORT).show() }
                .addOnFailureListener { Log.e("MainActivity", "Failed to update streak") }
        }
    }
}
