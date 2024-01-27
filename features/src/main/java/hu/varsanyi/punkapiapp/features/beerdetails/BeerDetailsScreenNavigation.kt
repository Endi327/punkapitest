package hu.varsanyi.punkapiapp.features.beerdetails

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

private const val BEER_DETAILS_NAV_ROUTE = "beerDetails"
private const val BEER_ID_ARG = "beerId"

fun NavController.navigateToBeerDetails(beerId: Int) {
    this.navigate("$BEER_DETAILS_NAV_ROUTE/$beerId")
}

fun NavGraphBuilder.beerDetailsScreen() {
    composable(
        route = "$BEER_DETAILS_NAV_ROUTE/{$BEER_ID_ARG}",
        arguments = listOf(
            navArgument(BEER_ID_ARG) { type = NavType.IntType }
        )) {
        val beerId = it.arguments?.getInt(BEER_ID_ARG)
        BeerDetailsScreen(
            beerId
        )
    }
}