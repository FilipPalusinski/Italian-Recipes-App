package pl.studioandroida.italianrecipesapp.domain.model

data class Recipe(
    val id: Int = 0,
    val id_meal: Int,
    val description: String,
    val servings: Int
)
