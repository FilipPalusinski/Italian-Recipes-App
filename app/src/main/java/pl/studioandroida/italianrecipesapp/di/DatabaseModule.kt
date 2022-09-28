package pl.studioandroida.italianrecipesapp.di

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import pl.studioandroida.italianrecipesapp.data.local.RecipeDao
import pl.studioandroida.italianrecipesapp.data.local.RecipeDatabase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideRecipeDatabase(app: Application): RecipeDatabase {
        return Room.databaseBuilder(
            app,
            RecipeDatabase::class.java,
            "recipes.db"
        ).createFromAsset("databases/recipes").build()
    }

    @Provides
    fun provideRecipeDao(recipeDatabase: RecipeDatabase): RecipeDao = recipeDatabase.recipeDao()
}
