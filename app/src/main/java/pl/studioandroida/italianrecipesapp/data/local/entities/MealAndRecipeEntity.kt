package pl.studioandroida.italianrecipesapp.data.local.entities

import androidx.annotation.NonNull
import androidx.room.ColumnInfo

data class MealAndRecipeEntity(
    @NonNull
    @ColumnInfo(name = "MARid") val id: Int = 0,
    @NonNull
    @ColumnInfo(name = "MARname")val name: String = "",
    @NonNull
    @ColumnInfo(name = "MARimage")val image: String= "",
    @NonNull
    @ColumnInfo(name = "MARservings")val servings: Int= 0
)
