package com.example.opsc7312poe

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters

class MoodWorker(context: Context, workerParams: WorkerParameters) : Worker(context, workerParams) {

    override fun doWork(): Result {
        return try {
            // Your background task to save the mood data or perform any action here.
            Log.d("MoodWorker", "Running mood worker...")

            // Simulate some work (you can replace this with your actual work)
            // For example, you could fetch and save mood data from Firebase

            // Return Result.success() if the work was successful
            Result.success()
        } catch (e: Exception) {
            Log.e("MoodWorker", "Error in MoodWorker: ${e.message}")
            // Return Result.failure() if there was an error
            Result.failure()
        }
    }
}
