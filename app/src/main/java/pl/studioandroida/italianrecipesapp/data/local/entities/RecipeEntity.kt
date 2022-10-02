package pl.studioandroida.italianrecipesapp.data.local.entities

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "recipe")
data class RecipeEntity (
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @NonNull
    val id_meal: Int,
    @NonNull
    val description: String,
    @NonNull
    val servings: Int
    )

