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
import io.ktor.client.request.forms.formData
import io.ktor.client.request.forms.submitFormWithBinaryData
import io.ktor.client.request.header
import io.ktor.client.request.setBody
import io.ktor.client.statement.bodyAsText
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.URLProtocol
import io.ktor.http.contentType
import io.ktor.http.headers
import io.ktor.http.path
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
    private val accessToken = ""
    val httpClient = HttpClient(engine) {
        expectSuccess = true
        install(Resources)
        install(DefaultRequest) {
            url {
                protocol = URLProtocol.HTTPS
                host = "dev.api.lifty.page"
                path("api/v1/")
            }
            headers {
                contentType(ContentType.Application.Json)
                header(HttpHeaders.Accept, ContentType.Text.EventStream)
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

    // User Info
    suspend fun getUserInfo(): UserInfoResponse = httpClient.get(resource = UserInfo()).body()

    // Diary
    suspend fun getDiary(): DiaryResponse = httpClient.get(resource = Diary()).body()

    // Chat
    suspend fun postChat(chatRequest: ChatRequest): String = httpClient.post(resource = Chat()) {
        setBody(chatRequest)
    }.bodyAsText()


    companion object {
        const val TIMEOUT_REQUEST: Long = 10_000
        const val TIMEOUT_CONNECT: Long = 10_000
        const val TIMEOUT_SOCKET: Long = 10_000
    }
}