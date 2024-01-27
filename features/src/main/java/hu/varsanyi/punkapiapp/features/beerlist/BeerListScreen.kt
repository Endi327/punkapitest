package hu.varsanyi.punkapiapp.features.beerlist

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
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
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(12.dp)
    ) {
        items(uiState.beerList, key = { beer -> beer.id }) {
            BeerListItem(
                modifier = Modifier.fillMaxWidth(),
                imageUrl = it.imageUrl,
                name = it.name,
                onClick = { onBeerClicked(it.id) })
        }
    }
}