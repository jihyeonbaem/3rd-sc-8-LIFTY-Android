package page.lifty.gdsclifty.core.network.ktor

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.HttpResponseValidator
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.observer.ResponseObserver
import io.ktor.client.plugins.resources.Resources
import io.ktor.client.plugins.resources.get
import io.ktor.client.plugins.resources.post
import io.ktor.client.request.header
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.URLProtocol
import io.ktor.http.headers
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import page.lifty.gdsclifty.core.network.api.Chat
import page.lifty.gdsclifty.core.network.api.Diary
import page.lifty.gdsclifty.core.network.api.UserInfo
import page.lifty.gdsclifty.core.network.model.request.ChatRequest
import page.lifty.gdsclifty.core.network.model.response.DiaryResponse
import page.lifty.gdsclifty.core.network.model.response.UserInfoResponse
import javax.inject.Inject

class Ktor @Inject constructor(
    engine: HttpClientEngine,
) {
    private val BASE_URL = "dev.api.lifty.page"
    private val accessToken =
        "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJnZHNjbGlmdHl0ZXN0QGdtYWlsLmNvbSIsImF1dGgiOiJST0xFX1VTRVIiLCJleHAiOjE3MDg3OTc4MTZ9.mHjtLo_JvZ0JXOcoSTMXozU_uuhv_4SumPPT8tDVzPCt5iDMDafF0QM1gTfpEyvbELQxmg3TZcQmDRjQAhJ1jA"

    val httpClient = HttpClient(engine) {
        expectSuccess = true
        install(Resources)
        install(DefaultRequest) {
            host = BASE_URL
            url {
                protocol = URLProtocol.HTTPS
            }
            headers {
                header(HttpHeaders.Accept, ContentType.Application.Json)
                header(HttpHeaders.AcceptCharset, Charsets.UTF_8)
                header(HttpHeaders.Authorization, "Bearer $accessToken")
            }
        }
        install(ContentNegotiation) {
            json(Json {
                prettyPrint = true
                isLenient = true
                encodeDefaults = true
                ignoreUnknownKeys = true
                coerceInputValues = true
            })
        }
        install(Logging) {

        }
        install(HttpTimeout) {
            requestTimeoutMillis = TIMEOUT_REQUEST
            connectTimeoutMillis = TIMEOUT_CONNECT
            socketTimeoutMillis = TIMEOUT_SOCKET
        }
        install(ResponseObserver) {
            onResponse { response ->
            }
        }
        HttpResponseValidator { }
    }

    suspend fun getUserInfo(): UserInfoResponse = httpClient.get(resource = UserInfo).body()
    suspend fun getDiary(): DiaryResponse = httpClient.get(resource = Diary).body()

    suspend fun postChat(): ChatRequest = httpClient.post(resource = Chat).body()

    companion object {
        const val TIMEOUT_REQUEST: Long = 10_000
        const val TIMEOUT_CONNECT: Long = 10_000
        const val TIMEOUT_SOCKET: Long = 10_000
    }
}