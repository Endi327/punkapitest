package hu.varsanyi.punkapiapp.features.beerdetails

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import hu.varsanyi.punkapiapp.domain.models.Beer
import org.koin.androidx.compose.koinViewModel

@Composable
fun BeerDetailsScreen(
    beerId: Int?,
    beerDetailsViewModel: BeerDetailsViewModel = koinViewModel()
) {
    val uiState by beerDetailsViewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(key1 = true) {
        beerDetailsViewModel.onEvent(BeerDetailsViewModel.Event.GetBeerDetails(beerId))
    }

    uiState.beer?.let {
        BeerDetailsScreenContent(beer = it)
    }
}

@Composable
fun BeerDetailsScreenContent(
    beer: Beer
) {
    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        AsyncImage(
            modifier = Modifier
                .fillMaxWidth()
                .height(350.dp),
            model = beer.imageUrl,
            contentDescription = null
        )

        Spacer(modifier = Modifier.height(12.dp))

        Text(text = beer.name, fontSize = 32.sp, fontWeight = FontWeight.Bold)

        Spacer(modifier = Modifier.height(12.dp))

        Text(modifier = Modifier.padding(horizontal = 12.dp), text = beer.description)
    }
}