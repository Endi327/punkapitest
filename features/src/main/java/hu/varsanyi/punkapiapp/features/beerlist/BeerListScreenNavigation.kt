package hu.varsanyi.punkapiapp.features.beerlist

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

private const val BEER_LIST_NAV_ROUTE = "beerList"

fun NavController.navigateToBeerList() {
    this.navigate(BEER_LIST_NAV_ROUTE) {
        launchSingleTop = true
    }
}

fun NavGraphBuilder.beerListScreen(
    navigateToDetails: (beerId: Int) -> Unit
) {
    composable(BEER_LIST_NAV_ROUTE) {
        BeerListScreen(navigateToDetails = navigateToDetails)
    }
}