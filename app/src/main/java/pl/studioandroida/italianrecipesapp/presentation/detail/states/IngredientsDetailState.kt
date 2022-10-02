package pl.studioandroida.italianrecipesapp.presentation.detail.states

import pl.studioandroida.italianrecipesapp.domain.model.Ingredients

data class IngredientsDetailState(
    val isLoading: Boolean = false,
    val ingredients: List<Ingredients> = emptyList(),
    val error: String = ""
)
