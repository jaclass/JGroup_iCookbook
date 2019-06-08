package controller.customized;

import java.util.Iterator;
import java.util.List;

import controller.db.DBController;
import entity.Ingredient;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import view.customized.IngredientBox;
import view.pop.AlertBox;
import view.pop.ConfirmBox;
import view.pop.SetIngBox;

/** 
 * Controller of customized IngredientBox.
 * 
 * @author JGroup
 *
 */
public class IngredientBoxController {
	
	/**
	 * Delete one IngredientBox.
	 * 
	 * @param recipe_id Recipe id.
	 * @param ing Deleted ingredient.
	 * @param container Container of all ingredients.
	 */
	/**
	 * Delete one IngredientBox.
	 * 
	 * @param recipe_id Recipe id.
	 * @param ing Ingredient.
	 * @param ings List of ingredients.
	 * @param container Container.
	 * @param self Self-container.
	 */
	public static void delete(int recipe_id, Ingredient ing, List<Ingredient> ings, VBox container, VBox self) {
		Boolean option = ConfirmBox.display("Delete", "Are you sure to delete this ingredient?");
		if(option == true) {
			if(DBController.deleteIngredient(recipe_id, ing) != -1) {
				(new IngredientBox(DBController.getIngredientsOfRecipe(recipe_id), container, recipe_id)).generate();
			}else {
				AlertBox.display("DB Error", "Something wrong with the datebase!");
			}		
		}
	}
	
	/**
	 * Add one IngredientBox.
	 * 
	 * @param recipe_id Recipe id.
	 * @param ings Ingredient to be added.
	 * @param container Container.
	 */
	public static void add(int recipe_id, List<Ingredient> ings, VBox container) {
		Ingredient resultIng = (new SetIngBox("Add New Ingredient")).display();
		if(resultIng == null) {
			return;
		}else if(resultIng.getIngredientName().trim().length() == 0 || resultIng.getUnit().trim().length() == 0) {
			AlertBox.display("Cannot Update", "Name, amount and unit cannot be empty!");
			return;
		}else {
			Iterator<Ingredient> it = ings.iterator();
			while(it.hasNext()) {
				if((it.next()).getIngredientName().equals(resultIng.getIngredientName())){
					AlertBox.display("Cannot Update", "You already have this ingredient!");
					return ;
				}
			}
			
			if(DBController.insertIngredient(recipe_id, resultIng) != 0) {
				AlertBox.display("Add Successfully", "You have successfully add the new ingredient!");
				(new IngredientBox(DBController.getIngredientsOfRecipe(recipe_id), container, recipe_id)).generate();
			}else {
				AlertBox.display("DB Error", "Something wrong with the datebase!");
			}	
		}
	}
	
	/**
	 * Edit one ingredient.
	 * 
	 * @param recipe_id Recipe id.
	 * @param ing One ingredient.
	 * @param ings List of ingredients.
	 * @param container Container
	 * @param nameLabel Label for ingredient name.
	 * @param amountLabel Label for amount.
	 * @param unitLabel Label for unit.
	 * @param desLabel Label for description.
	 */
	public static void edit(int recipe_id, Ingredient ing, List<Ingredient> ings, VBox container, Label nameLabel, Label amountLabel, Label unitLabel, Label desLabel) {
		Ingredient resultIng = (new SetIngBox("Add New Ingredient", ing)).display();
		if(resultIng == null) {
			return;
		}else if(resultIng.getIngredientName().trim().length() == 0 || resultIng.getUnit().trim().length() == 0) {
			AlertBox.display("Cannot Update", "Name, amount and unit cannot be empty!");
			return;
		}else {
			Iterator<Ingredient> it = ings.iterator();
			if(!resultIng.getIngredientName().equals(ing.getIngredientName())) {
				while(it.hasNext()) {
					if((it.next()).getIngredientName().equals(resultIng.getIngredientName())){
						AlertBox.display("Cannot Update", "You already have this ingredient!");
						return;
					}
				}
			}
			
			if(DBController.updateIngredient(recipe_id, ing.getIngredientName(), resultIng) != 0) {
				(new IngredientBox(DBController.getIngredientsOfRecipe(recipe_id), container, recipe_id)).generate();
			}else {
				AlertBox.display("DB Error", "Something wrong with the datebase!");
			}	
		}
	}
}
