package view.customized;

import java.util.List;

import controller.customized.PreparationStepViewBoxController;
import entity.PreparationStep;
import javafx.scene.layout.VBox;

/**
 * Box to view the preparation steps.
 * 
 * @author JGroup
 *
 */
public class PreparationStepViewBox{
	private List<PreparationStep> steps;
	private VBox container;
	
	/**
	 * Constructor.
	 * 
	 * @param steps List of steps.
	 * @param container Box container.
	 */
	public PreparationStepViewBox(List<PreparationStep> steps, VBox container) {
		super();
		this.steps = steps;
		this.container = container;
	}

	/**
	 * Generate the box.
	 */
	public void generate() {
		PreparationStepViewBoxController.generate(this.steps, this.container);
	}

	
}
