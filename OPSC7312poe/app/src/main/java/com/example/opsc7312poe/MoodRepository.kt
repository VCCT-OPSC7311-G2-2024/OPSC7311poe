package com.example.opsc7312poe

class MoodRepository(private val moodDao: MoodDao) {
    suspend fun insertMood(mood: Mood) {
        moodDao.insertMood(mood)
    }

    suspend fun getAllMoods(): List<Mood> {
        return moodDao.getAllMoods()
    }
}
