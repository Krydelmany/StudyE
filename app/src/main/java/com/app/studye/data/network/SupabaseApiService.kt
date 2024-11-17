package com.app.studye.data.network

import com.app.studye.data.model.Profile
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface SupabaseApiService {
    @GET("/rest/v1/profiles")
    suspend fun getProfiles(
        @Header("apikey") apiKey: String,
        @Header("Authorization") authToken: String,
        @Query("select") selectFields: String = "*"
    ): Response<List<Profile>>
}