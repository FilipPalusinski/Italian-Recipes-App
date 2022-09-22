package pl.studioandroida.italianrecipesapp.presentation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Star
import androidx.compose.ui.graphics.vector.ImageVector

private const val ROUTE_HOME = "home"
private const val ROUTE_FAV = "favourite"


sealed class NavigationItem(var route: String, var icon: ImageVector, var title: String) {
    object Home : NavigationItem(ROUTE_HOME, Icons.Default.Home, "Cookbook")
    object Favourite : NavigationItem(ROUTE_FAV, Icons.Default.Star, "Favourite")

}