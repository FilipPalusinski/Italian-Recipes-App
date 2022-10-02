package pl.studioandroida.italianrecipesapp.domain.use_case

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import pl.studioandroida.italianrecipesapp.domain.model.MealAndRecipe
import pl.studioandroida.italianrecipesapp.domain.repository.RecipeRepository
import pl.studioandroida.italianrecipesapp.util.Resource
import javax.inject.Inject

class GetMealUseCase @Inject constructor(
    private val repository: RecipeRepository
){


    operator fun invoke(mealId: Int): Flow<Resource<MealAndRecipe>> = flow {
        try {
            emit(Resource.Loading())
            val meal = repository.getMealById(mealId)
            emit(meal)
        }catch (e: Exception) {
            emit(Resource.Error("An unexpected error occured"))
        }
    }

}

