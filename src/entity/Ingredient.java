package entity;

import java.io.Serializable;

/** 
 * Ingredient Entity.
 * 
 * @author JGroup
 *
 */
public class Ingredient implements Serializable{
	
	private static final long serialVersionUID = 7720481568328987661L;
	
	private String ingredientName;
	private double amount;
	private String unit;
	private String description;
	
	/**
	 * Constructor with ingredient name, amount and unit.
	 * 
	 * @param ingredientName Ingredient name.
	 * @param amount The amount of the ingredient.
	 * @param unit The unit of the amount.
	 */
	public Ingredient(String ingredientName, double amount, String unit) {
		this.ingredientName = ingredientName;
		this.amount = amount;
		this.unit = unit;
	}
	
	/**
	 * Constructor with ingredient name, amount and unit.
	 * 
	 * @param ingredientName Ingredient name.
	 * @param amount The amount of the ingredient.
	 * @param unit The unit of the amount.
	 * @param description Attached description.
	 */
	public Ingredient(String ingredientName, double amount, String unit, String description) {
		this.ingredientName = ingredientName;
		this.unit = unit;
		this.amount = amount;
		this.description = description;
	}

	/**
	 * Get the ingredient name.
	 * 
	 * @return The ingredient name.
	 */
	public String getIngredientName() {
		return ingredientName;
	}
	
	/**
	 * Set the ingredient name.
	 * 
	 * @param ingredientName The ingredient name.
	 */
	public void setIngredientName(String ingredientName) {
		this.ingredientName = ingredientName;
	}
	
	/**
	 * Get the amount of the ingredient.
	 * 
	 * @return The amount of the ingredient.
	 */
	public double getAmount() {
		return amount;
	}
	
	/**
	 * Set the amount of the ingredient.
	 * 
	 * @param amount The amount of the ingredient.
	 */
	public void setAmount(double amount) {
		this.amount = amount;
	}
	
	/**
	 * Get the unit of the unit.
	 * 
	 * @return The unit of the amount.
	 */
	public String getUnit() {
		return unit;
	}
	
	/**
	 * Set the unit of the amount.
	 * 
	 * @param unit The unit of the amount.
	 */
	public void setUnit(String unit) {
		this.unit = unit;
	}

	/**
	 * Get the attached description.
	 * 
	 * @return Attached description.
	 */
	public String getDescription() {
		return description;
	}
	
	/**
	 * Set the attached description.
	 * 
	 * @param description Attached description.
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	
	/**
	 * Override the toString method of the class Ingredient.
	 */
	@Override
	public String toString() {
		return "Ingredient {ingredientName: " + ingredientName + ", unit: " + unit + ", amount: " + amount 
				+ ", description: " + description + "}";
	}
	
}

