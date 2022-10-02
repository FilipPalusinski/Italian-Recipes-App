package pl.studioandroida.italianrecipesapp.presentation.detail.states

import pl.studioandroida.italianrecipesapp.domain.model.Steps

data class StepsDetailState(
    val isLoading: Boolean = false,
    val steps: List<Steps> = emptyList(),
    val error: String = ""
)
