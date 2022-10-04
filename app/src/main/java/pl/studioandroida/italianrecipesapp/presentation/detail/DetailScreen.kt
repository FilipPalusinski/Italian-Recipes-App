package pl.studioandroida.italianrecipesapp.presentation.detail

import android.content.res.Resources
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import pl.studioandroida.italianrecipesapp.presentation.detail.components.imageParallaxScroll


@Composable
fun DetailScreen(
    navController: NavController,
    viewModel: DetailViewModel = hiltViewModel()
) {
    val meal = viewModel.mealState.value.meal
    val ingredients = viewModel.ingredientsState.value.ingredients
    val steps = viewModel.stepsState.value.steps


    meal?.let { meal ->
        val context = LocalContext.current
        val resources: Resources = context.resources
        val resourceId: Int = resources.getIdentifier(meal.image, "drawable", context.packageName)

        Box(Modifier.fillMaxSize()) {
            imageParallaxScroll(
                resourceId = resourceId,
                meal = meal,
                ingredients = ingredients,
                steps = steps
            )
            Box(
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
            ) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "Back",
                    tint = Color.White,
                    modifier = Modifier
                        .padding(20.dp)
                        .clickable {
                            val route = navController.previousBackStackEntry?.destination?.route ?: ""
                            navController.navigate(route)
                                   },
                )
            }


        }


    }
}





