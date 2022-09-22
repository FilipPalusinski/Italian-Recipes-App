package pl.studioandroida.italianrecipesapp.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import pl.studioandroida.italianrecipesapp.presentation.favourite.FavouriteScreen
import pl.studioandroida.italianrecipesapp.presentation.home.HomeScreen
import pl.studioandroida.italianrecipesapp.presentation.ui.theme.Green500
import pl.studioandroida.italianrecipesapp.presentation.ui.theme.Green700
import pl.studioandroida.italianrecipesapp.presentation.ui.theme.ItalianRecipesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ItalianRecipesTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val navController = rememberNavController()

                    Scaffold(
                        content = {padding ->
                            Box(modifier = Modifier.padding(padding)) {
                                Navigation(navController = navController)
                            }
                        },
                        bottomBar = {
                            BottomNavigationBar(navController)
                        }

                    )
                }
            }
        }
    }

    @Composable
    fun Navigation(
        navController: NavHostController,
        //HomeViewModel: HomeViewModel = hiltViewModel(),
        //FavViewModel: FavouriteViewModel = hiltViewModel()
    ) {

        NavHost(
            navController = navController,
            startDestination = NavigationItem.Home.route
        ){
            composable(
                route = NavigationItem.Home.route
            ) {
                //HomeScreen(HomeViewModel)
                HomeScreen()
            }
            composable(
                route = NavigationItem.Favourite.route
            ) {
                //FavouriteScreen(FavViewModel)
                FavouriteScreen()
                //FavViewModel.getActivities()
            }
        }
    }



    @Composable
    fun BottomNavigationBar(navController: NavController) {
        val items = listOf(
            NavigationItem.Home,
            NavigationItem.Favourite
        )
        BottomNavigation(
            backgroundColor = Green700
        ) {
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentRoute = navBackStackEntry?.destination?.route
            items.forEach { item ->
                BottomNavigationItem(
                    icon = { Icon(imageVector = item.icon, contentDescription = item.title) },
                    label = { Text(text = item.title) },
                    selectedContentColor = Color.White,
                    unselectedContentColor = Color.White.copy(0.4f),
                    alwaysShowLabel = true,
                    selected = currentRoute == item.route,
                    onClick = {
                        navController.navigate(item.route) {
                            navController.graph.startDestinationRoute?.let { route ->
                                popUpTo(route) {
                                    saveState = true
                                }
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                )
            }
        }
    }




}

