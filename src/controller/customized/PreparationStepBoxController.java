package controller.customized;

import controller.db.DBController;
import entity.PreparationStep;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import view.customized.PreparationStepBox;
import view.pop.AlertBox;
import view.pop.ConfirmBox;
import view.pop.SetBox;

/**
 * Controller for PreparationStepBox.
 * 
 * @author JGroup
 *
 */
public class PreparationStepBoxController {

	/**
	 * ActionListener for edit button.
	 * 
	 * @param prep PreprationStep.
	 * @param label Label to put PreparationStep.
	 * @param id Recipe id.
	 */
	public static void edit(PreparationStep prep, Label label, int id) {
		SetBox popBox = new SetBox("Edit", "Edit Step", prep.getDetail());
		String get = popBox.display();
		if (get == null) {
			return;
		} else if (get.trim().length() == 0) {
			AlertBox.display("No Detail", "You must have the detail for each step!");
		} else { 
			// Valid input and update database.
			if (DBController.updatePreparationStep(id, prep.getStep(), get) != 0) {
				label.setText(prep.getStep() + ". " + get);
				prep.setDetail(get);
			} else {
				AlertBox.display("DB Error", "Something wrong with the datebase!");
			}
		}
	}

	/**
	 * ActionListener for delete button.
	 * 
	 * @param prep PreparationStep.
	 * @param vbox Box container.
	 * @param fpane Pane.
	 * @param id Recipe id.
	 */
	public static void delete(PreparationStep prep, VBox vbox, VBox fpane, int id) {
		if (vbox.getChildren().size() == 1) {
			// Cannot delete the remaining one.
			AlertBox.display("Cannot Delete", "You cannot delete the last step!");
			return;
		}
		Boolean option = ConfirmBox.display("Delete", "Are you sure to delete this step?");

		if (option) {
			// Delete from database.
			if (DBController.deletePreparationStep(id, prep.getStep()) != 0) {
				(new PreparationStepBox(DBController.getPreparationStepsOfRecipe(id), vbox, id)).generate();
			}
		}
	}

	/**
	 * ActionListener for insertBefore button.
	 * 
	 * @param prep PreparationStep.
	 * @param vbox Box.
	 * @param pane Pane.
	 * @param option Boolean.
	 * @param id Recipe id.
	 */
	public static void insert(PreparationStep prep, VBox vbox, VBox pane, boolean option, int id) {
		SetBox popBox = new SetBox("Insert", "Insert a new step:");
		String get = popBox.display();
		if (get == null) {
			return;
		} else if (get.trim().length() == 0) {
			// Empty String.
			AlertBox.display("No Detail", "You must have the detail for each step!");
		} else { 
			// Valid input and insert into database.
			if (option == true) {
				// Insert before.
				if (DBController.insertPreparationStep(id, prep.getStep(), new PreparationStep(get)) != -1) {
					(new PreparationStepBox(DBController.getPreparationStepsOfRecipe(id), vbox, id)).generate();
				} else {
					AlertBox.display("DB Error", "Something wrong with the datebase!");
				}
			} else {
				// Insert after.
				if (DBController.insertPreparationStep(id, prep.getStep() + 1, new PreparationStep(get)) != -1) {
					(new PreparationStepBox(DBController.getPreparationStepsOfRecipe(id), vbox, id)).generate();
				} else {
					AlertBox.display("DB Error", "Something wrong with the datebase!");
				}
			}
		}
	}
	
	/**
	 * ActionListener for add button appears when creating.
	 * 
	 * @param container Box.
	 * @param self Button itself.
	 * @param id Recipe id.
	 */
	public static void addFirst(VBox container, int id, Button self) {
		SetBox popBox = new SetBox("Add", "Add your first step:");
		String get = popBox.display();
		if(get == null) {
			return;
		}
		else if(get.trim().length() == 0) {
			// Empty String.
			AlertBox.display("No Detail", "You must have the detail for each step!");
		}
		else {
			// Valid input and update database.
			if(DBController.insertPreparationStep(id, 1, new PreparationStep(get)) != 0) {
				(new PreparationStepBox(DBController.getPreparationStepsOfRecipe(id), container, id)).generate();
				container.getChildren().remove(self);
			}else {
				AlertBox.display("DB Error", "Something wrong with the datebase!");
			}
		}
	}

}
