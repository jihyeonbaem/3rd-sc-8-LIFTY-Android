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
import io.ktor.http.path
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import page.lifty.gdsclifty.core.network.api.Chat
import page.lifty.gdsclifty.core.network.api.User
import page.lifty.gdsclifty.core.network.model.response.UserResponse
import javax.inject.Inject

class Ktor @Inject constructor(
    engine: HttpClientEngine,
) {
    private val accessToken =
        "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJnZHNjbGlmdHl0ZXN0QGdtYWlsLmNvbSIsImF1dGgiOiJST0xFX1VTRVIiLCJleHAiOjE3MDg3ODEzMjl9.HX2L-vNC7U7J8l-A59geclSh07ENzo5UqWcYT9CuGsVIAVOC8LUCGKzyB4IJfRMki7c2_iJKY7MPAWx6TRM1Jg"

    val httpClient = HttpClient(engine) {
        expectSuccess = true
        install(Resources)
        install(DefaultRequest) {
            url {

                protocol = URLProtocol.HTTPS
                host = "dev.api.lifty.page"
                path("/api/v1/")
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

    suspend fun postChat() = httpClient.post(Chat())
    suspend fun getUser() = httpClient.get(User()).body<UserResponse>()

    companion object {
        const val TIMEOUT_REQUEST: Long = 10_000
        const val TIMEOUT_CONNECT: Long = 10_000
        const val TIMEOUT_SOCKET: Long = 10_000
    }
}