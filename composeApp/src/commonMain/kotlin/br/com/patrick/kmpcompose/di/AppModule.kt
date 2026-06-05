package br.com.patrick.kmpcompose.di

import br.com.patrick.kmpcompose.features.converter.data.KtorClient
import br.com.patrick.kmpcompose.features.converter.data.datasource.RemoteCurrencyDataSource
import br.com.patrick.kmpcompose.features.converter.data.repository.CurrencyRepositoryImpl
import br.com.patrick.kmpcompose.features.converter.domain.data.datasource.CurrencyDataSource
import br.com.patrick.kmpcompose.features.converter.domain.data.repository.CurrencyRepository
import br.com.patrick.kmpcompose.features.converter.domain.usecase.CurrencyUseCase
import org.koin.core.module.Module
import org.koin.dsl.module

val appModule: Module = module {
    single { KtorClient() }
    single<CurrencyDataSource> { RemoteCurrencyDataSource(get()) }
    single<CurrencyRepository> { CurrencyRepositoryImpl(get()) }
    factory { CurrencyUseCase(get()) }
}
