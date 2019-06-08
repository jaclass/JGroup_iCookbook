package entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javafx.scene.image.Image;

/** 
 * Recipe Entity.
 * 
 * @author JGroup
 *
 */
public class Recipe implements Serializable {
	
	private static final long serialVersionUID = 1361533885319330479L;
	
	private int recipeId;
	private String author;
	private String recipeName;
	private int serveNum;
	private int preparationTime;
	private int cookingTime;
	private List<Ingredient> ingredients = new ArrayList<>();
	private List<PreparationStep> preparationSteps = new ArrayList<>();
	private Image image;
	
	/**
	 * Constructor without parameters.
	 */
	public Recipe() {
		super();
	}

	/**
	 * Constructor with recipeName and serveNum.
	 * 
	 * @param recipeName The name of the recipe.
	 * @param serveNum Serve number.
	 */
	public Recipe(String recipeName, int serveNum) {
		this.recipeName = recipeName;
		this.serveNum = serveNum;
	}
	
	/**
	 * Constructor with recipeName, serveNum, preparationTime and cookingTime.
	 * 
	 * @param recipeName The name of the recipe.
	 * @param serveNum Serve number.
	 * @param preparationTime Preparation time.
	 * @param cookingTime Cooking time.
	 */
	public Recipe(String recipeName, int serveNum, int preparationTime, int cookingTime) {
		this.recipeName = recipeName;
		this.serveNum = serveNum;
		this.preparationTime = preparationTime;
		this.cookingTime = cookingTime;
	}
	
	/**
	 * Constructor with recipeId, recipeName, serveNum, preparationTime and cookingTime.
	 * 
	 * @param recipeId Recipe id.
	 * @param recipeName The name of the recipe.
	 * @param serveNum Serve number.
	 * @param preparationTime Preparation time.
	 * @param cookingTime Cooking time.
	 */
	public Recipe(int recipeId, String recipeName, int serveNum, int preparationTime, int cookingTime) {
		super();
		this.recipeId = recipeId;
		this.recipeName = recipeName;
		this.serveNum = serveNum;
		this.preparationTime = preparationTime;
		this.cookingTime = cookingTime;
	}

	/**
	 * Constructor with recipeId, author, recipeName, serveNum, preparationTime and cookingTime.
	 * 
	 * @param recipeId Recipe id.
	 * @param author Author's name.
	 * @param recipeName The name of the recipe.
	 * @param serveNum Serve number.
	 * @param preparationTime Preparation time.
	 * @param cookingTime Cooking time.
	 */
	public Recipe(int recipeId, String author, String recipeName, int serveNum, int preparationTime, int cookingTime) {
		super();
		this.recipeId = recipeId;
		this.author = author;
		this.recipeName = recipeName;
		this.serveNum = serveNum;
		this.preparationTime = preparationTime;
		this.cookingTime = cookingTime;
	}

	/**
	 * Get the id of the reicipe.
	 * 
	 * @return Recipe id.
	 */
	public int getRecipeId() {
		return recipeId;
	}

	/**
	 * Set the id of the recipe.
	 * 
	 * @param recipeId Recipe id.
	 */
	public void setRecipeId(int recipeId) {
		this.recipeId = recipeId;
	}

	/**
	 * Get the author.
	 * 
	 * @return Author.
	 */
	public String getAuthor() {
		return author;
	}

	/**
	 * Set the author.
	 * 
	 * @param author Author's name.
	 */
	public void setAuthor(String author) {
		this.author = author;
	}

	/**
	 * Get the name of the recipe.
	 * 
	 * @return The name of the recipe.
	 */
	public String getRecipeName() {
		return recipeName;
	}

	/**
	 * Set the name of the recipe.
	 * 
	 * @param recipeName The name of the recipe.
	 */
	public void setRecipeName(String recipeName) {
		this.recipeName = recipeName;
	}

	/**
	 * Get the serve number.
	 * 
	 * @return Serve number.
	 */
	public int getServeNum() {
		return serveNum;
	}
	
