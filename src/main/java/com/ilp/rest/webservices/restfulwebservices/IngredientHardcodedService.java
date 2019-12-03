package com.ilp.rest.webservices.restfulwebservices;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class IngredientHardcodedService {
	private static List<Ingredient> ingredients = new ArrayList<Ingredient>();

	static {
		ingredients.add(new Ingredient("Pepper", 2));
		ingredients.add(new Ingredient("Tomatoes", 3));
		ingredients.add(new Ingredient("Patty", 3));
	}

	public List<Ingredient> findAll() {
		return ingredients;
	}

	public Ingredient save(int index, Ingredient ingredient) {
		if (index == -1) {
			ingredients.add(ingredient);
		} else {
			deleteByIndex(index);
			ingredients.add(ingredient);
		}

		return ingredient;
	}

	public Ingredient deleteByIndex(int index) {
		Ingredient ingredient;
		 
		try { 
			ingredient = findByIndex(index);
		} catch (Exception e) {
			System.out.println("Error: " + e);
			return null;
		}
  
		if (ingredient != null) {
			ingredients.remove(ingredient);
			return ingredient;
		}

		return null;
	}

	public Ingredient findByIndex(int index) {
		Ingredient ingredient = ingredients.get(index);

		return ingredient;

	}

	public static List<Ingredient> getIngredients() {
		return ingredients;
	}
}
