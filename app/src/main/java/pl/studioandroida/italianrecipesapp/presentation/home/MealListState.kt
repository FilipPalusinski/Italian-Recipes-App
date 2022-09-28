package pl.studioandroida.italianrecipesapp.presentation.home

import pl.studioandroida.italianrecipesapp.domain.model.MealAndRecipe

data class MealListState(
    val isLoading: Boolean = false,
    val meals: List<MealAndRecipe> = emptyList(),
    val error: String = ""
)
