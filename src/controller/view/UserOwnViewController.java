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
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import view.listcell.RecipeListCell;

public class UserOwnViewController implements Initializable{
	
	private String username;
    private ObservableList<Recipe> userRecipes;
	
	@FXML private ListView<Recipe> recipeListView;
    
    public void initData(String username) {
    	this.username = username;
    	
    	userRecipes = FXCollections.observableArrayList();
    	userRecipes.clear();
    	userRecipes.addAll(DBController.getRecipeByUsername(username));
    	
    	recipeListView.getItems().clear();
    	recipeListView.getItems().addAll(userRecipes);
    	recipeListView.setCellFactory(param -> new RecipeListCell());
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
    
    public void logoutButtonPushed(ActionEvent event) throws IOException {
    	FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("view/fxml/LoginView.fxml"));
		Parent loginViewParent = loader.load();
		
        Scene loginViewScene = new Scene(loginViewParent);
        
        // This line gets the Stage information.
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(loginViewScene);
        window.show();
    }
    
    public void createButtonPushed(ActionEvent event) throws IOException {
    	FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("view/fxml/CreateView.fxml"));
		Parent createViewParent = loader.load();
		
        Scene createViewScene = new Scene(createViewParent);
        
        // This line gets the Stage information.
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(createViewScene);
        window.show();
        
		// Access the controller and call a method.
        CreateViewController controller = loader.getController();
        controller.initData(username);
    }
    
	public void itemMouseClicked(MouseEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("view/fxml/RecipeView.fxml"));
		Parent recipeViewParent = loader.load();
		
		Scene recipeViewScene = new Scene(recipeViewParent);
		
		// This line gets the Stage information.
		Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
		
		window.setScene(recipeViewScene);
		window.show();
		
		// Access the controller and call a method.
        RecipeViewController controller = loader.getController();
        controller.initData(recipeListView.getSelectionModel().getSelectedItem(), username);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
	}

}
