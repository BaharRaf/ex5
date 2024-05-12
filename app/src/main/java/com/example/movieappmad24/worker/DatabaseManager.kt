package com.example.movieappmad24.worker

import DatabaseWorker
import android.content.Context
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager

class DatabaseManager (context: Context) {

    private val workManager = WorkManager.getInstance(context)
    fun seedDatabase() {
        // Create WorkRequest to seed Database
        val seedWorkRequestBuilder = OneTimeWorkRequestBuilder<DatabaseWorker>()

        // Start the work
        workManager.enqueue(seedWorkRequestBuilder.build())
    }
}