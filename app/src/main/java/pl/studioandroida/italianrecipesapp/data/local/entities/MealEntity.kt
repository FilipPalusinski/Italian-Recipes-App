package pl.studioandroida.italianrecipesapp.data.local.entities

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "meal")
data class MealEntity (
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @NonNull
    val name: String,
    @NonNull
    val image: String,
    @NonNull
    val language: String
)
