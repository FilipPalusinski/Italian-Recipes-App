package pl.studioandroida.italianrecipesapp.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import pl.studioandroida.italianrecipesapp.R
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
            val bottomBarState = rememberSaveable { (mutableStateOf(true)) }

            ItalianRecipesTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val navController = rememberNavController()

                    val navBackStackEntry by navController.currentBackStackEntryAsState()
                    when (navBackStackEntry?.destination?.route) {
                        "home" -> {
                            bottomBarState.value = true
                        }
                        "favourite" -> {
                            bottomBarState.value = true
                        }
                        "detail" -> {
                            bottomBarState.value = false
                        }
                    }
                    Scaffold(
                        content = { padding ->
                            Box(modifier = Modifier.padding(padding)) {
                                Navigation(
                                    navController,
                                    bottomBarState
                                )
                            }
                        },
                        bottomBar = {
                            BottomNavigationBar(
                                navController,
                                bottomBarState = bottomBarState
                            )
                        }

                    )
                }
            }
        }
    }


    @Composable
    fun Navigation(
        navController: NavHostController,
        bottomBarState: MutableState<Boolean>
    ) {

        NavHost(
            navController = navController,
            startDestination = NavigationItem.Home.route
        ){
            composable(
                route = NavigationItem.Home.route
            ) {
                LaunchedEffect(Unit) {
                    bottomBarState.value = true
                }
                HomeScreen(navController)
            }
            composable(
                route = NavigationItem.Favourite.route
            ) {
                LaunchedEffect(Unit) {
                    bottomBarState.value = true
                }
                FavouriteScreen()
            }
            composable(
                route = NavigationItem.Detail.route + "/{mealId}"
            ){
                LaunchedEffect(Unit) {
                    bottomBarState.value = false
                }
                DetailScreen(navController)
            }
        }
    }



    @Composable
    fun BottomNavigationBar(
        navController: NavController,
        bottomBarState: MutableState<Boolean>
    ) {
        val items = listOf(
            NavigationItem.Home,
            NavigationItem.Favourite
        )

        AnimatedVisibility(
            visible = bottomBarState.value,
            enter = slideInVertically(initialOffsetY = { it }),
            exit = slideOutVertically(targetOffsetY = { it }),
            content = {

                BottomNavigation(backgroundColor = Green700)
                {
                    val navBackStackEntry by navController.currentBackStackEntryAsState()
                    val currentRoute = navBackStackEntry?.destination?.route
                    items.forEach { item ->
                        var itemTitle = ""
                        if(item.title == "Cookbook") {
                            itemTitle = stringResource(R.string.NavigationItem_cookbook)
                        }else if(item.title == "Favourite") {
                            itemTitle = stringResource(R.string.NavigationItem_favourite)
                        }
                                BottomNavigationItem(
                                    icon = { Icon(imageVector = item.icon, contentDescription = item.title) },
                                    label = { Text(text = itemTitle) },
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

        })
        }
    }






