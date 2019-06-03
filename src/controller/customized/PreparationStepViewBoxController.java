package controller.customized;

import java.util.Iterator;
import java.util.List;

import entity.PreparationStep;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

/**
 * Controller for PreparationStepViewBox.
 * 
 * @author JGroup
 *
 */
public class PreparationStepViewBoxController {

	/**
	 * Generate the box for preparation steps.
	 * 
	 * @param steps List of steps.
	 * @param container Box container.
	 */
	public static void generate(List<PreparationStep> steps, VBox container) {
		container.getChildren().clear();
		Iterator<PreparationStep> it = steps.iterator();
		while(it.hasNext()) {
			PreparationStep step = it.next();
			
			Label detaiLabel = new Label();
			detaiLabel.setWrapText(true);
			detaiLabel.setPrefWidth(560);
			detaiLabel.setText("· " + step.getDetail());
			
			container.getChildren().add(detaiLabel);
		}			
	}
	
}
