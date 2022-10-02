package pl.studioandroida.italianrecipesapp.data.repository

import android.util.Log
import pl.studioandroida.italianrecipesapp.data.local.RecipeDao
import pl.studioandroida.italianrecipesapp.data.mapper.toIngredients
import pl.studioandroida.italianrecipesapp.data.mapper.toMealAndRecipe
import pl.studioandroida.italianrecipesapp.data.mapper.toSteps
import pl.studioandroida.italianrecipesapp.domain.model.Ingredients
import pl.studioandroida.italianrecipesapp.domain.model.MealAndRecipe
import pl.studioandroida.italianrecipesapp.domain.model.Steps
import pl.studioandroida.italianrecipesapp.domain.repository.RecipeRepository
import pl.studioandroida.italianrecipesapp.util.Resource
import java.io.IOException
import java.util.*
import javax.inject.Inject

class RecipeRepositoryImpl @Inject constructor(
    private val recipeDao: RecipeDao,

    ): RecipeRepository {


    override suspend fun getMeals(): Resource<List<MealAndRecipe>> {
        return try {
            val language = if(Locale.getDefault().language == "pl") { "pl" }  else "en"
            val meals = recipeDao.getMeals("$language")
            Resource.Success(
                data = meals.map { it.toMealAndRecipe() },
            )
        } catch (e: Exception) {
            Resource.Error(e.localizedMessage ?: "An unexpected error occured")
        }catch (e: IOException){
            Resource.Error("Database Error")
        }
    }

    override suspend fun getMealById(mealId: Int): Resource<MealAndRecipe> {
        return try {
            val meal = recipeDao.getMealById(mealId)
            Resource.Success(
                data = meal.toMealAndRecipe()
            )
        } catch (e: Exception) {
            Resource.Error(e.localizedMessage ?: "An unexpected error occured")
        }catch (e: IOException){
            Resource.Error("Database Error")
        }
    }

    override suspend fun getIngredientsById(mealId: Int): Resource<List<Ingredients>> {
        return try {
            val ingredients = recipeDao.getIngredientsById(mealId)

            Resource.Success(
                data = ingredients.map { it.toIngredients() },
            )
        } catch (e: Exception) {
            Resource.Error(e.localizedMessage ?: "An unexpected error occured")
        }catch (e: IOException){
            Resource.Error("Database Error")
        }
    }

    override suspend fun getStepsById(mealId: Int): Resource<List<Steps>> {
        return try {
            val steps = recipeDao.getStepsById(mealId)
            Resource.Success(
                data = steps.map { it.toSteps() },
            )
        } catch (e: Exception) {
            Resource.Error(e.localizedMessage ?: "An unexpected error occured")
        }catch (e: IOException){
            Resource.Error("Database Error")
        }
    }
}
