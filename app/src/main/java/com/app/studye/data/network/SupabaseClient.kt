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
        supabaseKey = "Adicionar"
    ) {
        install(Auth)
        install(Postgrest)
    }
}
