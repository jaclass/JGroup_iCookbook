/**
 * 
 */
package view.pop;

import entity.Ingredient;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * setBox for editing and adding ingredients
 * 
 * @author 39873
 *
 */
public class SetIngBox {

	private String title;
	private Ingredient message;
	private Ingredient result;

	public SetIngBox(String title) {
			this.title = title;
			this.message = null;
			this.result = null;
		}

	public SetIngBox(String title, Ingredient message) {
			this.title = title;
			this.message = message;
			this.result=null;
		}

	/**
	 * Display one box.
	 * 
	 * @return the set message.
	 */
	public Ingredient display() {
		Stage window = new Stage();
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle(this.title);
		window.setWidth(600);
		window.setHeight(130);
		window.setResizable(false);
		window.getIcons().add(new Image("/image/app_icon.png"));


		
		HBox inputBox = new HBox(5);
		TextField getName = new TextField();
		getName.setMaxWidth(100);
		TextField getAmount = new TextField();
		getAmount.setMaxWidth(50);
		TextField getUnit = new TextField();
		getUnit.setMaxWidth(100);
		TextField getDes = new TextField();
		getDes.setMaxWidth(250);
		
		if(this.message!=null) {	// edit : set default value
			getName.setText(message.getIngredientName());
			getAmount.setText(Double.toString(message.getAmount()));
			getUnit.setText(message.getUnit());
			getDes.setText(message.getDescription());
		}
		
		VBox vbox1 = new VBox(3);
		vbox1.getChildren().addAll(getName,new Label("name"));
		VBox vbox2 = new VBox(3);
		vbox2.getChildren().addAll(getAmount,new Label("amount"));
		VBox vbox3 = new VBox(3);
		vbox3.getChildren().addAll(getUnit,new Label("unit"));
		VBox vbox4 = new VBox(3);
		vbox4.getChildren().addAll(getDes,new Label("Description"));
		
		inputBox.setAlignment(Pos.CENTER);
		inputBox.getChildren().addAll(vbox1,vbox2,vbox3,vbox4);

		Button okButton = new Button("OK");
		okButton.setPrefWidth(50);
		okButton.setOnAction(e -> {
			double amount;
			try {
				 amount = Double.parseDouble(getAmount.getText().trim());
			}catch(NumberFormatException exception) {
				window.close();
				AlertBox.display("Number Error", "The value of unit must be a number!");
				return ;
			}
			this.result = new Ingredient(getName.getText()==null?"":getName.getText(), 
											amount, 
											getUnit.getText()==null?"":getUnit.getText(), 
											getDes.getText()==null?"":getDes.getText());
			window.close();
		});

		VBox layout = new VBox(5);
		layout.setAlignment(Pos.CENTER);
		layout.getChildren().addAll(inputBox, okButton);

		Scene scene = new Scene(layout);
		scene.getStylesheets().add("/application/application.css");
		window.setScene(scene);
		window.showAndWait();
		return this.result;
	}

	
	
	
	

}
