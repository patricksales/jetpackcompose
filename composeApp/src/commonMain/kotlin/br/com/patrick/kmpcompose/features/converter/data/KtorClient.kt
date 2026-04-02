package br.com.patrick.kmpcompose.features.converter.data

import br.com.patrick.kmpcompose.features.converter.data.response.CurrencyConversionResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.logging.SIMPLE
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

private const val API_KEY = ""

object ApiClient {

    val client = HttpClient {
        expectSuccess = true

        defaultRequest {
            url("https://v6.exchangerate-api.com/v6/$API_KEY/")
        }

        install(ContentNegotiation) {
            json(
                Json {
                    prettyPrint = true
                    isLenient = true
                    ignoreUnknownKeys = true
                }
            )
        }

        install(Logging) {
            logger = Logger.SIMPLE
        }
    }
}

class KtorClient(private val client: HttpClient = ApiClient.client) {

    suspend fun convertCurrency(
        fromCurrency: String,
        toCurrency: String,
        amount: Double
    ): Result<CurrencyConversionResponse> {
        return client.get("pair/$fromCurrency/$toCurrency/$amount").body()
    }
}