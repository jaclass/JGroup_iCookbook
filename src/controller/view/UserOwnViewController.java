package controller.view;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import controller.db.DBController;
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
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import view.customized.RecipeModifyListCell;
import view.pop.AlertBox;
import view.pop.ConfirmBox;
import view.pop.SetBox;

/**
 * Controller for UserOwnView.
 * 
 * @author JGroup
 *
 */
public class UserOwnViewController implements Initializable{
	
	private String username;
    private ObservableList<Recipe> userRecipes;
	
	@FXML private ListView<Recipe> recipeListView;
	@FXML private VBox noRecipeBox;
    
    /**
     * Initialize the data.
     * 
     * @param username Username.
     */
    public void initData(String username) {
    	this.username = username;
    	
    	if (DBController.getRecipeByUsername(username).size() == 0) {
    		Label noRecipeLabel = new Label("Oh! You have no recipe! Create one now!");
    		noRecipeBox.getChildren().add(noRecipeLabel);
		} else {
			userRecipes = FXCollections.observableArrayList();
			userRecipes.clear();
			userRecipes.addAll(DBController.getRecipeByUsername(username));
			
			recipeListView.getItems().clear();
			recipeListView.getItems().addAll(userRecipes);
			recipeListView.setCellFactory(param -> new RecipeModifyListCell(username));
		}
    }
	
    /**
     * When push the button, change to HomeView.
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
     * When push the button, change to LoginView.
     * 
     * @param event
     * @throws IOException
     */
    public void logoutButtonPushed(ActionEvent event) throws IOException {
    	Boolean answer = ConfirmBox.display("Sign Out", "Are you sure to sign out?");
    	
    	if (answer) {
    		FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("view/fxml/LoginView.fxml"));
    		Parent loginViewParent = loader.load();
    		
    		Scene loginViewScene = new Scene(loginViewParent);
    		
    		// This line gets the Stage information.
    		Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
    		
    		window.setScene(loginViewScene);
    		window.show();
		}
    }
    
    /**
     * When push the button, change to CreateView.
     * 
     * @param event
     * @throws IOException
     */
    public void createButtonPushed(ActionEvent event) throws IOException {
    	SetBox popBox = new SetBox("Recipe Name", "Please input the recipe name:");
		String get = popBox.display();
		if(get == null) {
			return;
		}else if(get.trim().length() == 0) {
			AlertBox.display("No Recipe Name", "You must put the recipe name!");
		}else {
			// Valid input.
			FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("view/fxml/CreateView.fxml"));
			Parent createViewParent;
			try {
				createViewParent = loader.load();
				Scene createViewScene = new Scene(createViewParent);
				
				// Insert an new empty recipe into database.
				Recipe insertedRecipe = new Recipe();
				insertedRecipe.setRecipeName(get);
				insertedRecipe.setAuthor(this.username);
				insertedRecipe.setServeNum(1);
				int inserted_id = DBController.insertRecipe(insertedRecipe);
				
				// This line gets the Stage information.
				Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
				
				window.setScene(createViewScene);
				window.show();
				
				// Access the controller and call a method.
				CreateViewController controller = loader.getController();
				controller.initData(DBController.getRecipeById(inserted_id),username);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
    }

	/**
	 * Initialize the view.
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
	}

}
