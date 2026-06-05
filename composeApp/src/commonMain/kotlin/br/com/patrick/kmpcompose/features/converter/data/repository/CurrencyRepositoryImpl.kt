package br.com.patrick.kmpcompose.features.converter.data.repository

import br.com.patrick.kmpcompose.features.converter.domain.data.datasource.CurrencyDataSource
import br.com.patrick.kmpcompose.features.converter.domain.data.repository.CurrencyRepository
import br.com.patrick.kmpcompose.features.converter.domain.model.CurrencyConversion

class CurrencyRepositoryImpl(
    private val dataSource: CurrencyDataSource
) : CurrencyRepository {
    override suspend fun convertCurrency(
        fromCurrency: String,
        toCurrency: String,
        amount: Double
    ): Result<CurrencyConversion> {
        dataSource.convertCurrency(fromCurrency, toCurrency, amount)
            .onSuccess { response ->
                val conversion = CurrencyConversion(
                    baseCode = response.baseCode,
                    targetCode = response.targetCode,
                    conversionRate = response.conversionRate.toString(),
                    conversionResult = response.conversionResult.toString()
                )
                return Result.success(conversion)
            }.onFailure { error ->
                return Result.failure(error)
            }
        return Result.failure(Exception("Unknown error during currency conversion"))
    }
}