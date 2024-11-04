package com.example.opsc7312poe

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "mood_table")
data class Mood(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val mood: String,
    val date: String
)
