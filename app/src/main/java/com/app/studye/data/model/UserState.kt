package com.app.studye.data.model

sealed class UserState {
    object Idle : UserState()
    object Loading : UserState()
    data class Success(val message: String) : UserState()
    data class ProfileSaved(val message: String = "Perfil salvo com sucesso") : UserState()
    data class ProfileSaveError(val error: String) : UserState()
    data class Error(val error: String, val errorCode: String? = null) : UserState()
}
