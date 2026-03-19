package br.com.patrick.kmpcompose

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform