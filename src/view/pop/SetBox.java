package view.pop;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Generate a pop window with information to set.
 * 
 * @author Jgroup
 *
 */
public class SetBox {
	
	static String info;
	
	/**
	 * Display one box.
	 * 
	 * @param title Title.
	 * @param message Message to show.
	 */
	public static String display(String title, String message) {
		Stage window = new Stage();
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle(title);
		window.setWidth(400);
		window.setHeight(130);
		window.setResizable(false);
		window.getIcons().add(new Image("/image/app_icon.png"));
		
		Label label = new Label();
		label.setText(message);
		
		TextField get = new TextField();
		get.setMaxWidth(300);
		
		Button okButton = new Button("OK");
		okButton.setPrefWidth(50);
		
		okButton.setOnAction(e -> {
			setInfo(get.getText());
			window.close();
		});
		
		VBox layout = new VBox(5);
		layout.setAlignment(Pos.CENTER);
		layout.getChildren().addAll(label, get, okButton);
		
		Scene scene = new Scene(layout);
		scene.getStylesheets().add("/application/application.css");
		window.setScene(scene);
		window.showAndWait();
		
		return info;
	}
	
	/**
	 * Set the static parameter.
	 * 
	 * @param text Text to set.
	 */
	private static void setInfo(String text) {
		info = text;
	}

}
