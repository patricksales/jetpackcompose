package br.com.patrick.kmpcompose.features.converter.data.datasource

import br.com.patrick.kmpcompose.features.converter.data.KtorClient
import br.com.patrick.kmpcompose.features.converter.data.response.CurrencyConversionResponse
import br.com.patrick.kmpcompose.features.converter.domain.data.datasource.CurrencyDataSource

class RemoteCurrencyDataSource(
    private val ktorClient: KtorClient
) : CurrencyDataSource {

    override suspend fun convertCurrency(
        fromCurrency: String,
        toCurrency: String,
        amount: Double
    ): Result<CurrencyConversionResponse> {
        runCatching {
            val response = ktorClient.convertCurrency(fromCurrency, toCurrency, amount)
            if (response.isSuccess) {
                val body = response.getOrNull()
                if (body != null) {
                    return Result.success(body)
                } else {
                    return Result.failure(Exception("Empty response body"))
                }
            } else {
                return Result.failure(Exception("API error: ${response.exceptionOrNull()}"))
            }
        }
        return Result.failure(Exception("API Exception"))
    }
}