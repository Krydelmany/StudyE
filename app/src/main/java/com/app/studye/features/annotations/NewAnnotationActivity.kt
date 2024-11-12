package com.app.studye.features.annotations

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.app.studye.databinding.ActivityNewAnnotationBinding

class NewAnnotationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNewAnnotationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewAnnotationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Configurar a ActionBar (opcional)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Nova Anotação"

        // Implementar lógica para criar uma nova anotação
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
                // Salvar a anotação ou enviar para o backend
                // Exemplo:
                // saveAnnotation(annotationText)
                finish() // Fechar a Activity após salvar
            } else {
                // Mostrar mensagem de erro
                binding.annotationEditText.error = "Por favor, insira o texto da anotação"
            }
        }
    }

    // Função exemplo para salvar a anotação (implemente conforme necessário)
    /*
    private fun saveAnnotation(text: String) {
        // Implementar a lógica para salvar a anotação
    }
    */
}
