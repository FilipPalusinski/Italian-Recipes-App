package pl.studioandroida.italianrecipesapp.data.local.entities

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "steps")
data class StepsEntity (
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @NonNull
    val recipe_id: Int,
    @NonNull
    val instruction: String
)
