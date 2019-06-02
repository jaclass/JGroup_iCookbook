package controller.view;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import controller.db.DBController;
import entity.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class RegisterViewController implements Initializable {
	
	private String username;
	private String password;
	private String confirm;
	
	@FXML private TextField usernameField;
	@FXML private PasswordField passwordField;
	@FXML private PasswordField confirmField;

    /**
     * When this method is called, it will change the scene to LoginView.
     * 
     * @throws IOException 
     */
    public void createAccountButtonPushed(ActionEvent event) throws IOException {
    	username = usernameField.getText();
    	password = passwordField.getText();
    	confirm = confirmField.getText();
    	
		String regex = "^[0-9A-Za-z]{6,12}$";
		Alert alert = new Alert(AlertType.INFORMATION);
		
		if(username.trim().equals("") || password.trim().equals("") || confirm.trim().equals("")) {
			alert.setTitle("Register Failure");
			alert.setHeaderText(null);
			alert.setContentText("Username, password and confirm cannot be empty!");
			alert.showAndWait();
			return;
		}
		if(!username.matches(regex)) {
			alert.setTitle("Register Failure");
			alert.setHeaderText(null);
			alert.setContentText("Username should consist of 6-12 characters or numbers");
			alert.showAndWait();
			return;
		}
		if(!password.matches(regex)) {
			alert.setTitle("Register Failure");
			alert.setHeaderText(null);
			alert.setContentText("Password should consist of 6-12 characters or numbers");
			alert.showAndWait();
			return;
		}
		if(!password.equals(confirm)) {
			alert.setTitle("Register Failure");
			alert.setHeaderText(null);
			alert.setContentText("Passwords don't match!");
			alert.showAndWait();
			return;
		}
		if(DBController.insertUser(new User(username,password)) == -1) {
			alert.setTitle("Register Failure");
			alert.setHeaderText(null);
			alert.setContentText("Username already exists, please change to another one!");
			alert.showAndWait();
			return;
		}
		
		alert.setTitle("Register Success");
		alert.setHeaderText(null);
		alert.setContentText("You have a new account, welcome to iCookBook!");
		alert.showAndWait();

    	FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("view/fxml/LoginView.fxml"));
		Parent loginViewParent = loader.load();
		
        Scene loginViewScene = new Scene(loginViewParent);
        
        // This line gets the Stage information.
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(loginViewScene);
        window.show();
    }
    
    public void loginButtonPushed(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("view/fxml/LoginView.fxml"));
		Parent loginViewParent = loader.load();
		
		Scene loginViewScene = new Scene(loginViewParent);
		
		// This line gets the Stage information.
		Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
		
		window.setScene(loginViewScene);
		window.show();
    }
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
	}

}
