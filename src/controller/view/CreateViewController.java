package controller.view;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import controller.customized.IngredientBoxController;
import controller.db.DBController;
import entity.PreparationStep;
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
import view.customized.IngredientBox;
import view.customized.PreparationStepBox;
import view.pop.AlertBox;
import view.pop.SetBox;

/**
 * Controller for CreateView.
 * 
 * @author JGroup
 *
 */
public class CreateViewController implements Initializable{
	private Recipe selectedRecipe;
	private String username;
	
	/* @FXML private Label usernameLabel; */
	@FXML private Label authorLabel;
	@FXML private Label recipeNameLabel;
	@FXML private Label serveNumLabel;
	@FXML private Label preparationTimeLabel;
	@FXML private Label cookingTimeLabel;
	@FXML private VBox stepBox;
	@FXML private VBox ingredientBox;
	
	/**
	 * Initiate the view.
	 * 
	 * @param username Username.
	 */
	public void initData(Recipe item,String username) {
		this.username = username;
		this.selectedRecipe = item;
    	
		/* usernameLabel.setText(username); */
    	authorLabel.setText(selectedRecipe.getAuthor());
    	recipeNameLabel.setText(selectedRecipe.getRecipeName());
    	serveNumLabel.setText(String.valueOf(selectedRecipe.getServeNum()));
    	preparationTimeLabel.setText(String.valueOf(selectedRecipe.getPreparationTime())+"");
    	cookingTimeLabel.setText(String.valueOf(selectedRecipe.getCookingTime())+"");
    	(new PreparationStepBox(item.getPreparationSteps(),stepBox,item.getRecipeId())).generate();
    	(new IngredientBox(item.getIngredients(),ingredientBox,item.getRecipeId())).generate();
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
	 * When the button clicked, update serNum.
	 * 
	 * @param event
	 * @throws IOException
	 */
	public void updateSerNum(ActionEvent event) throws IOException {
		System.out.println(1);
		String result = (new SetBox("edit","Edit your serve number",this.serveNumLabel.getText())).display();
		if(result==null) {
			return ;
		}
		if(result.trim().length()==0) {
			AlertBox.display("Edit Failure", "The serve number cannot be empty!");
			return ;
		}
		int ret;
		try {
			 ret = Integer.parseInt(result.trim());
		}catch(NumberFormatException exception) {
			AlertBox.display("Number Error", "The value of serve number must be a number!");
			return ;
		}
		DBController.updateServeNum(selectedRecipe.getRecipeId(), ret);
		this.serveNumLabel.setText(result);
	}
	
	/**
	 * When the button clicked, update PreparationTime.
	 * 
	 * @param event
	 * @throws IOException
	 */
	public void updatePreparationTime(ActionEvent event) throws IOException {
		String result = (new SetBox("edit","Edit your preparation time",this.preparationTimeLabel.getText())).display();
		if(result==null) {
			return ;
		}
		if(result.trim().length()==0) {
			AlertBox.display("Edit Failure", "The preparation time cannot be empty!");
			return ;
		}
		int ret;
		try {
			 ret = Integer.parseInt(result.trim());
		}catch(NumberFormatException exception) {
			AlertBox.display("Number Error", "The value of preparation time must be a number!");
			return ;
		}
		DBController.updatePrepTime(selectedRecipe.getRecipeId(), ret);
		this.preparationTimeLabel.setText(result);
	}
	
	/**
	 * When the button clicked, update cooking time.
	 * 
	 * @param event
	 * @throws IOException
	 */
	public void updateCookingTime(ActionEvent event) throws IOException {
		String result = (new SetBox("edit","Edit your cooking time",this.cookingTimeLabel.getText())).display();
		if(result==null) {
			return ;
		}
		if(result.trim().length()==0) {
			AlertBox.display("Edit Failure", "The preparation time cannot be empty!");
			return ;
		}
		int ret;
		try {
			 ret = Integer.parseInt(result.trim());
		}catch(NumberFormatException exception) {
			AlertBox.display("Number Error", "The value of preparation time must be a number!");
			return ;
		}
		DBController.updateCookTime(selectedRecipe.getRecipeId(), ret);
		this.cookingTimeLabel.setText(result);
	}
	
	/**
	 * When the button clicked, add new ingredient.
	 * 
	 * @param event
	 * @throws IOException
	 */
	public void addIngredientClicked(ActionEvent event) throws IOException {
		IngredientBoxController.add(selectedRecipe.getRecipeId(), selectedRecipe.getIngredients(), ingredientBox);
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
	}

}
