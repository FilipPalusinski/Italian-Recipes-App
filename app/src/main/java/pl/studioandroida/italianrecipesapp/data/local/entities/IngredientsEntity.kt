package pl.studioandroida.italianrecipesapp.data.local.entities

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ingredients")
data class IngredientsEntity (
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @NonNull
    val ingredient: String,
    @NonNull
    val recipe_id: Int,
)
