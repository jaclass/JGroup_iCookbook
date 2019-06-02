package controller.view;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import entity.Ingredient;
import entity.PreparationStep;
import entity.Recipe;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class RecipeViewController implements Initializable {
	
	private Recipe selectedRecipe;
	private String username;
	private ObservableList<Ingredient> ingredients;
	private ObservableList<PreparationStep> steps;
	
	@FXML private Label usernameLabel;
	@FXML private Label authorLabel;
	@FXML private Label recipeNameLabel;
	@FXML private Label serveNumLabel;
	@FXML private Label preparationTimeLabel;
	@FXML private Label cookingTimeLabel;
	@FXML private ListView<Ingredient> ingredientView;
	@FXML private ListView<String> stepView;

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
    	
    	ingredients = FXCollections.observableArrayList();
    	ingredients.clear();
    	ingredients.addAll(selectedRecipe.getIngredients());
    	
    	steps = FXCollections.observableArrayList();
    	steps.clear();
    	steps.addAll(selectedRecipe.getPreparationSteps());
    	
		for (int i = 1; i < selectedRecipe.getPreparationSteps().size() + 1; i++) {
			stepView.getItems().add(i + ". " + selectedRecipe.getPreparationSteps().get(i-1).getDetail());
		}
//		stepView.setCellFactory(param -> new StepListCell());
    }

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
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
	}

}
