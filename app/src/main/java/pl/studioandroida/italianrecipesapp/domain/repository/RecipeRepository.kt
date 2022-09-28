package pl.studioandroida.italianrecipesapp.domain.repository

import pl.studioandroida.italianrecipesapp.domain.model.MealAndRecipe
import pl.studioandroida.italianrecipesapp.util.Resource

interface RecipeRepository {
    suspend fun getMeals(): Resource<List<MealAndRecipe>>


}
