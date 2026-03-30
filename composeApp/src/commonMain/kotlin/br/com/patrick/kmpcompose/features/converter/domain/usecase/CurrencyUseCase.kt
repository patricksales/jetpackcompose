package br.com.patrick.kmpcompose.features.converter.domain.usecase

import br.com.patrick.kmpcompose.features.converter.domain.data.repository.CurrencyRepository

class CurrencyUseCase(private val repository: CurrencyRepository) {
    suspend operator fun invoke(
        fromCurrency: String,
        toCurrency: String,
        amount: Double
    ) = repository.convertCurrency(fromCurrency, toCurrency, amount)
}