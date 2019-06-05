package view.customized;

import java.util.Iterator;
import java.util.List;

import controller.customized.PreparationStepBoxController;
import controller.db.DBController;
import entity.PreparationStep;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import view.pop.AlertBox;
import view.pop.SetBox;

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
	/*
	 * public void generate() {
	 * PreparationStepBoxController.generate(this.container, this.preps,
	 * this.recipe_id); }
	 */
	
	/**
	 * Generate the whole box.
	 * 
	 * @param container Box container.
	 * @param preps PreparationStep.
	 * @param id Recipe id.
	 */
	public void generate() {
		this.container.getChildren().clear();
		if(preps.size()==0) {	// create a new recipe
			Button add_btn = new Button("add one");
			this.container.getChildren().add(add_btn);
			//add listener to add_btn
			add_btn.setOnAction((e)->{
				/*
				 * SetBox popBox = new SetBox("Add", "Add your first step!"); String get =
				 * popBox.display(); if(get == null) { // X button return ; } else
				 * if(get.trim().length() == 0) { // empty string AlertBox.display("No Detail",
				 * "You must have the detail for each step!"); } else { // valid input // Update
				 * database. if(DBController.insertPreparationStep(this.recipe_id, 1, new
				 * PreparationStep(get))!=-1) {
				 * this.preps=DBController.getPreparationStepsOfRecipe(this.recipe_id);
				 * this.generate(); container.getChildren().remove(add_btn); }else {
				 * AlertBox.display("DB error", "Something runs wrong with datebase!"); } }
				 */	
				PreparationStepBoxController.addFirst(this.container, this.recipe_id, add_btn);
			});
			return ;
		}
		Iterator<PreparationStep> it = preps.iterator();
		while(it.hasNext()) {
			PreparationStep prep = it.next();
			VBox oneBox = this.generateOne(prep, container, recipe_id);
			container.getChildren().add(oneBox);
		}
	}
	
	/**
	 * Generate one flowpane.
	 * 
	 * @param prep PreparationStep.
	 * @param mainBox Box.
	 * @param id Recipe id.
	 * @return Generated box.
	 */
	public VBox generateOne(PreparationStep prep, VBox mainBox, int id) {
		VBox oneBox = new VBox();
		oneBox.setMaxWidth(580);
		
		Label label = new Label(prep.getStep()+". "+prep.getDetail());
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
	
}
