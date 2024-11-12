import com.app.studye.data.network.SupabaseClient.supabase
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.Serializable
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.postgrest.postgrest
import io.github.jan.supabase.postgrest.query.Columns

// Defina o modelo de dados correspondente à estrutura da tabela
@Serializable
data class SeuModelo(
    val id: Int,
    val body: String,
    // Adicione outros campos conforme necessário
)

fun main() = runBlocking {
    // Supondo que você já tenha uma instância do SupabaseClient chamada 'supabaseClient'
    val postgrest = supabase.postgrest

    try {
        // Realize a consulta na tabela desejada
        val resultado = postgrest["notes"]
            .select(columns = Columns.list("id", "body")) // Especifique as colunas que deseja obter
            .decodeList<SeuModelo>()

        // Exiba os resultados
        resultado.forEach { item ->
            println("ID: ${item.id}, Nome: ${item.body}")
            // Exiba outros campos conforme necessário
        }
    } catch (e: Exception) {
        println("Erro ao realizar a consulta: ${e.message}")
    }
}
