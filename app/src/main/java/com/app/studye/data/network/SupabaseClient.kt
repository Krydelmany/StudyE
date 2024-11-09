package com.app.studye.data.network

import com.app.studye.BuildConfig
import io.github.jan.supabase.auth.Auth
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest

object SupabaseClient {
    val client = createSupabaseClient(
        supabaseUrl = "https://eequonbvkgojqjifdrma.supabase.co",
        supabaseKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6ImVlcXVvbmJ2a2dvanFqaWZkcm1hIiwicm9sZSI6ImFub24iLCJpYXQiOjE3MzAzMzE0MTUsImV4cCI6MjA0NTkwNzQxNX0.HzdKez8PVZp3sQuzxpHNEHoX1wwnNNAZxu7cniWqz_U"
    ) {
        install(Auth)
        install(Postgrest)
    }
}
