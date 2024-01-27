package hu.varsanyi.punkapiapp.features.beermatcher

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import hu.varsanyi.punkapiapp.features.beermatcher.components.BeerMatcherCard
import org.koin.androidx.compose.koinViewModel

@Composable
fun BeerMatcherScreen(
    viewModel: BeerMatcherViewModel = koinViewModel(),
    navigateToBeerList: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(key1 = true) {
        viewModel.uiNews.collect { news ->
            when (news) {
                BeerMatcherViewModel.News.BeerJudgeLimitReached -> navigateToBeerList()
            }
        }
    }

    BeerMatcherScreenContent(uiState = uiState, onEvent = viewModel::onEvent)
}

@Composable
fun BeerMatcherScreenContent(
    uiState: BeerMatcherViewModel.UiState,
    onEvent: (BeerMatcherViewModel.Event) -> Unit
) {
    Box {
        uiState.currentBeer?.let {
            BeerMatcherCard(
                imageUrl = it.imageUrl,
                name = it.name,
                tagline = it.tagline,
                dismissClicked = { /*TODO*/ }) {

            }
        }
    }
}