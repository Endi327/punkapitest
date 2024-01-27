package hu.varsanyi.punkapiapp.features.beerlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import hu.varsanyi.punkapiapp.domain.models.Beer
import hu.varsanyi.punkapiapp.domain.usecases.GetLikedBeerListUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class BeerListViewModel(
    private val getLikedBeerListUseCase: GetLikedBeerListUseCase
) : ViewModel() {

    data class UiState(
        val beerList: List<Beer> = emptyList()
    )

    private val _uiState = MutableStateFlow(UiState())

    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            getLikedBeerListUseCase.invoke(Unit)
                .foldResult(
                    onSuccess = { beerList ->
                        _uiState.update {
                            it.copy(beerList = beerList)
                        }
                    },
                    onFailure = {

                    }
                )
        }
    }
}