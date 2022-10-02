package pl.studioandroida.italianrecipesapp.presentation.detail.states

import pl.studioandroida.italianrecipesapp.domain.model.MealAndRecipe

data class MealDetailState(
    val isLoading: Boolean = false,
    val meal: MealAndRecipe? = null,
    val error: String = ""
)
