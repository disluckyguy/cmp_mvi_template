package io.github.mostafa.mvitemplate

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform