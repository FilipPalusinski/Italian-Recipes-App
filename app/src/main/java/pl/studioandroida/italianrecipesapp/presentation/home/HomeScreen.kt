package pl.studioandroida.italianrecipesapp.presentation.home

import android.provider.Settings.Global.getString
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import pl.studioandroida.italianrecipesapp.R

@Composable
fun HomeScreen() {
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


        NormalTextField(label = "Search Recipes") {
            Icon(
                imageVector = Icons.Outlined.Search,
                contentDescription = null
            )
        }

        RecipeListItem()


    }
    

}

@Composable
fun RecipeListItem(
    
) {

        Spacer(modifier = Modifier.height(20.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
        ) {
            Image (
                painter = painterResource(id = R.drawable.neapolitan_pizza),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                        modifier = Modifier
                    .clip(RoundedCornerShape(5.dp))
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                Color.Transparent,
                                Color.Black
                            ),
                            startY = 50f,
                            endY = 800f
                        )
                    )
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(12.dp),
                contentAlignment = Alignment.BottomStart
            ) {
                Column() {
                    Text(
                        text = "Neapolitan Pizza",
                        style = TextStyle(
                            color = Color.White,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        )
                    )

                    Text(
                        text = "Serves 4",
                        style = TextStyle(
                            color = Color.White,
                            fontSize = 15.sp
                        )
                    )
                }


            }
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

@Preview
@Composable
fun HomeScreenPreview(){
    HomeScreen()
}