	/**
	 * Set the serve number.
	 * When changing the serve number, the amount of all ingredients will change correspondingly.
	 * 
	 * @param value New serve number.
	 */
	public void setServeNum(int value) {
		double times = (double)value/this.serveNum;
		Iterator<Ingredient> it = ingredients.iterator();
		
		while(it.hasNext()) {
			Ingredient ing = it.next();
			double preAmount = ing.getAmount();
			// Display one decimal.
			ing.setAmount(Double.parseDouble(String.format("%.1f", preAmount*times)));
		}
		
		this.serveNum = value;
	}

	/**
	 * Get the preparation time.
	 * 
	 * @return The preparation time.
	 */
	public int getPreparationTime() {
		return preparationTime;
	}

	/**
	 * Set the preparation time.
	 * 
	 * @param preparationTime The preparation time.
	 */
	public void setPreparationTime(int preparationTime) {
		this.preparationTime = preparationTime;
	}

	/**
	 * Get the cooking time.
	 * 
	 * @return The cooking time.
	 */
	public int getCookingTime() {
		return cookingTime;
	}

	/**
	 * Set the cooking time.
	 * 
	 * @param cookingTime The cooking time.
	 */
	public void setCookingTime(int cookingTime) {
		this.cookingTime = cookingTime;
	}
	
	/**
	 * Get all ingredients.
	 * 
	 * @return List of ingredients.
	 */
	public List<Ingredient> getIngredients() {
		return ingredients;
	}

	/**
	 * Set a list of ingredients.
	 * 
	 * @param ingredients List of ingredients.
	 */
	public void setIngredients(List<Ingredient> ingredients) {
		this.ingredients = ingredients;
	}

	/**
	 * Get a list of preparation steps.
	 * 
	 * @return List of preparation steps.
	 */
	public List<PreparationStep> getPreparationSteps() {
		return preparationSteps;
	}

	/**
	 * Set a list of preparation steps.
	 * 
	 * @param preparationSteps List of preparation steps.
	 */
	public void setPreparationSteps(List<PreparationStep> preparationSteps) {
		this.preparationSteps = preparationSteps;
	}
	
	/**
	 * Get the image.
	 * 
	 * @return Image.
	 */
	public Image getImage() {
		return image;
	}

	/**
	 * Set the image.
	 * 
	 * @param image Image.
	 */
	public void setImage(Image image) {
		this.image = image;
	}

	/**
	 * Add the ingredient to recipe.
	 * 
	 * @param ingredient The input ingredient.
	 */
	public void addIngredient(Ingredient ingredient) {
		this.ingredients.add(ingredient);
	}
	
	/**
	 * Add the preparation step to recipe.
	 * 
	 * @param detail The input detail of the preparation step.
	 */
	public void addPreparationStep(String detail) {
		this.preparationSteps.add(new PreparationStep(detail));
	}
	
	/**
	 * Override the toString method of the recipe.
	 */
	@Override
	public String toString() {
		StringBuffer steps = new StringBuffer("[ ");
		if(!this.preparationSteps.isEmpty()) {
			Iterator<PreparationStep> stepIt = this.preparationSteps.iterator();
			while(stepIt.hasNext()) {
				steps.append(stepIt.next().toString() + ", ");
			}
			steps.delete(steps.length() - 2, steps.length());
		}
		steps.append("]");
		
		StringBuffer ings = new StringBuffer("[ ");
		if(!this.ingredients.isEmpty()) {
			Iterator<Ingredient> ingIt = this.ingredients.iterator();
			while(ingIt.hasNext()) {
				ings.append(ingIt.next().toString() + ", ");
			}
			ings.delete(ings.length() - 2, ings.length());
		}
		ings.append("]");
		
		return "Recipe {recipeId = " + recipeId + ", author = " + author + ", recipeName = " + recipeName + ", serveNum = " + serveNum
				+ ", preparationTime = " + preparationTime + ", cookingTime = " + cookingTime 
				+ ", ingredients = " + ings + ", preparationSteps = " + steps + "}";
	}
	
}

