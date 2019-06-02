/**
 * 
 */
package controller.view;

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
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * @author 39873
 *
 */
public class PreparationStepBoxController {
	
	//ActionListener for edit button
	public static void edit(PreparationStep prep, Label label,int id) {
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
			label.setText("¡¤ "+str);
			
			//update database
			DBController.updatePreparationStep(id, prep.getStep(), str);
			System.out.println(prep.getStep());
			
			stage.close();
		});
		stage.setScene(scene);
		stage.show();
	}
	
	//ActionListener for delete button
	public static void delete(PreparationStep prep,VBox vbox,FlowPane fpane, int id) {
		if(vbox.getChildren().size()==1) {	//cannot delete the remaining one
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Cannot Delete");
			alert.setHeaderText(null);
			alert.setContentText("There is only one preparation step, you cannot delete it!");
			return ;
		}
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Delete");
		alert.setHeaderText(null);
		alert.setContentText("Are you sure to delete this step?");
		Optional<ButtonType> option = alert.showAndWait();
		
		if (option.get() == ButtonType.OK) {
			//delete from database
			if(DBController.deletePreparationStep(id, prep.getStep())!=-1) {
				//vbox.getChildren().remove(fpane);
				generate(vbox,DBController.getPreparationStepsOfRecipe(id),id);
			}
		}
	}
	
	//ActionListener for insertBefore button
	public static void insert(PreparationStep prep,VBox vbox,FlowPane pane,boolean option,int id) {
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
		
		Button btn = new Button("insert");
		fpane.getChildren().addAll(input,btn);
		sPane.getChildren().add(fpane);
		
		btn.setOnAction((e)->{
			String str = input.getText();
			FlowPane newPane;
			//insert into database
			if(option == true) { //insert before
				DBController.insertPreparationStep(id, prep.getStep(), new PreparationStep(str));
				generate(vbox,DBController.getPreparationStepsOfRecipe(id),id);
			}else { //insert After
				DBController.insertPreparationStep(id, prep.getStep()+1, new PreparationStep(str));
				generate(vbox,DBController.getPreparationStepsOfRecipe(id),id);
			}
			
			
			stage.close();
		});
		stage.setScene(scene);
		stage.show();
	}
	
	public static FlowPane generateOne(PreparationStep prep, VBox mainBox, int id) {
		FlowPane fpane = new FlowPane();
		Label label = new Label("¡¤ "+prep.getDetail());
		Button edit_btn = new Button("edit");
		Button delete_btn = new Button("delete");
		Button insert_before_btn = new Button("insert before");
		Button insert_after_btn = new Button("insert after");
		
		//add Listener
		edit_btn.setOnAction((e)->{
			PreparationStepBoxController.edit(prep,label,id);
		});
		delete_btn.setOnAction((e)->{
			PreparationStepBoxController.delete(prep,mainBox,fpane,id);
		});
		insert_before_btn.setOnAction((e)->{
			PreparationStepBoxController.insert(prep,mainBox,fpane,true,id);
		});
		insert_after_btn.setOnAction((e)->{
			PreparationStepBoxController.insert(prep,mainBox,fpane,false,id);
		});
		fpane.getChildren().addAll(label,edit_btn,delete_btn,insert_before_btn,insert_after_btn);
		return fpane;
	}
	
	
	
	public static void generate(VBox container,List<PreparationStep> preps,int id) {
		container.getChildren().clear();
		VBox mainBox = new VBox(5);
		Iterator<PreparationStep> it = preps.iterator();
		while(it.hasNext()) {
			PreparationStep prep = it.next();
			FlowPane fpane = PreparationStepBoxController.generateOne(prep,mainBox,id);
			mainBox.getChildren().add(fpane);
		}
		container.getChildren().add(mainBox);
	}
	
}
