package view.customized;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Genrate an alert box.
 * 
 * @author JGroup
 *
 */
public class AlertBox {
	
	/**
	 * Display one alert box.
	 * 
	 * @param title Title.
	 * @param message Message to show.
	 */
	public static void display(String title, String message) {
		Stage window = new Stage();
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle(title);
		window.setWidth(400);
		window.setHeight(100);
		window.setResizable(false);
		window.getIcons().add(new Image("/image/app_icon.png"));
		
		Label label = new Label();
		label.setText(message);
		
		Button okButton = new Button("OK");
		okButton.setPrefWidth(50);
		
		okButton.setOnAction(e -> {
			window.close();
		});
		
		VBox layout = new VBox(10);
		layout.setAlignment(Pos.CENTER);
		layout.getChildren().addAll(label, okButton);
		
		Scene scene = new Scene(layout);
		scene.getStylesheets().add("/application/application.css");
		window.setScene(scene);
		window.showAndWait();
	}
	
}
