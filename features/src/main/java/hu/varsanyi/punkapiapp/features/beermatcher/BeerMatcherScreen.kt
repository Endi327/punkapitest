package hu.varsanyi.punkapiapp.features.beermatcher

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.fadeIn
import androidx.compose.animation.togetherWith
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import hu.varsanyi.punkapiapp.features.beermatcher.components.BeerMatcherCard
import org.koin.androidx.compose.koinViewModel

@Composable
fun BeerMatcherScreen(
    viewModel: BeerMatcherViewModel = koinViewModel(),
    navigateToBeerList: () -> Unit,
    showSnackbar: (message: String) -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(key1 = true) {
        viewModel.uiNews.collect { news ->
            when (news) {
                BeerMatcherViewModel.News.BeerJudgeLimitReached -> navigateToBeerList()
                BeerMatcherViewModel.News.BeerSaved -> showSnackbar("Beer saved")
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
    var slideDirection by remember {
        mutableStateOf(AnimatedContentTransitionScope.SlideDirection.Right)
    }
    AnimatedContent(targetState = uiState.currentBeer, transitionSpec = {
        fadeIn() togetherWith slideOutOfContainer(
            slideDirection
        )
    }, label = "") { currentBeer ->
        currentBeer?.let {
            BeerMatcherCard(
                imageUrl = it.imageUrl,
                name = it.name,
                tagline = it.tagline,
                dismissClicked = {
                    slideDirection = AnimatedContentTransitionScope.SlideDirection.Left
                    onEvent(BeerMatcherViewModel.Event.JudgeBeer(didLike = false))
                },
                likeClicked = {
                    slideDirection = AnimatedContentTransitionScope.SlideDirection.Right
                    onEvent(BeerMatcherViewModel.Event.JudgeBeer(didLike = true))
                })
        }
    }
}