package hu.varsanyi.punkapiapp.domain.usecases

import hu.varsanyi.punkapiapp.domain.models.Beer
import hu.varsanyi.punkapiapp.domain.usecases.core.IUseCase

interface GetLikedBeerListUseCase: IUseCase<Unit, List<Beer>>