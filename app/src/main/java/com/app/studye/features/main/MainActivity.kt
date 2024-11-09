package com.app.studye.features.main

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.app.studye.R
import com.app.studye.features.auth.login.LoginActivity
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Configura o layout de introdução
        setContentView(R.layout.activity_intro)

        // Aguarda 2 segundos e muda para a tela de login
        lifecycleScope.launch {
            delay(2000)
            startActivity(Intent(this@MainActivity, LoginActivity::class.java))
            finish()
        }
    }
}
