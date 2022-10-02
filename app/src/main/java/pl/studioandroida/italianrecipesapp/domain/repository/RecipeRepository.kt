package pl.studioandroida.italianrecipesapp.domain.repository

import pl.studioandroida.italianrecipesapp.domain.model.Ingredients
import pl.studioandroida.italianrecipesapp.domain.model.MealAndRecipe
import pl.studioandroida.italianrecipesapp.domain.model.Steps
import pl.studioandroida.italianrecipesapp.util.Resource

interface RecipeRepository {
    suspend fun getMeals(): Resource<List<MealAndRecipe>>

    suspend fun getMealById(mealId: Int): Resource<MealAndRecipe>

    suspend fun getIngredientsById(mealId: Int): Resource<List<Ingredients>>

    suspend fun getStepsById(mealId: Int): Resource<List<Steps>>

    }
