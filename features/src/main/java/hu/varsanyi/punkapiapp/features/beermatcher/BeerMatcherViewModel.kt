package hu.varsanyi.punkapiapp.features.beermatcher

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import hu.varsanyi.punkapiapp.domain.models.Beer
import hu.varsanyi.punkapiapp.domain.usecases.GetRandomBeerUseCase
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class BeerMatcherViewModel(
    private val getRandomBeerUseCase: GetRandomBeerUseCase
) : ViewModel() {

    data class UiState(
        val isLoading: Boolean = false,
        val currentBeer: Beer? = null,
        val nextBeer: Beer? = null,
        val judgedBeerCount: Int = 0
    )

    sealed class Event {
        data object JudgeBeer : Event()
    }

    sealed class News {
        data object BeerJudgeLimitReached : News()
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
                Event.JudgeBeer -> {
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
}