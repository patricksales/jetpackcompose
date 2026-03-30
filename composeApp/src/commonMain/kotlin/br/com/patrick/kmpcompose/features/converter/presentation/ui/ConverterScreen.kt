package br.com.patrick.kmpcompose.features.converter.presentation.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import br.com.patrick.kmpcompose.components.CurrencyField
import br.com.patrick.kmpcompose.features.converter.presentation.ui.model.ConverterFormEvent
import br.com.patrick.kmpcompose.features.converter.presentation.ui.model.ConverterFormState
import br.com.patrick.kmpcompose.features.converter.presentation.viewmodel.ConverterViewModel
import kmpcompose.composeapp.generated.resources.Res
import kmpcompose.composeapp.generated.resources.ic_south
import org.jetbrains.compose.resources.painterResource

@Composable
fun ConverterScreen() {
    val viewModel = viewModel<ConverterViewModel>()

    val formState by viewModel.formState.collectAsStateWithLifecycle()
    val conversionState by viewModel.conversionState.collectAsStateWithLifecycle()

    ConverterContent(
        formState = formState,
        conversionState,
        onFormEvent = viewModel::dispatchViewEvent
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ConverterContent(
    formState: ConverterFormState,
    conversionState: ConverterViewModel.ConversionState,
    onFormEvent: (ConverterFormEvent) -> Unit
) {
    Scaffold(
        modifier = Modifier.imePadding(),
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Converter Currency",
                        fontWeight = FontWeight.SemiBold
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background
                )
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp)
                .consumeWindowInsets(innerPadding)
                .systemBarsPadding()
        ) {
            Box(contentAlignment = Alignment.Center) {
                Column {
                    CurrencyField(
                        currencies = formState.fromCurrenciesList,
                        selectedCurrency = formState.fromCurrencySelected,
                        currencyAmount = formState.fromCurrencyAmount,
                        onCurrencySelected = {
                            onFormEvent(
                                ConverterFormEvent.OnFromCurrencySelected(it)
                            )
                        },
                        onCurrencyAmountChanged = {
                            onFormEvent(
                                ConverterFormEvent.OnFromCurrencyAmountChanged(it)
                            )
                        }
                    )

                    CurrencyField(
                        currencies = formState.toCurrenciesList,
                        selectedCurrency = formState.toCurrencySelected,
                        currencyAmount = formState.toCurrencyAmount,
                        onCurrencySelected = {
                            onFormEvent(
                                ConverterFormEvent.OnToCurrencySelected(it)
                            )
                        },
                        onCurrencyAmountChanged = {},
                        modifier = Modifier.padding(top = 8.dp)
                    )
                }
                Surface(
                    shape = CircleShape,
                    color = MaterialTheme.colorScheme.primary,
                    border = BorderStroke(width = 0.5.dp, color = Color.LightGray),
                ) {
                    Icon(
                        painter = painterResource(Res.drawable.ic_south),
                        contentDescription = null,
                        modifier = Modifier.padding(4.dp)
                    )
                }
            }


            Box(
                modifier = Modifier.fillMaxWidth().padding(top = 32.dp),
                contentAlignment = Alignment.Center
            ) {
                when (conversionState) {
                    is ConverterViewModel.ConversionState.Idle -> {

                    }

                    is ConverterViewModel.ConversionState.Loading -> {
                        CircularProgressIndicator()
                    }

                    is ConverterViewModel.ConversionState.Success -> {
                        Text(
                            "Conversion successful!",
                            color = Color.Green,
                            modifier = Modifier.align(Alignment.Center)
                        )
                    }

                    is ConverterViewModel.ConversionState.Error -> {
                        Text(
                            "Conversion failed. Please try again.",
                            color = MaterialTheme.colorScheme.error
                        )
                    }
                }
            }

            Box(modifier = Modifier.weight(1f))
            Button(
                onClick = { },
                modifier = Modifier
                    .fillMaxWidth(),
                shape = MaterialTheme.shapes.medium,
                contentPadding = PaddingValues(vertical = 16.dp),
            ) {
                Text("Converter Currency")
            }
        }
    }
}

@Preview
@Composable
fun ConverterScreenPreview() {
    MaterialTheme {
        ConverterContent(
            formState = ConverterFormState(
                fromCurrenciesList = listOf("USD", "EUR", "JPY"),
                toCurrenciesList = listOf("USD", "EUR", "JPY"),
                fromCurrencySelected = "USD",
                toCurrencySelected = "EUR"
            ),
            conversionState = ConverterViewModel.ConversionState.Idle,
            onFormEvent = {}
        )
    }
}