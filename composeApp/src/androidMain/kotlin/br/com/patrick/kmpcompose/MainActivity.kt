package br.com.patrick.kmpcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import br.com.patrick.kmpcompose.di.appModule
import org.koin.core.context.startKoin

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        initializeKoin()
        setContent {
            App()
        }
    }
}

fun initializeKoin() {
    startKoin {
        modules(listOf(appModule, androidModule))
    }
}

@Preview
@Composable
fun AppAndroidPreview() {
    App()
}