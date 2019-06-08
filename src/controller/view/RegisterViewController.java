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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import view.pop.AlertBox;

/**
 * Controller for RegisterView.
 * 
 * @author JGroup
 *
 */
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
     * @param event
     * @throws IOException 
     */
    public void createAccountButtonPushed(ActionEvent event) throws IOException {
    	username = usernameField.getText();
    	password = passwordField.getText();
    	confirm = confirmField.getText();
    	
		String regex = "^[0-9A-Za-z]{6,12}$";
		
		if(username.trim().equals("") || password.trim().equals("") || confirm.trim().equals("")) {
			AlertBox.display("Register Failure", "Username, password and confirm cannot be empty!");
			return;
		}
		if(!username.matches(regex)) {
			AlertBox.display("Register Failure", "Username should consist of 6-12 characters or numbers!");
			return;
		}
		if(!password.matches(regex)) {
			AlertBox.display("Register Failure", "Password should consist of 6-12 characters or numbers!");
			return;
		}
		if(!password.equals(confirm)) {
			AlertBox.display("Register Failure", "Passwords don't match!");
			return;
		}
		if(DBController.insertUser(new User(username,password)) == -1) {
			AlertBox.display("Register Failure", "Username already exists, please change to another one!");
			return;
		}
		
		AlertBox.display("Register Success", "You have a new account, welcome to iCookBook!");

    	FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("view/fxml/LoginView.fxml"));
		Parent loginViewParent = loader.load();
		
        Scene loginViewScene = new Scene(loginViewParent);
        
        // This line gets the Stage information.
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(loginViewScene);
        window.show();
    }
    
    /**
     * When push the button, change to LoginView.
     * 
     * @param event
     * @throws IOException
     */
    public void loginButtonPushed(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("view/fxml/LoginView.fxml"));
		Parent loginViewParent = loader.load();
		
		Scene loginViewScene = new Scene(loginViewParent);
		
		// This line gets the Stage information.
		Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
		
		window.setScene(loginViewScene);
		window.show();
    }
	
	/**
	 * Initialize the view.
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
	}

}
