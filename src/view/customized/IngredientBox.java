package view.customized;

import java.util.Iterator;
import java.util.List;

import controller.customized.IngredientBoxController;
import entity.Ingredient;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * Box for ingredients.
 * 
 * @author JGroup
 *
 */
public class IngredientBox {
	
	private VBox container;
	private int recipe_id;
	private List<Ingredient> ings;
	
	/**
	 * Constructor.
	 * 
	 * @param ings List of ingredients.
	 * @param container Container for ingredients.
	 * @param recipe_id Recipe id.
	 */
	public IngredientBox(List<Ingredient> ings, VBox container, int recipe_id) {
		this.container = container;
		this.recipe_id = recipe_id;
		this.ings = ings;
	}
	
	/**
	 * Generate the whole box.
	 */
	public void generate() {
		container.getChildren().clear();
		Iterator<Ingredient> it = ings.iterator();
		while(it.hasNext()) {
			Ingredient ing = it.next();
			VBox oneBox = this.generateOne(ing);
			this.container.getChildren().add(oneBox);
		}
	}
	
	/**
	 * Generate one ingredient one.
	 * 
	 * @param ing Ingredient.
	 * @return Box.
	 */
	private VBox generateOne(Ingredient ing) {
		Label nameLabel = new Label();
		Label amountLabel = new Label();
		Label unitLabel = new Label(); 
		Label descriptionLabel = new Label();
		
		nameLabel.setPrefWidth(170);
		amountLabel.setPrefWidth(35);
		unitLabel.setPrefWidth(100);
		
		nameLabel.setText(ing.getIngredientName() + ": ");
		amountLabel.setText(String.valueOf(ing.getAmount()));
		unitLabel.setText(ing.getUnit());
		descriptionLabel.setText(ing.getDescription());
		
		HBox ingBox = new HBox();
		ingBox.getChildren().addAll(nameLabel, amountLabel, unitLabel, descriptionLabel);
		
		Button edit_btn = new Button("Edit");
		edit_btn.setPrefWidth(100);
		edit_btn.setId("secondary-button");
		Button delete_btn = new Button("Delete");
		delete_btn.setPrefWidth(100);
		delete_btn.setId("secondary-button");
		
		HBox buttonBox = new HBox(5);
		buttonBox.setPrefWidth(240);
		buttonBox.setAlignment(Pos.CENTER_RIGHT);
		buttonBox.getChildren().addAll(edit_btn, delete_btn);
		VBox ingredientBox = new VBox(5);
		
		// Add listener.
		delete_btn.setOnAction(e -> {
			IngredientBoxController.delete(recipe_id, ing, ings, container, ingredientBox);
		});
		edit_btn.setOnAction(e -> {
			IngredientBoxController.edit(recipe_id, ing, ings, container, nameLabel, amountLabel, unitLabel, descriptionLabel);
		});
		
		ingredientBox.getChildren().addAll(ingBox, buttonBox, new Separator());
		return ingredientBox;
	}
	
}
