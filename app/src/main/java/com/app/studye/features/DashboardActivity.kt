package com.app.studye.features

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.studye.R
import com.app.studye.adapters.ActivitiesAdapter
import com.app.studye.adapters.CoursesAdapter
import com.app.studye.databinding.ActivityDashboardBinding
import com.app.studye.features.settings.SettingsActivity
import com.app.studye.features.annotations.NewAnnotationActivity
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys

class DashboardActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDashboardBinding
    private lateinit var sharedPreferences: SharedPreferences

    // Adaptadores para os RecyclerViews
    private lateinit var coursesAdapter: CoursesAdapter
    private lateinit var activitiesAdapter: ActivitiesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inicializar o ViewBinding
        binding = ActivityDashboardBinding.inflate(layoutInflater)
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

        // Configurar os RecyclerViews
        setupRecyclerViews()

        // Configurar o Gráfico de Progresso
        setupProgressChart()

        // Buscar o nome completo do usuário dos SharedPreferences e exibir apenas o primeiro nome na saudação
        val fullName = sharedPreferences.getString("user_name", "Usuário") ?: "Usuário"
        val firstName = fullName.split(" ").firstOrNull() ?: "Usuário" // Obtém o primeiro nome
        val welcomeMessage = getString(R.string.welcome_message, firstName)
        binding.headerCard.findViewById<TextView>(R.id.welcomeText).text = welcomeMessage

        // Configurar o botão de Configurações
        binding.headerCard.findViewById<ImageButton>(R.id.settingsButton).setOnClickListener {
            openSettings()
        }

        // Configurar Citação Motivacional
        setupMotivationalQuote()
    }

    private fun setupRecyclerViews() {
        // Configurar RecyclerView de Cursos
        coursesAdapter = CoursesAdapter(getMockCourses())
        binding.coursesRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@DashboardActivity, LinearLayoutManager.HORIZONTAL, false)
            adapter = coursesAdapter
        }

        // Configurar RecyclerView de Próximas Atividades
        activitiesAdapter = ActivitiesAdapter(getMockActivities())
        binding.activitiesRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@DashboardActivity, LinearLayoutManager.VERTICAL, false)
            adapter = activitiesAdapter
        }
    }

    private fun setupProgressChart() {
        val barChart: BarChart = binding.progressChart

        // Exemplo de dados para o gráfico
        val entries = ArrayList<BarEntry>()
        entries.add(BarEntry(1f, 50f))
        entries.add(BarEntry(2f, 80f))
        entries.add(BarEntry(3f, 60f))
        entries.add(BarEntry(4f, 90f))
        entries.add(BarEntry(5f, 70f))

        val barDataSet = BarDataSet(entries, "Progresso Semanal")
        barDataSet.color = resources.getColor(R.color.primary, theme)

        val barData = BarData(barDataSet)
        barChart.data = barData

        // Configurações adicionais do gráfico
        barChart.description.isEnabled = false
        barChart.axisRight.isEnabled = false
        barChart.animateY(1000)
        barChart.invalidate() // Atualiza o gráfico
    }

    private fun openSettings() {
        val intent = Intent(this, SettingsActivity::class.java)
        startActivity(intent)
    }

    private fun openNewAnnotation() {
        val intent = Intent(this, NewAnnotationActivity::class.java)
        startActivity(intent)
    }

    private fun setupMotivationalQuote() {
        binding.quoteCard.findViewById<TextView>(R.id.motivationalQuote).text = "“O sucesso é a soma de pequenos esforços repetidos dia após dia.”"
    }

    // Funções para obter dados mockados (substitua com dados reais do seu backend)
    private fun getMockCourses(): List<String> {
        return listOf("Matéria 1", "Matéria 2", "Matéria 3")
    }

    private fun getMockActivities(): List<String> {
        return listOf("Atividade 1", "Atividade 2", "Atividade 3")
    }
}
