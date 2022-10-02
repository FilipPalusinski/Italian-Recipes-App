package pl.studioandroida.italianrecipesapp.domain.model


data class Steps(
    val id: Int = 0,
    val recipe_id: Int,
    val instruction: String
)
