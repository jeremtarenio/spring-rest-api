package com.ilp.rest.webservices.restfulwebservices;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class RecipeHardCodedService {
	private static List<Recipe> recipes = new ArrayList<Recipe>();

	static {
		List<Ingredient> ingredients = new ArrayList<Ingredient>();
		ingredients.add(new Ingredient("Patis", 2));
		ingredients.add(new Ingredient("Suka", 3));
		ingredients.add(new Ingredient("Kamatis", 3));

		recipes.add(new Recipe(1, "Angus Burger", "Lorem ipsum dolor sit amet, consectetur adipiscing elit.",
				"./assets/photos/angusburger.png", ingredients));
		recipes.add(new Recipe(2, "Bacon Fries", "Lorem ipsum dolor sit amet, consectetur adipiscing elit.",
				"./assets/photos/baconfries.png", ingredients));
		recipes.add(new Recipe(3, "Spicy Buffalo Wings", "Lorem ipsum dolor sit amet, consectetur adipiscing elit.",
				"./assets/photos/buffalowings.png", ingredients));
	}

	public List<Recipe> findAll() {
		return recipes;
	}

	public Recipe findById(int id) {
		for (Recipe recipe : recipes) {
			if (recipe.getId() == id) {
				return recipe;
			}
		}

		return null;
	}

	public Recipe save(int id, Recipe recipe) {
		if (id == -1) {
			Recipe newRecipe = new Recipe(generateId(), recipe.getName(), recipe.getDescription(),
					recipe.getImagePath(), recipe.getIngredients());
			recipes.add(newRecipe);
		} else {
			deleteById(id);
			recipes.add(recipe);
		}

		return recipe;
	}

	public Recipe deleteById(int id) {
		Recipe recipe;

		try {
			recipe = findById(id);
		} catch (Exception e) {
			System.out.println("Error: " + e);
			return null;
		}

		if (recipe != null) {
			recipes.remove(recipe);
			return recipe;
		}

		return null;

	}

	private int generateId() {
		int latestIdYet = 0;

		for (Recipe recipe : recipes) {
			if (recipe.getId() > latestIdYet) {
				latestIdYet = recipe.getId();
			}
		}
		 
		return latestIdYet + 1;
	}

}
