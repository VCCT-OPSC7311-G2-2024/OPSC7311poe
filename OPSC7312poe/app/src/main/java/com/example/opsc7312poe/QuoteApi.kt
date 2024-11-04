package com.example.opsc7312poe

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface QuoteApi {
    @GET("/api/quotes/{mood}")  // Remove the leading slash for the path
    fun getQuotesByMood(@Path("mood") mood: String): Call<List<QuoteResponse>>
}

// Retrofit setup
val retrofit = Retrofit.Builder()
    .baseUrl("https://zenquotes.io/api/")
    .addConverterFactory(GsonConverterFactory.create())
    .build()

val quoteApi = retrofit.create(QuoteApi::class.java)