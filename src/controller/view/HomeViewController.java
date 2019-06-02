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
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import view.customized.RecipeListCell;

public class HomeViewController implements Initializable {

	private String username;
    private ObservableList<Recipe> recipes;
	
	@FXML private ListView<Recipe> recipeListView;
	@FXML private Label authorLabel;
	@FXML private TextField searchField;
	
	public HomeViewController() {
		recipes = FXCollections.observableArrayList();
		recipes.clear();
		recipes.addAll(DBController.getAllRecipes());
	}
	
	public void initData(String username) {
		this.username = username;
		authorLabel.setText(username);
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
	
	public void searchButtonPushed(ActionEvent event) {
		recipes.clear();
		recipes.addAll(DBController.searchRecipeByName(searchField.getText()));
		
		recipeListView.getItems().clear();
		recipeListView.getItems().addAll(recipes);
		recipeListView.setCellFactory(param -> new RecipeListCell());
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// Configuring the ListView.
		recipeListView.getItems().addAll(recipes);
		recipeListView.setCellFactory(param -> new RecipeListCell());
		// The same like:
		/*
		recipeListView.setCellFactory(new Callback<ListView<Recipe>, ListCell<Recipe>>() {
		    @Override
		    public ListCell<Recipe> call(ListView<Recipe> param) {
		        return new RecipeListCell();
		    }
		});
		*/
	}

}
