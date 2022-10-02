package pl.studioandroida.italianrecipesapp.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import pl.studioandroida.italianrecipesapp.presentation.detail.DetailScreen
import pl.studioandroida.italianrecipesapp.presentation.favourite.FavouriteScreen
import pl.studioandroida.italianrecipesapp.presentation.home.HomeScreen
import pl.studioandroida.italianrecipesapp.presentation.ui.theme.Green700
import pl.studioandroida.italianrecipesapp.presentation.ui.theme.ItalianRecipesTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ItalianRecipesTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val navController = rememberNavController()
                    Scaffold(
                        content = {padding ->
                            Box(modifier = Modifier.padding(padding)) {
                                Navigation(navController)
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
        navController: NavHostController) {

        NavHost(
            navController = navController,
            startDestination = NavigationItem.Home.route
        ){
            composable(
                route = NavigationItem.Home.route
            ) {
                HomeScreen(navController)
            }
            composable(
                route = NavigationItem.Favourite.route
            ) {
                FavouriteScreen()
            }
            composable(
                route = NavigationItem.Detail.route + "/{mealId}"
            ){
                DetailScreen()
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

