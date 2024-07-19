package com.uefa.wordle.core.presentation.navigation

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument

internal sealed class Screens(
    private val route: String,
    val index: Int? = null,
    val navArguments: List<NamedNavArgument> = emptyList()
) {

    val name: String = route.appendArguments(navArguments)

    data object Splash : Screens(route = "/splash")

    data object WordleGame : Screens(
        route = "/wordle-game",
        navArguments = listOf(
            navArgument("tourGamedayId") { type = NavType.StringType }
        )
    ) {

        fun createRoute(tourGamedayId: Int): String {
            return name.replace(
                "tourGamedayId", tourGamedayId.toString()
            )
        }
    }

}

private fun String.appendArguments(navArguments: List<NamedNavArgument>): String {
    val mandatoryArguments = navArguments.filter { it.argument.defaultValue == null }
        .takeIf { it.isNotEmpty() }
        ?.joinToString(separator = "/", prefix = "/") { "{${it.name}}" }
        .orEmpty()
    val optionalArguments = navArguments.filter { it.argument.defaultValue != null }
        .takeIf { it.isNotEmpty() }
        ?.joinToString(separator = "&", prefix = "?") { "${it.name}={${it.name}}" }
        .orEmpty()
    return "$this$mandatoryArguments$optionalArguments"
}