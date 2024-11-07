package com.app.studye

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Configura o layout de introdução
        setContentView(R.layout.activity_intro)

        // Aguarda 2 segundos e muda para a tela de login
        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish() // Fecha a atividade de introdução
        }, 2000) // 2000 milissegundos = 2 segundos
    }
}
