package hu.varsanyi.punkapiapp.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import hu.varsanyi.punkapiapp.features.beerlist.beerListScreen
import hu.varsanyi.punkapiapp.features.beerlist.navigateToBeerList
import hu.varsanyi.punkapiapp.features.beermatcher.BEER_MATCHER_NAV_ROUTE
import hu.varsanyi.punkapiapp.features.beermatcher.beerMatcherScreen

@Composable
fun PunkApiAppNavigation(
    navController: NavHostController
) {
    NavHost(navController = navController, startDestination = BEER_MATCHER_NAV_ROUTE) {
        beerMatcherScreen(navigateToBeerList = {
            navController.navigateToBeerList()
        }, showSnackbar = {})

        beerListScreen(navigateToDetails = {})
    }
}