package com.example.opsc7312poe

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class JournalEntriesAdapter(private val entriesList: List<JournalEntry>) :
    RecyclerView.Adapter<JournalEntriesAdapter.EntryViewHolder>() {

    private val TAG = "JournalEntriesAdapter"

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EntryViewHolder {
        Log.d(TAG, "onCreateViewHolder called")
        // Inflate the item layout
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_journal_entry, parent, false)
        return EntryViewHolder(view)
    }

    override fun onBindViewHolder(holder: EntryViewHolder, position: Int) {
        val entry = entriesList[position]

        // Log the details of the entry being bound
        Log.d(TAG, "onBindViewHolder called for position: $position, entry: $entry")

        // Safely set the body, date, and mood
        holder.bodyTextView.text = entry.body.ifEmpty { "No Content Available" }
        holder.dateTextView.text = entry.date.ifEmpty { "Date Unknown" }
        holder.moodTextView.text = entry.mood.ifEmpty { "Mood Unknown" }
    }

    override fun getItemCount(): Int {
        val count = entriesList.size
        Log.d(TAG, "getItemCount called, count: $count")
        return count
    }

    // ViewHolder for the journal entry item
    class EntryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // Bind the TextViews from the item layout
        val dateTextView: TextView = itemView.findViewById(R.id.entryDate)
        val bodyTextView: TextView = itemView.findViewById(R.id.entryBody)
        val moodTextView: TextView = itemView.findViewById(R.id.entryMood) // TextView for mood
    }
}
