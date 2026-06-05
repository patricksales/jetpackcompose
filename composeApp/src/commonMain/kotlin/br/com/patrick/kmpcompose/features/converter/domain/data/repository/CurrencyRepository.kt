package br.com.patrick.kmpcompose.features.converter.domain.data.repository

import br.com.patrick.kmpcompose.features.converter.domain.model.CurrencyConversion

interface CurrencyRepository {
    suspend fun convertCurrency(
        fromCurrency: String,
        toCurrency: String,
        amount: Double
    ): Result<CurrencyConversion>
}
