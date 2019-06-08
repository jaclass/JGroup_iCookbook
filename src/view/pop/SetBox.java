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
	
	private String info;
	private String title;
	private String message;
	private String result;

	/**
	 * Constructor with title and info.
	 * 
	 * @param title Title.
	 * @param info Information to show.
	 */
	public SetBox(String title, String info) {
		this.info = info;
		this.title = title;
		this.result = null;
	}
	
	/**
	 * Constructor with title, info and message.
	 * 
	 * @param title Title.
	 * @param info Information.
	 * @param message Default message.
	 */
	public SetBox(String title, String info,  String message) {
		this.info = info;
		this.title = title;
		this.message = message;
		this.result = null;
	}
	
	/**
	 * Display one box.
	 * 
	 * @return Message.
	 */
	public String display() {
		Stage window = new Stage();
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle(this.title);
		window.setWidth(400);
		window.setHeight(130);
		window.setResizable(false);
		window.getIcons().add(new Image("/image/icon.png"));
		
		Label label = new Label();
		label.setText(this.info);
		
		TextField get = new TextField(this.message);
		get.setMaxWidth(300);
		
		Button okButton = new Button("OK");
		okButton.setPrefWidth(50);
		okButton.setOnAction(e -> {
			this.result= get.getText() == null ? "" : get.getText();
			window.close();
		});
		
		VBox layout = new VBox(5);
		layout.setAlignment(Pos.CENTER);
		layout.getChildren().addAll(label, get, okButton);
		
		Scene scene = new Scene(layout);
		scene.getStylesheets().add("/application/application.css");
		window.setScene(scene);
		window.showAndWait();
		return this.result;
	}
	
	// Getters and Setters.
	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

}
