package br.com.patrick.kmpcompose.features.converter.presentation.ui.model

sealed interface ConverterFormEvent {
    data class OnFromCurrencySelected(val currency: String) : ConverterFormEvent
    data class OnToCurrencySelected(val currency: String) : ConverterFormEvent
    data class OnFromCurrencyAmountChanged(val amount: String) : ConverterFormEvent
    data object SendConverterForm : ConverterFormEvent
}