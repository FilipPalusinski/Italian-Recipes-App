package pl.studioandroida.italianrecipesapp.presentation.detail.components

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import pl.studioandroida.italianrecipesapp.R
import pl.studioandroida.italianrecipesapp.domain.model.Ingredients
import pl.studioandroida.italianrecipesapp.domain.model.MealAndRecipe
import pl.studioandroida.italianrecipesapp.domain.model.Steps

@Composable
fun imageParallaxScroll(
    resourceId: Int,
    meal: MealAndRecipe,
    ingredients: List<Ingredients>,
    steps: List<Steps>
) {
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(scrollState),

        ) {

        Box() {

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
                    .background(MaterialTheme.colors.surface)
                    .graphicsLayer {
                        alpha = 1f - ((scrollState.value.toFloat() / scrollState.maxValue) * 4.5f)
                        translationY = 0.5f * scrollState.value
                    },
                contentAlignment = Alignment.Center
            ) {

                Image(
                    painterResource(id = resourceId),
                    contentDescription = "meal",
                    contentScale = ContentScale.None,
                    modifier = Modifier.fillMaxSize()
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .fillMaxWidth()
                    .padding(0.dp, 290.dp, 0.dp, 0.dp)
                    .clip(RoundedCornerShape(15.dp, 15.dp, 0.dp, 0.dp))
                    .background(MaterialTheme.colors.surface),

                ) {

                Column(
                    modifier = Modifier
                    .padding(20.dp)
                ) {

                    Text(
                        text = meal.name,
                        fontSize = 26.sp,
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.fillMaxWidth()
                    )

                    Divider(
                        modifier = Modifier.padding(20.dp),
                        startIndent = 8.dp,
                        thickness = 0.5.dp,
                        color = MaterialTheme.colors.onSurface
                    )

                    Text(

                        text = stringResource(R.string.detail_screen_about),
                        fontSize = 22.sp,
                        textAlign = TextAlign.Start,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = meal.description,
                        fontSize = 18.sp,
                        textAlign = TextAlign.Start,
                    )

                    Divider(
                        modifier = Modifier.padding(20.dp),
                        startIndent = 8.dp,
                        thickness = 0.5.dp,
                        color = MaterialTheme.colors.onSurface
                    )

                    Text(
                        text = stringResource(R.string.detail_screen_ingredients),
                        fontSize = 22.sp,
                        textAlign = TextAlign.Start,
                        fontWeight = FontWeight.Bold
                    )

                    ingredients.forEach { ingredient ->
                        Text(
                            modifier = Modifier
                                .padding(0.dp, 5.dp, 0.dp, 5.dp),
                            text = ingredient.name,
                            fontSize = 18.sp,
                            textAlign = TextAlign.Start,
                        )
                    }

                    Divider(
                        modifier = Modifier.padding(20.dp),
                        startIndent = 8.dp,
                        thickness = 0.5.dp,
                        color = MaterialTheme.colors.onSurface
                    )

                    Text(
                        text = stringResource(R.string.detail_screen_steps),
                        fontSize = 22.sp,
                        textAlign = TextAlign.Start,
                        fontWeight = FontWeight.Bold
                    )

                    var stepNumber: Int = 0
                    steps.forEach { steps ->
                        stepNumber++
                        Text(
                            modifier = Modifier
                                .padding(0.dp, 5.dp, 0.dp, 5.dp),
                            text = "$stepNumber. ${steps.instruction}",
                            fontSize = 18.sp,
                            textAlign = TextAlign.Start,
                        )
                    }

                }

            }

        }

    }
}
