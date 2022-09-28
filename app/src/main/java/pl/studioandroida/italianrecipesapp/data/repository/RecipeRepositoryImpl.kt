package pl.studioandroida.italianrecipesapp.data.repository

import android.util.Log
import pl.studioandroida.italianrecipesapp.data.local.RecipeDao
import pl.studioandroida.italianrecipesapp.data.mapper.toMealAndRecipe
import pl.studioandroida.italianrecipesapp.domain.model.MealAndRecipe
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

            Log.d("sprawdzamjezyk","$language")

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
}
