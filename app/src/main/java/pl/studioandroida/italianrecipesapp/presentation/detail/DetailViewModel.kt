package pl.studioandroida.italianrecipesapp.presentation.detail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import pl.studioandroida.italianrecipesapp.domain.use_case.GetIngredientsUseCase
import pl.studioandroida.italianrecipesapp.domain.use_case.GetMealUseCase
import pl.studioandroida.italianrecipesapp.domain.use_case.GetStepsUseCase
import pl.studioandroida.italianrecipesapp.presentation.detail.states.IngredientsDetailState
import pl.studioandroida.italianrecipesapp.presentation.detail.states.MealDetailState
import pl.studioandroida.italianrecipesapp.presentation.detail.states.StepsDetailState
import pl.studioandroida.italianrecipesapp.util.Constants
import pl.studioandroida.italianrecipesapp.util.Resource
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val getMealUseCase: GetMealUseCase,
    private val getIngredientsUseCase: GetIngredientsUseCase,
    private val getStepsUseCase: GetStepsUseCase,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _mealState = mutableStateOf(MealDetailState())
    val mealState: State<MealDetailState> = _mealState

    private val _ingredientsState = mutableStateOf(IngredientsDetailState())
    val ingredientsState: State<IngredientsDetailState> = _ingredientsState

    private val _stepsState = mutableStateOf(StepsDetailState())
    val stepsState: State<StepsDetailState> = _stepsState

    init {
        savedStateHandle.get<String>(Constants.PARAM_MEAL_ID)?.let { mealId ->
            getMealById(mealId)
            getIngredientsById(mealId)
            getStepsById(mealId)
        }

    }

    private fun getMealById(mealId: String){
        getMealUseCase(mealId.toInt()).onEach { result ->
            when(result) {
                is Resource.Success -> {
                    _mealState.value = result.data?.let { MealDetailState(meal = it) }!!
                }
                is Resource.Error -> {
                    _mealState.value = MealDetailState(
                        error = result.message ?: "An unexpected error occured"
                    )
                }
                is Resource.Loading -> {
                    _mealState.value = MealDetailState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun getIngredientsById(mealId: String){
        viewModelScope.launch {

            getIngredientsUseCase(mealId.toInt()).collect { result ->
                when (result) {
                    is Resource.Success -> {
                        _ingredientsState.value =
                            result.data?.let { IngredientsDetailState(ingredients = it) }!!
                    }
                    is Resource.Error -> {
                        _ingredientsState.value = IngredientsDetailState(
                            error = result.message ?: "An unexpected error occured"
                        )
                    }
                    is Resource.Loading -> {
                        _ingredientsState.value = IngredientsDetailState(isLoading = true)
                    }
                }
            }
        }
    }

    private fun getStepsById(mealId: String){
        viewModelScope.launch {

            getStepsUseCase(mealId.toInt()).collect { result ->
                when (result) {
                    is Resource.Success -> {
                        _stepsState.value =
                            result.data?.let { StepsDetailState(steps = it) }!!
                    }
                    is Resource.Error -> {
                        _stepsState.value = StepsDetailState(
                            error = result.message ?: "An unexpected error occured"
                        )
                    }
                    is Resource.Loading -> {
                        _stepsState.value = StepsDetailState(isLoading = true)
                    }
                }
            }
        }
    }
}

