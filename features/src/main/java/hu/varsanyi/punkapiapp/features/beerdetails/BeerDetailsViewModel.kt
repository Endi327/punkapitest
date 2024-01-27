package hu.varsanyi.punkapiapp.features.beerdetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import hu.varsanyi.punkapiapp.domain.models.Beer
import hu.varsanyi.punkapiapp.domain.usecases.GetBeerByIdUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class BeerDetailsViewModel(
    private val getBeerByIdUseCase: GetBeerByIdUseCase
) : ViewModel() {

    data class UiState(
        val beer: Beer? = null
    )

    sealed class Event {
        data class GetBeerDetails(val beerId: Int?) : Event()
    }

    private val _uiState = MutableStateFlow(UiState())
    val uiState = _uiState.asStateFlow()

    fun onEvent(event: Event) {
        when (event) {
            is Event.GetBeerDetails -> {
                viewModelScope.launch {
                    getBeerByIdUseCase.invoke(event.beerId)
                        .foldResult(
                            onSuccess = { beer ->
                                _uiState.update {
                                    it.copy(beer = beer)
                                }
                            },
                            onFailure = {

                            }
                        )
                }
            }
        }
    }
}