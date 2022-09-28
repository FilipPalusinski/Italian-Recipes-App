package pl.studioandroida.italianrecipesapp.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import pl.studioandroida.italianrecipesapp.data.local.RecipeDao
import pl.studioandroida.italianrecipesapp.data.repository.RecipeRepositoryImpl
import pl.studioandroida.italianrecipesapp.domain.repository.RecipeRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRecipeRepository(dao: RecipeDao): RecipeRepository {
        return RecipeRepositoryImpl(dao)
    }

}
