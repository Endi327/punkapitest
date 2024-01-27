package hu.varsanyi.punkapiapp.features.beermatcher

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import hu.varsanyi.punkapiapp.domain.models.Beer
import hu.varsanyi.punkapiapp.domain.usecases.GetRandomBeerUseCase
import hu.varsanyi.punkapiapp.domain.usecases.SaveLikedBeerUseCase
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class BeerMatcherViewModel(
    private val getRandomBeerUseCase: GetRandomBeerUseCase,
    private val saveLikedBeerUseCase: SaveLikedBeerUseCase
) : ViewModel() {

    data class UiState(
        val isLoading: Boolean = false,
        val currentBeer: Beer? = null,
        val nextBeer: Beer? = null,
        val judgedBeerCount: Int = 0
    )

    sealed class Event {
        data class JudgeBeer(val didLike: Boolean) : Event()
    }

    sealed class News {
        data object BeerJudgeLimitReached : News()
        data object BeerSaved: News()
    }

    private val _uiState = MutableStateFlow(UiState())
    private val _uiNews = MutableSharedFlow<News>()


    init {
        viewModelScope.launch {
            getNextBeer()
            _uiState.update {
                it.copy(currentBeer = it.nextBeer)
            }
            getNextBeer()
        }
    }

    val uiState = _uiState.asStateFlow()
    val uiNews = _uiNews.asSharedFlow()

    fun onEvent(event: Event) {
        viewModelScope.launch {
            when (event) {
                is Event.JudgeBeer -> {
                    if (event.didLike) saveLikedBeer(_uiState.value.currentBeer)
                    _uiState.update {
                        it.copy(
                            judgedBeerCount = it.judgedBeerCount + 1,
                            currentBeer = it.nextBeer
                        )
                    }


                    if (_uiState.value.judgedBeerCount == 10) {
                        _uiNews.emit(News.BeerJudgeLimitReached)
                    } else {
                        getNextBeer()
                    }
                }
            }
        }
    }

    private suspend fun getNextBeer() {
        _uiState.update {
            it.copy(isLoading = true)
        }
        getRandomBeerUseCase.invoke(Unit)
            .foldResult(
                onSuccess = { beer ->
                    _uiState.update {
                        it.copy(nextBeer = beer)
                    }
                },
                onFailure = {

                }
            )
    }

    private suspend fun saveLikedBeer(beer: Beer?) {
        saveLikedBeerUseCase.invoke(beer)
            .foldResult(
                onSuccess = {
                    _uiNews.emit(News.BeerSaved)
                },
                onFailure = {

                }
            )
    }
}