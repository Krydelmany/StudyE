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
        supabaseKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6ImVlcXVvbmJ2a2dvanFqaWZkcm1hIiwicm9sZSI6ImFub24iLCJpYXQiOjE3MzAzMzE0MTUsImV4cCI6MjA0NTkwNzQxNX0.HzdKez8PVZp3sQuzxpHNEHoX1wwnNNAZxu7cniWqz_U"
    ) {
        install(Auth)
        install(Postgrest)
    }
}
