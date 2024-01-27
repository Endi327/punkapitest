package hu.varsanyi.punkapiapp.features.beermatcher

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

const val BEER_MATCHER_NAV_ROUTE = "beerMatcher"

fun NavController.navigateToBeerMatcher() {
    this.navigate(BEER_MATCHER_NAV_ROUTE) {
        launchSingleTop = true
    }
}

fun NavGraphBuilder.beerMatcherScreen(
    navigateToBeerList: () -> Unit,
    showSnackbar: (message: String) -> Unit
) {
    composable(BEER_MATCHER_NAV_ROUTE) {
        BeerMatcherScreen(navigateToBeerList = navigateToBeerList, showSnackbar = showSnackbar)
    }
}