package controller.customized;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import controller.db.DBController;
import entity.PreparationStep;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
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
	 * ActionListener for edit button
	 * 
	 * @param prep  PreprationStep.
	 * @param label Label to put PreparationStep.
	 * @param id    Recipe id.
	 */
	public static void edit(PreparationStep prep, Label label, int id) {
		SetBox popBox = new SetBox("Edit", "Edit this step", prep.getDetail());
		String get = popBox.display();
		if (get == null) { // X button
			return;
		} else if (get.trim().length() == 0) { // empty string
			AlertBox.display("No Detail", "You must have the detail for each step!");
		} else { // valid input
			// Update database.
			if (DBController.updatePreparationStep(id, prep.getStep(), get) != -1) {
				label.setText(prep.getStep() + ". " + get);
				prep.setDetail(get);
			} else {
				AlertBox.display("DB error", "Something runs wrong with datebase!");
			}
		}

	}

	/**
	 * ActionListener for delete button.
	 * 
	 * @param prep  PreparationStep.
	 * @param vbox  Box container.
	 * @param fpane Pane.
	 * @param id    Recipe id.
	 */
	public static void delete(PreparationStep prep, VBox vbox, VBox fpane, int id) {
		if (vbox.getChildren().size() == 1) {
			// Cannot delete the remaining one.
			AlertBox.display("Cannot Delete", "You cannot delete the last step!");
			return;
		}
		Boolean option = ConfirmBox.display("Delete",
				"Are you sure to delete this step?");

		if (option) {
			// Delete from database.
			if (DBController.deletePreparationStep(id, prep.getStep()) != -1) {
				// vbox.getChildren().remove(fpane);
				(new PreparationStepBox(DBController.getPreparationStepsOfRecipe(id), vbox, id)).generate();
				// generate(vbox,DBController.getPreparationStepsOfRecipe(id),id);
			}
		}
	}

	/**
	 * ActionListener for insertBefore button.
	 * 
	 * @param prep   PreparationStep.
	 * @param vbox   Box.
	 * @param pane   Pane.
	 * @param option Boolean.
	 * @param id     Recipe id.
	 */
	public static void insert(PreparationStep prep, VBox vbox, VBox pane, boolean option, int id) {
		SetBox popBox = new SetBox("Insert", "Insert A new step");
		String get = popBox.display();
		if (get == null) { // X button
			return;
		} else if (get.trim().length() == 0) { // empty String
			AlertBox.display("No Detail", "You must have the detail for each step!");
		} else { // valid input
			// Insert into database.
			if (option == true) {
				// Insert before.
				if (DBController.insertPreparationStep(id, prep.getStep(), new PreparationStep(get)) != -1) {
					(new PreparationStepBox(DBController.getPreparationStepsOfRecipe(id), vbox, id)).generate();
				} else {
					AlertBox.display("DB error", "Something runs wrong with datebase!");
				}
			} else {
				// Insert after.
				if (DBController.insertPreparationStep(id, prep.getStep() + 1, new PreparationStep(get)) != -1) {
					(new PreparationStepBox(DBController.getPreparationStepsOfRecipe(id), vbox, id)).generate();
				} else {
					AlertBox.display("DB error", "Something runs wrong with datebase!");
				}
			}
		}
	}
	/**
	 * ActionListener for add button appears when creating.
	 * 
	 * @param container   Box.
	 * @param self the button itself
	 * @param id  Recipe id.
	 */
	public static void addFirst(VBox container, int id, Button self) {
		SetBox popBox = new SetBox("Add", "Add your first step!");
		String get = popBox.display();
		if(get == null) {	// X button
			return ;
		}
		else if(get.trim().length() == 0) {	// empty string
			AlertBox.display("No Detail", "You must have the detail for each step!");
		}
		else {	// valid input
			// Update database.
			if(DBController.insertPreparationStep(id, 1, new PreparationStep(get))!=-1) {
				(new PreparationStepBox(DBController.getPreparationStepsOfRecipe(id), container, id)).generate();
				container.getChildren().remove(self);
			}else {
				AlertBox.display("DB error", "Something runs wrong with datebase!");
			}
		}
	}
	

	

}
