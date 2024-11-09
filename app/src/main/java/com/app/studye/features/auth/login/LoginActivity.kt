package com.app.studye.features.auth.login

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.app.studye.data.model.UserState
import com.app.studye.databinding.ActivityLoginBinding
import com.app.studye.features.auth.register.RegisterActivity
import com.app.studye.features.main.MainActivity

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private val loginViewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

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
                    // Navegar para a MainActivity ou tela principal
                    val intent = Intent(this, MainActivity::class.java)
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
}
