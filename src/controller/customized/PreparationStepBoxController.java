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
	 * @param prep PreprationStep.
	 * @param label Label to put PreparationStep.
	 * @param id Recipe id.
	 */
	public static void edit(PreparationStep prep, Label label, int id) {
		Stage stage = new Stage();
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.setWidth(400);
		stage.setHeight(100);
		stage.setTitle("Edit");

		StackPane sPane = new StackPane();
		Scene scene = new Scene(sPane);
		FlowPane fpane = new FlowPane();
		fpane.setAlignment(Pos.CENTER);
		TextField input = new TextField(prep.getDetail());

		Button btn = new Button("edit");
		fpane.getChildren().addAll(input,btn);
		sPane.getChildren().add(fpane);
		btn.setOnAction((e)->{
			String str = input.getText();
			label.setText("· "+str);
			// Update database.
			if(DBController.updatePreparationStep(id, prep.getStep(), str)!=-1) {
				prep.setDetail(str);
			}
			System.out.println(prep.getStep());

			stage.close();
		});
		stage.setScene(scene);
		stage.show();
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
		if(vbox.getChildren().size() == 1) {
			// Cannot delete the remaining one.
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Cannot Delete");
			alert.setHeaderText(null);
			alert.setContentText("There is only one preparation step, you cannot delete it!");
			return;
		}
		
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Delete");
		alert.setHeaderText(null);
		alert.setContentText("Are you sure to delete this step?");
		Optional<ButtonType> option = alert.showAndWait();
		
		if (option.get() == ButtonType.OK) {
			// Delete from database.
			if(DBController.deletePreparationStep(id, prep.getStep()) != -1) {
				// vbox.getChildren().remove(fpane);
				generate(vbox,DBController.getPreparationStepsOfRecipe(id),id);
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
		Stage stage = new Stage();
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.setWidth(400);
		stage.setHeight(100);
		stage.setTitle("Insert");
		
		StackPane sPane = new StackPane();
		Scene scene = new Scene(sPane);
		FlowPane fpane = new FlowPane();
		fpane.setAlignment(Pos.CENTER);
		TextField input = new TextField();
		
		Button btn = new Button("Insert");
		fpane.getChildren().addAll(input, btn);
		sPane.getChildren().add(fpane);
		
		btn.setOnAction((e)->{
			String str = input.getText();
			// Insert into database.
			if(option == true) {
				// Insert before.
				DBController.insertPreparationStep(id, prep.getStep(), new PreparationStep(str));
				generate(vbox, DBController.getPreparationStepsOfRecipe(id), id);
			}else {
				// Insert after.
				DBController.insertPreparationStep(id, prep.getStep() + 1, new PreparationStep(str));
				generate(vbox, DBController.getPreparationStepsOfRecipe(id), id);
			}
			stage.close();
		});
		
		stage.setScene(scene);
		stage.show();
	}
	
	/**
	 * Generate one flowpane.
	 * 
	 * @param prep PreparationStep.
	 * @param mainBox Box.
	 * @param id Recipe id.
	 * @return Generated box.
	 */
	public static VBox generateOne(PreparationStep prep, VBox mainBox, int id) {
		VBox oneBox = new VBox();
		oneBox.setMaxWidth(580);
		
		Label label = new Label("· "+prep.getDetail());
		label.setWrapText(true);
		label.setMaxWidth(560);
		
		Button edit_btn = new Button("Edit");
		edit_btn.setPrefWidth(100);
		Button delete_btn = new Button("Delete");
		delete_btn.setPrefWidth(100);
		Button insert_before_btn = new Button("Insert Before");
		insert_before_btn.setPrefWidth(100);
		Button insert_after_btn = new Button("Insert After");
		insert_after_btn.setPrefWidth(100);
		
		HBox buttonBox = new HBox(5);
		buttonBox.setPrefWidth(560);
		buttonBox.setAlignment(Pos.CENTER_RIGHT);
		buttonBox.getChildren().addAll(edit_btn, delete_btn, insert_before_btn, insert_after_btn);
		
		// Add Listener.
		edit_btn.setOnAction((e)->{
			PreparationStepBoxController.edit(prep, label, id);
		});
		delete_btn.setOnAction((e)->{
			PreparationStepBoxController.delete(prep, mainBox, oneBox, id);
		});
		insert_before_btn.setOnAction((e)->{
			PreparationStepBoxController.insert(prep, mainBox, oneBox, true, id);
		});
		insert_after_btn.setOnAction((e)->{
			PreparationStepBoxController.insert(prep, mainBox, oneBox, false, id);
		});
		
		oneBox.getChildren().addAll(label, buttonBox);
		return oneBox;
	}
	
	/**
	 * Generate the whole box.
	 * 
	 * @param container Box container.
	 * @param preps PreparationStep.
	 * @param id Recipe id.
	 */
	public static void generate(VBox container, List<PreparationStep> preps, int id) {
		container.getChildren().clear();
		Iterator<PreparationStep> it = preps.iterator();
		while(it.hasNext()) {
			PreparationStep prep = it.next();
			VBox oneBox = PreparationStepBoxController.generateOne(prep, container, id);
			container.getChildren().add(oneBox);
		}
	}
	
}
