package com.app.studye.data.model

sealed class UserState {
    object Idle : UserState()
    object Loading : UserState()
    data class Success(val message: String) : UserState()
    data class Error(val error: String) : UserState()
}
