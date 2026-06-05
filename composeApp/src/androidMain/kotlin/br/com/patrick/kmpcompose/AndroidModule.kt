package br.com.patrick.kmpcompose

import br.com.patrick.kmpcompose.features.converter.presentation.viewmodel.ConverterViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val androidModule = module {
    viewModel{ ConverterViewModel(get(), get()) }
}