package pl.studioandroida.italianrecipesapp.presentation.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import pl.studioandroida.italianrecipesapp.domain.use_case.GetMealsUseCase
import pl.studioandroida.italianrecipesapp.util.Resource
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getMealsUseCase: GetMealsUseCase
) : ViewModel() {

    private var _mealState = mutableStateOf(MealListState())
    val mealState: State<MealListState> = _mealState

    init {
        getMeals()
    }

    fun getMeals() {
        viewModelScope.launch {

            getMealsUseCase().collect { result ->
                when (result) {
                    is Resource.Success -> {
                        _mealState.value = result.data?.let { MealListState(meals = it) }!!
                    }
                    is Resource.Error -> {
                        _mealState.value = MealListState(
                            error = result.message ?: "An unexpected error occured"
                        )
                    }
                    is Resource.Loading -> {
                        _mealState.value = MealListState(isLoading = true)
                    }
                }
            }
        }
    }



}
