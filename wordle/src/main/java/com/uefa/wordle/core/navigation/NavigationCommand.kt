package com.uefa.wordle.core.navigation

import androidx.navigation.NavOptions

sealed class NavigationCommand {
  data object NavigateUp : NavigationCommand()
}

sealed class ComposeNavigationCommand : NavigationCommand() {
  data class NavigateToRoute(val route: String, val options: NavOptions? = null) :
    ComposeNavigationCommand()

  data class NavigateUpWithResult<T>(
    val key: String,
    val result: T,
    val route: String? = null
  ) : ComposeNavigationCommand()

  data class PopUpToRoute(val route: String, val inclusive: Boolean) : ComposeNavigationCommand()
}
