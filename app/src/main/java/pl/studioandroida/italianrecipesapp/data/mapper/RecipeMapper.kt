package pl.studioandroida.italianrecipesapp.data.mapper

import pl.studioandroida.italianrecipesapp.data.local.entities.*
import pl.studioandroida.italianrecipesapp.domain.model.Ingredients
import pl.studioandroida.italianrecipesapp.domain.model.MealAndRecipe
import pl.studioandroida.italianrecipesapp.domain.model.Recipe
import pl.studioandroida.italianrecipesapp.domain.model.Steps


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
        description = description,
        servings = servings
    )
}


fun IngredientsEntity.toIngredients(): Ingredients {
    return Ingredients(
        id = id,
        name = ingredient,
        recipe_id = recipe_id
    )
}

fun StepsEntity.toSteps(): Steps {
    return Steps(
        id = id,
        recipe_id = recipe_id,
        instruction = instruction
    )
}
