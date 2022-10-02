package pl.studioandroida.italianrecipesapp.presentation.detail

import android.content.res.Resources
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import pl.studioandroida.italianrecipesapp.presentation.detail.components.imageParallaxScroll


@Composable
fun DetailScreen(
    viewModel: DetailViewModel = hiltViewModel()
) {
    val meal = viewModel.mealState.value.meal
    val ingredients = viewModel.ingredientsState.value.ingredients
    val steps = viewModel.stepsState.value.steps
    Log.d("sprawdzam", "${viewModel.ingredientsState.value}")


    meal?.let { meal ->
        val context = LocalContext.current
        val resources: Resources = context.resources
        val resourceId: Int = resources.getIdentifier(meal.image, "drawable", context.packageName)

        imageParallaxScroll(
            resourceId = resourceId,
            meal = meal,
            ingredients = ingredients,
            steps = steps
        )
    }
}





