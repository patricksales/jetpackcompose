package br.com.patrick.kmpcompose.features.converter.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.patrick.kmpcompose.features.converter.domain.data.repository.CurrencyRepository
import br.com.patrick.kmpcompose.features.converter.domain.usecase.CurrencyUseCase
import br.com.patrick.kmpcompose.features.converter.presentation.ui.model.ConverterFormEvent
import br.com.patrick.kmpcompose.features.converter.presentation.ui.model.ConverterFormState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ConverterViewModel(
    private val currencyRepository: CurrencyRepository,
    private val currencyUseCase: CurrencyUseCase
) : ViewModel() {

    private val _formState = MutableStateFlow(ConverterFormState())
    val formState = _formState.asStateFlow()

    private val _conversionState = MutableStateFlow<ConversionState>(ConversionState.Idle)
    val conversionState = _conversionState.asStateFlow()

    init {
        _formState.update {
            it.copy(
                fromCurrenciesList = listOf("USD", "EUR", "BRL"),
                toCurrenciesList = listOf("BRL", "EUR", "USD"),
                fromCurrencySelected = "USD",
                toCurrencySelected = "BRL"
            )
        }
    }

    fun dispatchViewEvent(event: ConverterFormEvent) {
        when (event) {
            is ConverterFormEvent.OnFromCurrencySelected -> {
                _formState.update {
                    it.copy(
                        fromCurrencySelected = event.currency
                    )
                }
            }

            is ConverterFormEvent.OnToCurrencySelected -> {
                _formState.update {
                    it.copy(
                        toCurrencySelected = event.currency
                    )
                }
            }

            is ConverterFormEvent.OnFromCurrencyAmountChanged -> {
                _formState.update {
                    it.copy(
                        fromCurrencyAmount = event.amount
                    )
                }
            }

            is ConverterFormEvent.SendConverterForm -> {
                convertCurrency()
            }

        }
    }

    private fun convertCurrency() {

        viewModelScope.launch {
            val fromCurrency = formState.value.fromCurrencySelected
            val toCurrency = formState.value.toCurrencySelected
            val amount = formState.value.fromCurrencyAmount.toDoubleOrNull()

            if (fromCurrency.isNotBlank() && toCurrency.isNotBlank() && amount != null) {

                _conversionState.update { ConversionState.Loading }

                // Simulate conversion logic

                currencyRepository.convertCurrency(fromCurrency, toCurrency, amount)
                    .fold(
                        onSuccess = { conversion ->
                            _formState.update {
                                it.copy(toCurrencyAmount = conversion.conversionResult)
                            }
                            _conversionState.update {
                                ConversionState.Success
                            }
                        },
                        onFailure = { error ->
                            _conversionState.update {
                                ConversionState.Error(error.message ?: "Conversion failed")
                            }
                        }
                    )

            } else {
                _conversionState.update { ConversionState.Error("Invalid input") }
            }
        }
    }

    sealed interface ConversionState {
        object Idle : ConversionState
        object Loading : ConversionState
        object Success : ConversionState
        data class Error(val message: String) : ConversionState
    }
}