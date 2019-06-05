/**
 * 
 */
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

/** Controller of customized IngredientBox
 * @author SG
 *
 */
public class IngredientBoxController {
	
	/**
	 * Delete one IngredientBox
	 * 
	 * @param recipe_id the id of recipe
	 * @param ing the deleted ingredient
	 * @param container the container of all ingredients
	 */
	public static void delete(int recipe_id, Ingredient ing, List<Ingredient> ings, VBox container, VBox self) {
		Boolean option = ConfirmBox.display("Delete",
				"Are you sure to delete this ingredient?");
		if(option==true) {
			if(DBController.deleteIngredient(recipe_id, ing)!=-1) {
				(new IngredientBox(DBController.getIngredientsOfRecipe(recipe_id),container,recipe_id)).generate();
			}else {
				AlertBox.display("DB error", "Something runs wrong with datebase");
			}		
		}
	}
	
	public static void add(int recipe_id, List<Ingredient> ings,VBox container) {
		Ingredient resultIng = (new SetIngBox("Add new ingredient")).display();
		if(resultIng==null) {
			return ;
		}else if(resultIng.getIngredientName().trim().length()==0||resultIng.getUnit().trim().length()==0) {
			AlertBox.display("Cannot update", "Name, Amount and Unit cannot be empty!");
			return;
		}else {
			Iterator<Ingredient> it = ings.iterator();
			while(it.hasNext()) {
				if((it.next()).getIngredientName().equals(resultIng.getIngredientName())){
					AlertBox.display("Cannot update", "You already have this ingredient!");
					return ;
				}
			}
			
			if(DBController.insertIngredient(recipe_id, resultIng)!=-1) {
				AlertBox.display("Add new Ingredient", "You have successfully add new ingredient: "+resultIng+" !");
				(new IngredientBox(DBController.getIngredientsOfRecipe(recipe_id),container,recipe_id)).generate();
			}else {
				AlertBox.display("DB error", "Something runs wrong with datebase");
			}	
		}
	}
	
	public static void edit(int recipe_id, Ingredient ing, List<Ingredient> ings, VBox container, Label nameLabel, Label amountLabel, Label unitLabel, Label desLabel) {
		Ingredient resultIng = (new SetIngBox("Add new ingredient",ing)).display();
		if(resultIng==null) {
			return ;
		}else if(resultIng.getIngredientName().trim().length()==0||resultIng.getUnit().trim().length()==0) {
			AlertBox.display("Cannot update", "Name, Amount and Unit cannot be empty!");
			return;
		}else {
			Iterator<Ingredient> it = ings.iterator();
			if(!resultIng.getIngredientName().equals(ing.getIngredientName())) {
				while(it.hasNext()) {
					if((it.next()).getIngredientName().equals(resultIng.getIngredientName())){
						AlertBox.display("Cannot update", "You already have this ingredient!");
						return ;
					}
				}
			}
			if(DBController.updateIngredient(recipe_id, ing.getIngredientName(), resultIng)!=-1) {
				(new IngredientBox(DBController.getIngredientsOfRecipe(recipe_id),container,recipe_id)).generate();
			}else {
				AlertBox.display("DB error", "Something runs wrong with datebase");
			}	
		}
	}
}
