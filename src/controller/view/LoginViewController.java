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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginViewController implements Initializable {
	
	private String username;
	private String password;
	
	@FXML private TextField usernameField;
	@FXML private PasswordField passwordField;

    /**
     * When this method is called, it will change the scene to RegisterView.
     * 
     * @throws IOException 
     */
    public void registerButtonPushed(ActionEvent event) throws IOException {
    	FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("view/fxml/RegisterView.fxml"));
		Parent registerViewParent = loader.load();
		
        Scene registerViewScene = new Scene(registerViewParent);
        
        // This line gets the Stage information.
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(registerViewScene);
        window.show();
    }
    
    /**
     * When this method is called, it will change the scene to HomeView.
     * 
     * @throws IOException 
     */
    public void loginButtonPushed(ActionEvent event) throws IOException {
    	username = usernameField.getText();
    	password = passwordField.getText();
    	
		Alert alert = new Alert(AlertType.INFORMATION);
		
		if(username.trim().equals("") || password.trim().equals("")) {
			alert.setTitle("Login Failure");
			alert.setHeaderText(null);
			alert.setContentText("Username and password cannot be empty!");
			alert.showAndWait();
			return;
		}
		
		String result = DBController.loginUser(new User(username,password));
		if(result != null) {
			// Login success.
			FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("view/fxml/HomeView.fxml"));
			Parent homeViewParent = loader.load();
			
			Scene homeViewScene = new Scene(homeViewParent);
			
			// This line gets the Stage information.
			Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
			
			window.setScene(homeViewScene);
			window.show();
			
			// Access the controller and call a method.
	        HomeViewController controller = loader.getController();
	        controller.initData(username);
	        
			return;
		}else {
			// Login failure
			alert.setTitle("Login Failure");
			alert.setHeaderText(null);
			alert.setContentText("Wrong username or password, please try again!");
			alert.showAndWait();
			return;
		}
    }
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
	}

}
