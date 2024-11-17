package com.app.studye.features.annotations

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.app.studye.databinding.ActivityNewAnnotationBinding
// TODO - Adicionar código ao fazer tela de anotaçoes
class NewAnnotationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNewAnnotationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewAnnotationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Nova Anotação"

        setupCreateAnnotation()
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }

    private fun setupCreateAnnotation() {
        binding.createAnnotationButton.setOnClickListener {
            val annotationText = binding.annotationEditText.text.toString().trim()
            if (annotationText.isNotEmpty()) {
                finish() // fecha a janela ao salvar
            } else {
                // mostra mensagem de erro
                binding.annotationEditText.error = "Por favor, insira o texto da anotação"
            }
        }
    }
}
