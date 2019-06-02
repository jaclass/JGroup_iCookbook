/**
 * 
 */
package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import controller.db.DBController;
import entity.PreparationStep;

/**
 * @author 39873
 *
 */
class PreparationStepDBTest {

	@Test
	void test() {
		DBController.insertPreparationStep(1, 5, new PreparationStep("inserted prepStep"));
		DBController.deletePreparationStep(1, 5);
	}

}
