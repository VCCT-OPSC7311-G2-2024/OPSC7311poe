package com.example.opsc7312poe

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface MoodDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMood(mood: Mood)

    @Query("SELECT * FROM mood_table WHERE date = :date")
    suspend fun getMoodByDate(date: String): Mood?

    @Query("SELECT * FROM mood_table")
    suspend fun getAllMoods(): List<Mood>

    @Query("DELETE FROM mood_table WHERE date = :date")
    suspend fun deleteMoodByDate(date: String)
}
