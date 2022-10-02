package pl.studioandroida.italianrecipesapp.domain.use_case

import android.util.Log
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import pl.studioandroida.italianrecipesapp.domain.model.Ingredients
import pl.studioandroida.italianrecipesapp.domain.repository.RecipeRepository
import pl.studioandroida.italianrecipesapp.util.Resource
import javax.inject.Inject

class GetIngredientsUseCase @Inject constructor(
    private val repository: RecipeRepository
){


    operator fun invoke(mealId: Int): Flow<Resource<List<Ingredients>>> = flow {
        try {
            emit(Resource.Loading())
            val ingredients = repository.getIngredientsById(mealId)
            emit(ingredients)
        }catch (e: Exception) {
            emit(Resource.Error("An unexpected error occured"))
        }
    }

}
