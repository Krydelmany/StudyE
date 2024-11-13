package com.app.studye.data.network

import android.util.Log
import io.github.jan.supabase.auth.Auth
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.postgrest.postgrest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

object SupabaseClient {
    val supabase = createSupabaseClient(
        supabaseUrl = "https://eequonbvkgojqjifdrma.supabase.co",
        supabaseKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6ImVlcXVvbmJ2a2dvanFqaWZkcm1hIiwicm9sZSI6ImFub24iLCJpYXQiOjE3MzE0Mzc4NjksImV4cCI6MjA0NzAxMzg2OX0.-NV9dcT7j4s63e4pdGxc1cq98Dq4emWynpurSmosd7A"
    ) {
        install(Auth)
        install(Postgrest)
    }
}
