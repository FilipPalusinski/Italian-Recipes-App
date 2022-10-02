package pl.studioandroida.italianrecipesapp.domain.use_case

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import pl.studioandroida.italianrecipesapp.domain.model.Ingredients
import pl.studioandroida.italianrecipesapp.domain.model.Steps
import pl.studioandroida.italianrecipesapp.domain.repository.RecipeRepository
import pl.studioandroida.italianrecipesapp.util.Resource
import javax.inject.Inject

class GetStepsUseCase @Inject constructor(
    private val repository: RecipeRepository
) {


    operator fun invoke(mealId: Int): Flow<Resource<List<Steps>>> = flow {
        try {
            emit(Resource.Loading())
            val steps = repository.getStepsById(mealId)
            emit(steps)
        } catch (e: Exception) {
            emit(Resource.Error("An unexpected error occured"))
        }
    }
}
