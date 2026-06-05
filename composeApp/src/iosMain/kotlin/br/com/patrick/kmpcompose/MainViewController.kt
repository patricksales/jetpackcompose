package br.com.patrick.kmpcompose

import androidx.compose.ui.window.ComposeUIViewController
import br.com.patrick.kmpcompose.di.initKoin

fun MainViewController() = ComposeUIViewController {
    initKoin()
    App()
}
