package com.app.studye.features.auth.register

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.app.studye.SupabaseAuthViewModel
import com.app.studye.data.model.UserState
import com.app.studye.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    private val authViewModel: SupabaseAuthViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Configura o clique no botão de registro
        binding.registerButton.setOnClickListener {
            val email = binding.emailInput.text.toString().trim()
            val password = binding.passwordInput.text.toString().trim()
            val confirmPassword = binding.confirmPasswordInput.text.toString().trim()

            if (email.isEmpty()) {
                binding.emailInput.error = "Por favor, insira um email"
                binding.emailInput.requestFocus()
                return@setOnClickListener
            }

            if (password.isEmpty()) {
                binding.passwordInput.error = "Por favor, insira uma senha"
                binding.passwordInput.requestFocus()
                return@setOnClickListener
            }

            if (password != confirmPassword) {
                binding.confirmPasswordInput.error = "As senhas não coincidem"
                binding.confirmPasswordInput.requestFocus()
                return@setOnClickListener
            }

            authViewModel.signUp(email, password)
        }

        // Observa o estado do usuário
        observeViewModel()

        // Volta para a página anterior ao clicar em loginLink
        binding.loginButton.setOnClickListener {
            finish()
        }
    }

    private fun observeViewModel() {
        authViewModel.userState.observe(this, Observer { state ->
            when (state) {
                is UserState.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
                is UserState.Success -> {
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(this, state.message, Toast.LENGTH_SHORT).show()
                    finish()
                }
                is UserState.Error -> {
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(this, state.error, Toast.LENGTH_SHORT).show()
                }
                else -> {
                    binding.progressBar.visibility = View.GONE
                }
            }
        })
    }
}
