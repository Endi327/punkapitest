package hu.varsanyi.punkapiapp.features.beerlist

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import hu.varsanyi.punkapiapp.features.beerlist.components.BeerListItem
import org.koin.androidx.compose.koinViewModel

@Composable
fun BeerListScreen(
    beerListViewModel: BeerListViewModel = koinViewModel(),
    navigateToDetails: (beerId: Int) -> Unit
) {
    val uiState by beerListViewModel.uiState.collectAsStateWithLifecycle()


    BeerListScreenContent(uiState = uiState, onBeerClicked = navigateToDetails)
}

@Composable
fun BeerListScreenContent(
    uiState: BeerListViewModel.UiState,
    onBeerClicked: (beerId: Int) -> Unit
) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(uiState.beerList, key = { beer -> beer.id }) {
            BeerListItem(imageUrl = it.imageUrl, name = it.name, onClick = { onBeerClicked(it.id) })
        }
    }
}