/**
 * 
 */
package view.customized;

import java.util.Iterator;
import java.util.List;

import controller.view.PreparationStepBoxController;
import entity.Ingredient;
import entity.PreparationStep;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * @author 39873
 *
 */
public class PreparationStepBox {
	private List<PreparationStep> preps;
	private int recipe_id;
	private VBox container;
	public PreparationStepBox(List<PreparationStep> preps, VBox container, int recipe_id) {
		this.preps = preps;
		this.container = container;
		this.recipe_id = recipe_id;
	}
	public void generate() {
		PreparationStepBoxController.generate(this.container,this.preps,this.recipe_id);
	}
}
