package pl.studioandroida.italianrecipesapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import pl.studioandroida.italianrecipesapp.data.local.entities.IngredientsEntity
import pl.studioandroida.italianrecipesapp.data.local.entities.MealEntity
import pl.studioandroida.italianrecipesapp.data.local.entities.RecipeEntity
import pl.studioandroida.italianrecipesapp.data.local.entities.StepsEntity

@Database(entities = [IngredientsEntity::class, MealEntity::class, RecipeEntity::class, StepsEntity::class], version = 1, exportSchema = true)
abstract class RecipeDatabase : RoomDatabase() {
    abstract fun recipeDao(): RecipeDao
}
