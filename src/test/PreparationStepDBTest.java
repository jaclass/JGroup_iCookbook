package test;

import org.junit.jupiter.api.Test;
import controller.db.DBController;
import entity.PreparationStep;

/**
 * Test the methods about the preparation steps.
 * 
 * @author JGroup
 *
 */
class PreparationStepDBTest {

	@Test
	void test() {
		DBController.insertPreparationStep(1, 5, new PreparationStep("inserted prepStep"));
		DBController.deletePreparationStep(1, 5);
		DBController.updatePreparationStep(1, 3, 
				"mix together the remaining 3 tbsp. soy sauce, rice wine, sugar, stock, vinegar, sesame oil, and dark soy sauce.");
	}

}
