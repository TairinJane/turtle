import io.ktor.client.*
import io.ktor.client.request.*
import kotlinx.coroutines.runBlocking

class RestClient {
    private val client = HttpClient()
    private val serverPath = "http://localhost:8080/api"

    fun get(path: String, param: Pair<String, Any>? = null): String {
        val response: String = runBlocking {
            client.get("$serverPath$path") {
                param?.let { parameter(param.first, param.second) }
            }
        }
        return response
    }
}