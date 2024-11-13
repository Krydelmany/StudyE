package com.app.studye.data.model

import android.util.Log
import com.app.studye.data.network.RetrofitInstance
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

fun fetchUserName(onResult: (String?) -> Unit) {
val apiKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6ImVlcXVvbmJ2a2dvanFqaWZkcm1hIiwicm9sZSI6ImFub24iLCJpYXQiOjE3MzE0Mzc4NjksImV4cCI6MjA0NzAxMzg2OX0.-NV9dcT7j4s63e4pdGxc1cq98Dq4emWynpurSmosd7A"
val authToken = "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6ImVlcXVvbmJ2a2dvanFqaWZkcm1hIiwicm9sZSI6InNlcnZpY2Vfcm9sZSIsImlhdCI6MTczMTQzNzg2OSwiZXhwIjoyMDQ3MDEzODY5fQ.AtEXTO07p-b0dbFpo-nJB_2BdfR8a7qe4EnYWZhQOnI"

    CoroutineScope(Dispatchers.IO).launch {
        val response = RetrofitInstance.api.getProfiles(
            apiKey = apiKey,
            authToken = authToken
        )

        if (response.isSuccessful) {
            val profiles = response.body()
            val userName = profiles?.firstOrNull()?.name
            onResult(userName)
        } else {
            Log.d("API Test", "Error: ${response.code()} - ${response.message()}")
            onResult(null)
        }
    }
}
