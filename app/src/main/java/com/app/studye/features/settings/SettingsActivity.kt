package com.app.studye.features.settings

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.app.studye.databinding.ActivitySettingsBinding
import com.app.studye.features.auth.login.LoginActivity
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys
import com.app.studye.R

class SettingsActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySettingsBinding
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingsBinding.inflate(layoutInflater)
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

        // Configurar a ActionBar (opcional)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Configurações"

        // Configurar o título da Toolbar
        binding.toolbar.title = "Configurações"

        // Configurar o comportamento do botão de navegação (seta para voltar)
        binding.toolbar.setNavigationOnClickListener {
            finish() // Finaliza a Activity atual, voltando para a anterior
        }

        // Configurar o botão de Logout
        binding.logoutButton.setOnClickListener {
            showLogoutConfirmationDialog()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }

    private fun showLogoutConfirmationDialog() {
        // Inflate o layout customizado para o diálogo
        val dialogView = layoutInflater.inflate(R.layout.dialog_logout_confirmation, null)

        // Crie o AlertDialog com o layout customizado
        val dialog = AlertDialog.Builder(this)
            .setView(dialogView)
            .create()

        // Configure os botões de ação
        dialogView.findViewById<Button>(R.id.positiveButton).setOnClickListener {
            performLogout()
            dialog.dismiss()
        }

        dialogView.findViewById<Button>(R.id.negativeButton).setOnClickListener {
            dialog.dismiss()
        }

        // Exiba o diálogo
        dialog.show()
    }


    private fun performLogout() {
        // Limpar dados de autenticação
        clearUserSession()

        // Exibir mensagem de logout (opcional)
        Toast.makeText(this, "Você foi deslogado com sucesso.", Toast.LENGTH_SHORT).show()

        // Redirecionar para a tela de Login
        val intent = Intent(this, LoginActivity::class.java)
        // Limpar a pilha de atividades para impedir voltar
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
        finish()
    }

    private fun clearUserSession() {
        with(sharedPreferences.edit()) {
            clear()
            apply()
        }
    }
}
