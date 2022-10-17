package com.karayel.composeworkaround.core

sealed class Screen {
    abstract val route: String
}

object BooNavigation: Screen() {
    override val route: String
        get() = "boo"
}

data class FooNavigation(val userId: String): Screen() {
    override val route: String
        get() = "foo?userId=$userId"
}
