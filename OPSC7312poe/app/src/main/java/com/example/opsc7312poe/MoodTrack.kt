package com.example.opsc7312poe

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ImageButton
import android.widget.ProgressBar
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.formatter.ValueFormatter
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Calendar
import java.util.Locale


class MoodTrack : AppCompatActivity() {

    private var user: FirebaseUser? = null
    private lateinit var auth: FirebaseAuth
    private lateinit var moodChart: BarChart
    private lateinit var timeFrameSpinner: Spinner
    private lateinit var userMoodsRef: DatabaseReference
    private lateinit var loadingIndicator: ProgressBar
    private lateinit var pieChart: PieChart

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mood_track)

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

        // Initialize UI components
        moodChart = findViewById(R.id.barchart)
        pieChart = findViewById<PieChart>(R.id.piechart)
        val entries = ArrayList<PieEntry>()
        entries.add(PieEntry(30f, "Rad"))
        entries.add(PieEntry(25f, "Good"))
        entries.add(PieEntry(20f, "Meh"))
        entries.add(PieEntry(15f, "Bad"))
        entries.add(PieEntry(10f, "Awful"))


        loadingIndicator = findViewById(R.id.loading_indicator)
        timeFrameSpinner = findViewById(R.id.time_frame_spinner)

        // Adjust padding for system bars
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Initialize Firebase Authentication and Database
        auth = FirebaseAuth.getInstance()
        user = auth.currentUser ?: run {
            Toast.makeText(this, "User not logged in", Toast.LENGTH_SHORT).show()
            finish()
            return
        }

        val userId = user!!.uid
        val database = FirebaseDatabase.getInstance("https://opsc7311poe-fd06a-default-rtdb.europe-west1.firebasedatabase.app")
        userMoodsRef = database.getReference("users").child(userId).child("moods")

        // Set up spinner adapter and listener
        timeFrameSpinner.adapter = ArrayAdapter.createFromResource(
            this,
            R.array.time_frame_options,
            android.R.layout.simple_spinner_item
        )
        timeFrameSpinner.setSelection(0)
        timeFrameSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                when (parent.getItemAtPosition(position).toString()) {
                    "Last 7 Days" -> {
                        moodChart.clear()
                        getMoodsForTimeFrame(getLast7Days(), showWeekly = false)
                    }
                    "Last 4 weeks" -> {
                        moodChart.clear()
                        getWeeklyMoodAverages()
                    }
                }
            }



            override fun onNothingSelected(parent: AdapterView<*>) {}
        }

        // Load mood data for the initial selection
        getMoodsForTimeFrame(getLast7Days(), showWeekly = false)

        getMoodCounts()

        // Navigation buttons
        setupNavigation()
    }

    private fun setupNavigation() {
        val navHome: ImageButton = findViewById(R.id.nav_home)
        navHome.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
            finish()
        }

        val navMood: ImageButton = findViewById(R.id.nav_mood)
        navMood.setOnClickListener {
            startActivity(Intent(this, MoodTrack::class.java))
        }
    }

    private fun getWeeklyMoodAverages() {
        loadingIndicator.visibility = View.VISIBLE

        val weekData = mutableListOf<BarEntry>()
        val calendar = Calendar.getInstance()
        val formatter = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())

        // Get current date and calculate start of the week (Sunday)
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY)

        // Loop to get data for each of the last 7 weeks
        for (week in 0 until 7) {
            val weekDates = getWeekDates(calendar)

            userMoodsRef.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    val moodValues = mutableListOf<Int>()
                    for (date in weekDates) {
                        val moodValue = dataSnapshot.child(date).child("mood").getValue(String::class.java)
                        moodValue?.let { moodValues.add(Mood.fromString(it).value) }
                    }

                    // Only add entry if there's mood data for the week
                    if (moodValues.isNotEmpty()) {
                        val averageMood = calculateAverageMood(moodValues)
                        // Add entries in reverse order (0 for latest week, 6 for oldest)
                        weekData.add(BarEntry((6 - week).toFloat(), averageMood.toFloat()))
                    }

                    // After processing all weeks, update the chart
                    if (week == 6) { // Only update after the last week is processed
                        displayMoodGraph(weekData, getLast7WeeksDates(), showWeekly = true)
                        loadingIndicator.visibility = View.GONE
                    }
                }

                override fun onCancelled(databaseError: DatabaseError) {
                    Toast.makeText(this@MoodTrack, "Failed to load mood data: ${databaseError.message}", Toast.LENGTH_SHORT).show()
                    loadingIndicator.visibility = View.GONE
                }
            })

            calendar.add(Calendar.WEEK_OF_YEAR, -1) // Move to the previous week
        }
    }


    private fun getLast7WeeksDates(): List<String> {
        val formatter = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val calendar = Calendar.getInstance()
        val weeks = mutableListOf<String>()

        // Loop to get start dates of the last 7 weeks
        for (i in 0 until 7) {
            calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY)
            weeks.add(formatter.format(calendar.time))
            calendar.add(Calendar.WEEK_OF_YEAR, -1) // Move to the previous week
        }
        return weeks.reversed() // Reverse to get the correct order
    }
    private fun getWeekDates(calendar: Calendar): List<String> {
        val formatter = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        return (0 until 7).map {
            formatter.format(calendar.time).also { calendar.add(Calendar.DAY_OF_MONTH, -1) }
        }.reversed() // Return dates in correct order
    }

    private fun calculateAverageMood(moodValues: List<Int>): Int {
        return if (moodValues.isNotEmpty()) {
            moodValues.sum() / moodValues.size
        } else {
            0
        }
    }

    private fun getLast7Days(): List<String> {
        return getDaysFromNow(12)
    }

    private fun getDaysFromNow(days: Int): List<String> {
        val formatter = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val calendar = Calendar.getInstance()
        return (0 until days).map {
            formatter.format(calendar.time).also { calendar.add(Calendar.DAY_OF_MONTH, -1) }
        }.reversed()
    }

    private fun displayMoodGraph(moodData: List<BarEntry>, lastDays: List<String>, showWeekly: Boolean) {
        moodChart.clear()

        // Function to get the color based on Mood enum
        fun getColorForMood(mood: Mood): Int {
            return when (mood) {
                Mood.AWFUL -> resources.getColor(R.color.awful, theme)
                Mood.BAD -> resources.getColor(R.color.bad, theme)
                Mood.MEH -> resources.getColor(R.color.meh, theme)
                Mood.GOOD -> resources.getColor(R.color.good, theme)
                Mood.RAD -> resources.getColor(R.color.rad, theme)
            }
        }

        // Create a list of colors based on the mood value of each BarEntry
        val colors = moodData.map { barEntry ->
            val moodValue = barEntry.y.toInt() // Assuming y represents the mood value
            getColorForMood(Mood.values().find { it.value == moodValue } ?: Mood.MEH) // Fallback to MEH
        }

        // Create a BarDataSet with the mood data and the corresponding colors
        val dataSet = BarDataSet(moodData, "Moods").apply {
            this.colors = colors
        }

        moodChart.data = BarData(dataSet)

        // Convert lastDays from date strings to day names (e.g., "Mon", "Tue", ...)
        val dayNames = lastDays.map { dateString ->
            val date = LocalDate.parse(dateString) // Parse the date string
            date.format(DateTimeFormatter.ofPattern("EEE")) // Format to abbreviated day name
        }

        moodChart.xAxis.valueFormatter = if (showWeekly) {
            WeeklyAxisValueFormatter(dayNames)
        } else {
            DayAxisValueFormatter(dayNames)
        }

        // Configure Y-axis
        moodChart.axisLeft.apply {
            isEnabled = true
            setDrawGridLines(false) // Disable Y-axis grid lines
            axisMinimum = 0f
            axisMaximum = 5f // Set Y-axis maximum to 5
            labelCount = 6 // Adjust label count to fit the range
            granularity = 1f
        }

        moodChart.axisRight.isEnabled = false // Disable the right Y-axis

        // Configure X-axis
        moodChart.xAxis.apply {
            position = XAxis.XAxisPosition.BOTTOM
            granularity = 1f
            setDrawGridLines(false) // Disable X-axis grid lines
        }

        // Disable any grid lines
        moodChart.axisLeft.setDrawGridLines(false) // Ensure Y-axis grid lines are off
        moodChart.axisRight.setDrawGridLines(false) // Ensure right Y-axis grid lines are off
        moodChart.xAxis.setDrawGridLines(false) // Ensure X-axis grid lines are off

        moodChart.invalidate()
        moodChart.animateY(500)
    }

    private fun getMoodsForTimeFrame(dates: List<String>, showWeekly: Boolean) {
        loadingIndicator.visibility = View.VISIBLE

        val moodData = mutableListOf<BarEntry>()

        userMoodsRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for (date in dates) {
                    val moodValue = dataSnapshot.child(date).child("mood").getValue(String::class.java)
                    moodValue?.let {
                        val moodIndex = Mood.fromString(it).value
                        val entry = BarEntry(dates.indexOf(date).toFloat(), moodIndex.toFloat())
                        moodData.add(entry)
                    }
                }
                displayMoodGraph(moodData, dates, showWeekly)
                loadingIndicator.visibility = View.GONE
            }

            override fun onCancelled(databaseError: DatabaseError) {
                Toast.makeText(this@MoodTrack, "Failed to load mood data: ${databaseError.message}", Toast.LENGTH_SHORT).show()
                loadingIndicator.visibility = View.GONE
            }
        })
    }

    private class DayAxisValueFormatter(private val days: List<String>) : ValueFormatter() {
        override fun getAxisLabel(value: Float, axis: AxisBase?): String {
            return if (value.toInt() in days.indices) days[value.toInt()] else ""
        }
    }

    private class WeeklyAxisValueFormatter(private val weeks: List<String>) : ValueFormatter() {
        override fun getAxisLabel(value: Float, axis: AxisBase?): String {
            return if (value.toInt() in weeks.indices) weeks[value.toInt()] else ""
        }
    }

    private fun getMoodCounts() {
        loadingIndicator.visibility = View.VISIBLE
        val moodCounts = mutableMapOf<String, Int>()

        userMoodsRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for (moodSnapshot in dataSnapshot.children) {
                    val moodValue = moodSnapshot.child("mood").getValue(String::class.java)
                    moodValue?.let {
                        val mood = Mood.fromString(it)
                        moodCounts[mood.name] = moodCounts.getOrDefault(mood.name, 0) + 1
                    }
                }
                displayMoodPieChart(moodCounts) // Call to display the pie chart
                loadingIndicator.visibility = View.GONE
            }

            override fun onCancelled(databaseError: DatabaseError) {
                Toast.makeText(this@MoodTrack, "Failed to load mood data: ${databaseError.message}", Toast.LENGTH_SHORT).show()
                loadingIndicator.visibility = View.GONE
            }
        })
    }


    private fun displayMoodPieChart(moodCounts: Map<String, Int>) {
        val entries = ArrayList<PieEntry>()

        // Prepare the entries based on moodCounts
        for ((mood, count) in moodCounts) {
            entries.add(PieEntry(count.toFloat(), mood))
        }

        val pieDataSet = PieDataSet(entries, "Mood Distribution").apply {
            colors = listOf(
                ContextCompat.getColor(this@MoodTrack, R.color.awful),
                ContextCompat.getColor(this@MoodTrack, R.color.bad),
                ContextCompat.getColor(this@MoodTrack, R.color.meh),
                ContextCompat.getColor(this@MoodTrack, R.color.good),
                ContextCompat.getColor(this@MoodTrack, R.color.rad)
            )
        }

        val data = PieData(pieDataSet)
        data.setDrawValues(true)
        data.setValueTextSize(12f)
        data.setValueTextColor(Color.BLACK)

        pieChart.data = data
        pieChart.invalidate() // Refresh the chart to display the new data
        pieChart.animateY(1000, Easing.EaseInOutQuad) // Optional animation
    }


    private enum class Mood(val value: Int) {
        AWFUL(1),
        BAD(2),
        MEH(3),
        GOOD(4),
        RAD(5);

        companion object {
            fun fromString(mood: String): Mood {
                return when (mood.lowercase(Locale.getDefault())) {
                    "awful" -> AWFUL
                    "bad" -> BAD
                    "meh" -> MEH
                    "good" -> GOOD
                    "rad" -> RAD
                    else -> MEH // Default mood
                }
            }
        }
    }


}
