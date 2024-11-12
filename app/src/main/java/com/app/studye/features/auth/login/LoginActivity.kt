package com.app.studye.features.auth.login

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.app.studye.data.model.UserState
import com.app.studye.databinding.ActivityLoginBinding
import com.app.studye.features.DashboardActivity
import com.app.studye.features.auth.register.RegisterActivity
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys
import android.util.Log

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private val loginViewModel: LoginViewModel by viewModels()
    private lateinit var sharedPreferences: SharedPreferences


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Inicializar SharedPreferences Encriptados
        val masterKeyAlias = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC)
        sharedPreferences = EncryptedSharedPreferences.create(
            "secure_prefs",
            masterKeyAlias,
            applicationContext,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )

        // Carregar credenciais salvas, se existirem
        loadSavedCredentials()

        // Configurações iniciais
        setupListeners()
        observeViewModel()
    }

    private fun setupListeners() {
        // Navegar para a tela de cadastro
        binding.signUpButton.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

        // Ação do botão de login
        binding.loginButton.setOnClickListener {
            val email = binding.emailInput.text.toString().trim()
            val password = binding.passwordInput.text.toString()

            if (validateInput(email, password)) {
                loginViewModel.login(email, password)
            }
        }

        // Esqueceu a senha (implementar posteriormente)
        binding.forgotPasswordButton.setOnClickListener {
            // Implementar funcionalidade de recuperação de senha
            Toast.makeText(this, "Funcionalidade em desenvolvimento", Toast.LENGTH_SHORT).show()
        }
    }

    private fun observeViewModel() {
        loginViewModel.userState.observe(this) { state ->
            when (state) {
                is UserState.Loading -> {
                    // Mostrar indicador de carregamento
                    binding.loginButton.isEnabled = false
                    // Você pode adicionar um ProgressBar se desejar
                }
                is UserState.Success -> {
                    binding.loginButton.isEnabled = true
                    // Salvar credenciais se o CheckBox estiver marcado
                    if (binding.rememberMeCheckBox.isChecked) {
                        saveCredentials(binding.emailInput.text.toString().trim(), binding.passwordInput.text.toString())
                    } else {
                        clearCredentials()
                    }
                    // Definir o flag de login
                    with(sharedPreferences.edit()) {
                        putBoolean("is_logged_in", true)
                        apply()
                    }
                    // Navegar para a Dashboard ou tela principal
                    val intent = Intent(this, DashboardActivity::class.java)
                    startActivity(intent)
                    finish()
                }
                is UserState.Error -> {
                    binding.loginButton.isEnabled = true
                    // Mostrar mensagem de erro
                    Toast.makeText(this, state.error, Toast.LENGTH_LONG).show()
                }
                else -> {
                    // Estado Idle ou desconhecido
                    binding.loginButton.isEnabled = true
                }
            }
        }
    }

    private fun validateInput(email: String, password: String): Boolean {
        var isValid = true

        if (email.isEmpty()) {
            binding.emailInputLayout.error = "Por favor, insira seu email"
            isValid = false
        } else {
            binding.emailInputLayout.error = null
        }

        if (password.isEmpty()) {
            binding.passwordInputLayout.error = "Por favor, insira sua senha"
            isValid = false
        } else {
            binding.passwordInputLayout.error = null
        }

        return isValid
    }

    private fun saveCredentials(email: String, password: String) {
        with(sharedPreferences.edit()) {
            putString("email", email)
            putString("password", password)
            apply()
        }
        Log.d("LoginActivity", "Credenciais salvas")
    }

    private fun loadSavedCredentials() {
        val savedEmail = sharedPreferences.getString("email", null)
        val savedPassword = sharedPreferences.getString("password", null)

        if (savedEmail != null && savedPassword != null) {
            binding.emailInput.setText(savedEmail)
            binding.passwordInput.setText(savedPassword)
            binding.rememberMeCheckBox.isChecked = true
            // Opcional: Auto-login
            loginViewModel.login(savedEmail, savedPassword)
        }
    }

    private fun clearCredentials() {
        with(sharedPreferences.edit()) {
            remove("email")
            remove("password")
            apply()
        }
        Log.d("LoginActivity", "Credenciais limpas")
    }
}
