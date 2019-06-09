package test.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import entity.Ingredient;
import entity.Recipe;

class RecipeTest {

	@Test
	void test() {
		Recipe recipe = new Recipe("Suan La Fen", 2);		
		recipe.addIngredient(new Ingredient("potato noodles", 1.0, "bunch"));
		recipe.addIngredient(new Ingredient("peanuts", 2.0, "tablespoon", "roasted"));
		recipe.addIngredient(new Ingredient("spring onion", 1.0, "tablespoon", "chopped"));
		recipe.addIngredient(new Ingredient("chicken wings", 1.5, "kg"));
		recipe.setServeNum(4);
		assertTrue(recipe.getIngredients().get(0).getAmount() == 2.0);
		assertTrue(recipe.getIngredients().get(1).getAmount() == 4.0);
		assertTrue(recipe.getIngredients().get(2).getAmount() == 2.0);
		assertTrue(recipe.getIngredients().get(3).getAmount() == 3.0);
	}

}
