package com.app.studye.data.model

data class Profile(
    val id: String,
    val name: String,
    val email: String,
    val password_hash: String,
    val created_at: String,
    val updated_at: String
)
