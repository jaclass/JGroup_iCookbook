package controller.customized;

import java.util.Iterator;
import java.util.List;

import entity.Ingredient;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * Controller for IngredientViewBox.
 * 
 * @author JGroup
 *
 */
public class IngredientViewBoxController {

	/**
	 * Genrate the whole box.
	 * 
	 * @param ingredients List of ingredients.
	 * @param container Box container.
	 */
	public static void generate(List<Ingredient> ingredients, VBox container) {
		container.getChildren().clear();
		Iterator<Ingredient> it = ingredients.iterator();
		while(it.hasNext()) {
			Ingredient ing = it.next();
			HBox oneBox = IngredientViewBoxController.generateOne(ing);
			container.getChildren().add(oneBox);
		}		
	}

	/**
	 * Generate one box.
	 * 
	 * @param ing Ingredient.
	 * @return One box.
	 */
	private static HBox generateOne(Ingredient ing) {
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
		
		HBox ingredientBox = new HBox();
		ingredientBox.getChildren().addAll(nameLabel, amountLabel, unitLabel, descriptionLabel);
		
		return ingredientBox;
	}

}
