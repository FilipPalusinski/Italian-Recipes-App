package pl.studioandroida.italianrecipesapp.presentation.home

import android.content.res.Resources
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import pl.studioandroida.italianrecipesapp.R
import pl.studioandroida.italianrecipesapp.presentation.NavigationItem
import pl.studioandroida.italianrecipesapp.presentation.home.components.MealListItem
import pl.studioandroida.italianrecipesapp.util.Resource

@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val state = viewModel.mealState.value
    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(20.dp)) {
        Text(
            fontSize = 30.sp,
            color = MaterialTheme.colors.primary,
            text = stringResource(R.string.recipes),
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(10.dp))

        NormalTextField(label = stringResource(R.string.searchbar_text)) {
            Icon(
                imageVector = Icons.Outlined.Search,
                contentDescription = null
            )
        }

        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(state.meals) { meal ->
                MealListItem(meal,onItemClick = {
                    navController.navigate(NavigationItem.Detail.route + "/${meal.id}")
                })
            }
        }
        if(state.error.isNotBlank()) {
            Text(
                text = state.error,
                color = MaterialTheme.colors.error,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 20.dp)

            )
        }
        if(state.isLoading) { CircularProgressIndicator() }
    }


}


@Composable
fun NormalTextField(
    label: String,
    Icon: @Composable (() -> Unit),
) {
    var text by remember { mutableStateOf("") }
    TextField(
        modifier = Modifier
                .fillMaxWidth(),
        leadingIcon = Icon,
        value = text,
        maxLines = 1,
        onValueChange = { text = it },
        label = { Text(text = label) },
        )
}
