package view.customized;

import java.util.List;

import controller.customized.PreparationStepBoxController;
import entity.PreparationStep;
import javafx.scene.layout.VBox;

/**
 * Box for preparation steps on which users can do some modify.
 * 
 * @author JGroup
 *
 */
public class PreparationStepBox {
	
	private List<PreparationStep> preps;
	private VBox container;
	private int recipe_id;
	
	/**
	 * Constructor.
	 * 
	 * @param preps List of preparation steps.
	 * @param container VBox to place the steps.
	 * @param recipe_id Recipe id.
	 */
	public PreparationStepBox(List<PreparationStep> preps, VBox container, int recipe_id) {
		this.preps = preps;
		this.container = container;
		this.recipe_id = recipe_id;
	}
	
	/**
	 * Generate the preparation steps.
	 */
	public void generate() {
		PreparationStepBoxController.generate(this.container, this.preps, this.recipe_id);
	}
	
}
