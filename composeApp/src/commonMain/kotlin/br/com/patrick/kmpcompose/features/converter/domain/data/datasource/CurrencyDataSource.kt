package br.com.patrick.kmpcompose.features.converter.domain.data.datasource

import br.com.patrick.kmpcompose.features.converter.data.response.CurrencyConversionResponse

interface CurrencyDataSource {
    suspend fun convertCurrency(
        fromCurrency: String,
        toCurrency: String,
        amount: Double
    ): Result<CurrencyConversionResponse>
}