package controller.view;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class CreateViewController implements Initializable{
	
	private String username;
	
	public void initData(String username) {
		this.username = username;
	}
	
    public void discardButtonPushed(ActionEvent event) throws IOException {
    	FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("view/fxml/UserOwnView.fxml"));
		Parent userOwnViewParent = loader.load();
		
        Scene userOwnViewScene = new Scene(userOwnViewParent);
        
        // This line gets the Stage information.
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(userOwnViewScene);
        window.show();
        
		// Access the controller and call a method.
        UserOwnViewController controller = loader.getController();
        controller.initData(username);
    }
    
    public void saveButtonPushed(ActionEvent event) throws IOException {
    	FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("view/fxml/UserOwnView.fxml"));
		Parent userOwnViewParent = loader.load();
		
        Scene userOwnViewScene = new Scene(userOwnViewParent);
        
        // This line gets the Stage information.
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(userOwnViewScene);
        window.show();
        
		// Access the controller and call a method.
        UserOwnViewController controller = loader.getController();
        controller.initData(username);
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
	}

}
