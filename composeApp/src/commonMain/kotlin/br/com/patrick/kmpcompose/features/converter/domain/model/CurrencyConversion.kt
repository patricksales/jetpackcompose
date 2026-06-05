package br.com.patrick.kmpcompose.features.converter.domain.model

data class CurrencyConversion(
    val baseCode: String,
    val targetCode: String,
    val conversionRate: String,
    val conversionResult: String
)