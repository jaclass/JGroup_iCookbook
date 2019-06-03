package controller.view;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import entity.Recipe;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import view.customized.IngredientViewBox;
import view.customized.PreparationStepViewBox;

/**
 * Controller for RecipeView.
 * 
 * @author JGroup
 *
 */
public class RecipeViewController implements Initializable {
	
	private Recipe selectedRecipe;
	private String username;
	
	@FXML private Label usernameLabel;
	@FXML private Label authorLabel;
	@FXML private Label recipeNameLabel;
	@FXML private Label serveNumLabel;
	@FXML private Label preparationTimeLabel;
	@FXML private Label cookingTimeLabel;
	
	@FXML private VBox ingredientBox;
	@FXML private VBox stepBox;

    /**
     * This method accepts a recipe to initialize the view.
     * 
     * @param recipe Selected recipe.
     */
    public void initData(Recipe recipe, String username) {
    	selectedRecipe = recipe;
    	this.username = username;
    	
    	usernameLabel.setText(username);
    	authorLabel.setText(selectedRecipe.getAuthor());
    	recipeNameLabel.setText(selectedRecipe.getRecipeName());
    	serveNumLabel.setText(String.valueOf(selectedRecipe.getServeNum()));
    	preparationTimeLabel.setText(String.valueOf(selectedRecipe.getPreparationTime()));
    	cookingTimeLabel.setText(String.valueOf(selectedRecipe.getCookingTime()));
    	
    	(new IngredientViewBox(recipe.getIngredients(), this.ingredientBox)).generate();
    	(new PreparationStepViewBox(recipe.getPreparationSteps(), this.stepBox)).generate();
    }

    /**
     * When push the button, back to HomeView.
     * 
     * @param event
     * @throws IOException
     */
    public void backButtonPushed(ActionEvent event) throws IOException {
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
    }
    
	/**
	 * When click the username, then change to UserOwnView.
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
	 * Initialize the view.
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
	}

}
