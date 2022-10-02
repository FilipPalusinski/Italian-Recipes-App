package pl.studioandroida.italianrecipesapp.data.local

import androidx.room.Dao
import androidx.room.Query
import pl.studioandroida.italianrecipesapp.data.local.entities.*

@Dao
interface RecipeDao {

//    @Query("SELECT * FROM meal where language=:language")
//    suspend fun getMeals(language: String): List<MealEntity>

    @Query("SELECT meal.id as MARid, meal.name as MARname, meal.image as MARimage," +
            "recipe.description as MARdesc,recipe.servings as MARservings " +
            "FROM meal INNER JOIN recipe" +
            " ON meal.id = recipe.id_meal where meal.language = :language")
    suspend fun getMeals(language: String): List<MealAndRecipeEntity>

    @Query("SELECT meal.id as MARid, meal.name as MARname, meal.image as MARimage," +
            "recipe.description as MARdesc, recipe.servings as MARservings " +
            "FROM meal INNER JOIN recipe" +
            " ON meal.id = recipe.id_meal where meal.id = :mealId")
    suspend fun getMealById(mealId: Int): MealAndRecipeEntity

    @Query("SELECT ingredients.* FROM ingredients" +
            " INNER JOIN recipe ON recipe.id = ingredients.recipe_id" +
            " WHERE recipe.id_meal = :mealId")
    suspend fun getIngredientsById(mealId: Int): List<IngredientsEntity>

    @Query("SELECT steps.* FROM steps" +
            " INNER JOIN recipe ON recipe.id = steps.recipe_id" +
            " WHERE recipe.id_meal = :mealId")
    suspend fun getStepsById(mealId: Int): List<StepsEntity>
}
