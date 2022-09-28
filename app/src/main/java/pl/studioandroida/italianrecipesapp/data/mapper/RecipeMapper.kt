package pl.studioandroida.italianrecipesapp.data.mapper

import pl.studioandroida.italianrecipesapp.data.local.entities.MealAndRecipeEntity
import pl.studioandroida.italianrecipesapp.data.local.entities.MealEntity
import pl.studioandroida.italianrecipesapp.data.local.entities.RecipeEntity
import pl.studioandroida.italianrecipesapp.domain.model.MealAndRecipe
import pl.studioandroida.italianrecipesapp.domain.model.Recipe


fun RecipeEntity.toRecipe(): Recipe {
        return Recipe(
            id = id,
            id_meal = id_meal,
            description = description,
            servings = servings
        )
    }


fun MealAndRecipeEntity.toMealAndRecipe(): MealAndRecipe {
    return MealAndRecipe(
        id = id,
        name = name,
        image = image,
        servings = servings
    )
}
