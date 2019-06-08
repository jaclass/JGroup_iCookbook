package view.pop;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Generate a confirm box with yes and no.
 * 
 * @author JGroup
 *
 */
public class ConfirmBox {
	
	static boolean answer = false;
	
	/**
	 * Display the confrim box.
	 * 
	 * @param title Title.
	 * @param message Message to show.
	 * @return Yes or no button pushed.
	 */
	@SuppressWarnings("unused")
	public static boolean display(String title, String message) {
		Stage window = new Stage();
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle(title);
		window.setWidth(400);
		window.setHeight(130);
		window.setResizable(false);
		window.getIcons().add(new Image("/image/icon.png"));
		
		Label label = new Label();
		label.setText(message);

		Button yesButton = new Button("Yes");
		Button noButton = new Button("No");
		yesButton.setPrefWidth(50);
		noButton.setPrefWidth(50);
		
		yesButton.setOnAction(e -> {
			answer = true;
			window.close();
		});
		noButton.setOnAction(e -> {
			answer = false;
			window.close();
		});
		
		boolean ret;
		ret = answer;
		answer = false;
		
		HBox buttonBox = new HBox(20);
		buttonBox.setAlignment(Pos.CENTER);
		buttonBox.getChildren().addAll(yesButton, noButton);
		
		VBox layout = new VBox(10);
		layout.setAlignment(Pos.CENTER);
		layout.getChildren().addAll(label, buttonBox);
		
		Scene scene = new Scene(layout);
		scene.getStylesheets().add("/application/application.css");
		window.setScene(scene);
		window.showAndWait();
		
		return answer;
	}
	
}
