package view.customized;

import java.util.List;

import controller.customized.IngredientViewBoxController;
import entity.Ingredient;
import javafx.scene.layout.VBox;

/**
 * Box to place the ingredient.
 * 
 * @author JGroup
 *
 */
public class IngredientViewBox{
	private List<Ingredient> ingredients;
	private VBox container;
	
	/**
	 * Constructor.
	 * 
	 * @param ingredients List of ingredients.
	 * @param container Box container.
	 */
	public IngredientViewBox(List<Ingredient> ingredients, VBox container) {
		super();
		this.ingredients = ingredients;
		this.container = container;
	}

	/**
	 * Generate the box.
	 */
	public void generate() {
		IngredientViewBoxController.generate(this.ingredients, this.container);
	}
}
