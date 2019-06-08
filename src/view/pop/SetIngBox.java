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
 * Box for editing and adding ingredients.
 * 
 * @author JGroup
 *
 */
public class SetIngBox {

	private String title;
	private Ingredient message;
	private Ingredient result;

	/**
	 * Constructor with title.
	 * 
	 * @param title Title.
	 */
	public SetIngBox(String title) {
			this.title = title;
			this.message = null;
			this.result = null;
		}

	/**
	 * Constructor with title and message.
	 * 
	 * @param title Title.
	 * @param message Default ingredient.
	 */
	public SetIngBox(String title, Ingredient message) {
			this.title = title;
			this.message = message;
			this.result = null;
	}

	/**
	 * Display one box.
	 * 
	 * @return Ingredient.
	 */
	public Ingredient display() {
		Stage window = new Stage();
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle(this.title);
		window.setWidth(400);
		window.setHeight(130);
		window.setResizable(false);
		window.getIcons().add(new Image("/image/icon.png"));
		
		HBox inputBox = new HBox(3);
		TextField getName = new TextField();
		getName.setMaxWidth(80);
		TextField getAmount = new TextField();
		getAmount.setMaxWidth(50);
		TextField getUnit = new TextField();
		getUnit.setMaxWidth(80);
		TextField getDes = new TextField();
		getDes.setMaxWidth(180);
		
		if(this.message != null) {
			// Edit: set default value.
			getName.setText(message.getIngredientName());
			getAmount.setText(Double.toString(message.getAmount()));
			getUnit.setText(message.getUnit());
			getDes.setText(message.getDescription());
		}
		
		VBox vbox1 = new VBox(2);
		vbox1.getChildren().addAll(getName, new Label("Name"));
		vbox1.setAlignment(Pos.CENTER);
		VBox vbox2 = new VBox(2);
		vbox2.getChildren().addAll(getAmount, new Label("Amount"));
		vbox2.setAlignment(Pos.CENTER);
		VBox vbox3 = new VBox(2);
		vbox3.getChildren().addAll(getUnit, new Label("Unit"));
		vbox3.setAlignment(Pos.CENTER);
		VBox vbox4 = new VBox(2);
		vbox4.getChildren().addAll(getDes, new Label("Description"));
		vbox4.setAlignment(Pos.CENTER);
		
		inputBox.setAlignment(Pos.CENTER);
		inputBox.getChildren().addAll(vbox2, vbox3, vbox1, vbox4);

		Button okButton = new Button("OK");
		okButton.setPrefWidth(50);
		okButton.setOnAction(e -> {
			double amount;
			try {
				 amount = Double.parseDouble(getAmount.getText().trim());
				 //System.out.println(amount);
			}catch(NumberFormatException exception) {
				window.close();
				AlertBox.display("Number Error", "You can just input the positive integer!");
				return;
			}
			this.result = new Ingredient(getName.getText() == null ? "" : getName.getText(), 
										amount, 
										getUnit.getText() == null ? "" : getUnit.getText(), 
										getDes.getText() == null ? "" : getDes.getText());
			//System.out.println(this.result.getAmount());
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
