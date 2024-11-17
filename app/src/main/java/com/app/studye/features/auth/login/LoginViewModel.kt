package com.app.studye.features.auth.login

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.app.studye.data.model.UserState
import com.app.studye.data.network.SupabaseClient.supabase
import io.github.jan.supabase.auth.auth
import io.github.jan.supabase.auth.exception.AuthRestException
import io.github.jan.supabase.auth.providers.builtin.Email
import io.github.jan.supabase.exceptions.HttpRequestException
import kotlinx.coroutines.launch

class LoginViewModel(application: Application) : AndroidViewModel(application) {

    private val _userState = MutableLiveData<UserState>()
    val userState: LiveData<UserState> = _userState

    fun login(userEmail: String, userPassword: String) {
        _userState.value = UserState.Loading

        viewModelScope.launch {
            try {
                // Tenta realizar o login
                supabase.auth.signInWith(Email) {
                    email = userEmail
                    password = userPassword
                }

                // Atualiza o estado para sucesso no login
                _userState.value = UserState.Success("Login realizado com sucesso")

            } catch (e: AuthRestException) {
                // Trata erro específico de autenticação
                _userState.value = UserState.Error("Erro de autenticação: ${e.message}")

            } catch (e: HttpRequestException) {
                // Trata falhas de rede
                _userState.value = UserState.Error("Falha de rede: verifique sua conexão com a internet.")

            } catch (e: Exception) {
                // Trata outros erros
                _userState.value = UserState.Error("Erro inesperado: ${e.message}")
            }
        }
    }
}
