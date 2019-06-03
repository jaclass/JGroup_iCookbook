package controller.view;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import controller.db.DBController;
import entity.PreparationStep;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import view.customized.PreparationStepBox;

/**
 * Controller for CreateView.
 * 
 * @author JGroup
 *
 */
public class CreateViewController implements Initializable{
	
	private String username;
	
	@FXML private VBox stepBox;
	
	/**
	 * Initiate the view.
	 * 
	 * @param username Username.
	 */
	public void initData(String username) {
		this.username = username;
	}
	
	/**
	 * When the button clicked, go to UserOwnView.
	 * 
	 * @param event
	 * @throws IOException
	 */
	public void usernameMouseClicked(MouseEvent event) throws IOException {
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
	
    /**
     * When the button pushed, then back to UserOwnView.
     * 
     * @param event
     * @throws IOException
     */
    public void finishButtonPushed(ActionEvent event) throws IOException {
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
    
    

	/**
	 * Initialize the view.
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		List<PreparationStep> preps = DBController.getPreparationStepsOfRecipe(1);
		(new PreparationStepBox(preps, this.stepBox, 1)).generate();
	}

}
