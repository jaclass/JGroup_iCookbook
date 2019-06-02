package entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/** 
 * CookBook Entity
 * 
 * @author JGroup
 *
 */
public class CookBook implements Serializable{
	
	private static final long serialVersionUID = -7506232126600577021L;
	
	private String cookBookName;
	private List<Recipe> recipes = new ArrayList<>();
	
	/**
	 * Constructor with cookBookName.
	 * 
	 * @param cookbookName The name of the cook book.
	 */
	public CookBook(String cookBookName) {
		this.cookBookName = cookBookName;
	}
	
	/**
	 * Get the name of the cook book.
	 * 
	 * @return The name of the cook book.
	 */
	public String getCookbookName() {
		return cookBookName;
	}

	/**
	 * Set the name of the cook book.
	 * 
	 * @param cookbookName The name of the cook book.
	 */
	public void setCookbookName(String cookbookName) {
		this.cookBookName = cookbookName;
	}
	
	/**
	 * Get all recipes.
	 * 
	 * @return List of recipes.
	 */
	public List<Recipe> getRecipes() {
		return recipes;
	}

	/**
	 * Set a list of recipes.
	 * 
	 * @param recipes List of recipes.
	 */
	public void setRecipes(List<Recipe> recipes) {
		this.recipes = recipes;
	}

	/**
	 * Add a new recipe to the cookbook.
	 * 
	 * @param recipe New recipe.
	 */
	public void add(Recipe recipe) {
		recipes.add(recipe);
	}
	
	/**
	 * Get the specified recipe.
	 * 
	 * @param name The name of the recipe.
	 * @return The specified recipe.
	 */
	public Recipe getRecipe(String name) {
		Iterator<Recipe> it = recipes.iterator();
		while(it.hasNext()) {
			Recipe re = it.next();
			if(re.getRecipeName().equals(name)){
				System.out.println("You choose the recipe: " + re.getRecipeName() + ".");
				return re;
			}
		}
		return null;
	}

}

