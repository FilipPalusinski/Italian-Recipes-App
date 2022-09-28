package pl.studioandroida.italianrecipesapp.data.local

import androidx.room.Dao
import androidx.room.Query
import pl.studioandroida.italianrecipesapp.data.local.entities.MealAndRecipeEntity
import pl.studioandroida.italianrecipesapp.data.local.entities.MealEntity
import pl.studioandroida.italianrecipesapp.data.local.entities.RecipeEntity

@Dao
interface RecipeDao {

//    @Query("SELECT * FROM meal where language=:language")
//    suspend fun getMeals(language: String): List<MealEntity>

    @Query("SELECT meal.id as MARid, meal.name as MARname, meal.image as MARimage,"
        + " recipe.servings as MARservings FROM meal INNER JOIN recipe"
        + " ON meal.id = recipe.id_meal where meal.language = :language")
    suspend fun getMeals(language: String): List<MealAndRecipeEntity>
}
