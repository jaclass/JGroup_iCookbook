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
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import view.customized.ConfirmBox;
import view.customized.RecipeModifyListCell;

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
    
    /**
     * Initialize the data.
     * 
     * @param username Username.
     */
    public void initData(String username) {
    	this.username = username;
    	
    	userRecipes = FXCollections.observableArrayList();
    	userRecipes.clear();
    	userRecipes.addAll(DBController.getRecipeByUsername(username));
    	
    	recipeListView.getItems().clear();
    	recipeListView.getItems().addAll(userRecipes);
    	recipeListView.setCellFactory(param -> new RecipeModifyListCell(username));
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
    	Stage stage = new Stage();
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.setWidth(400);
		stage.setHeight(100);
		stage.setTitle("Recipe Name");
		
		StackPane sPane = new StackPane();
		Scene scene = new Scene(sPane);
		FlowPane fpane = new FlowPane();
		fpane.setAlignment(Pos.CENTER);
		TextField input = new TextField();
		
		Button btn = new Button("create");
		fpane.getChildren().addAll(input, btn);
		sPane.getChildren().add(fpane);
		
		btn.setOnAction((e)->{
			String str = input.getText();
			if(str.equals(null)) {
				return ;
			}
	    	FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("view/fxml/CreateView.fxml"));
			Parent createViewParent;
			try {
				createViewParent = loader.load();
				Scene createViewScene = new Scene(createViewParent);
		        //insert an new empty recipe into db
		        Recipe insertedRecipe = new Recipe();
		        insertedRecipe.setRecipeName(str);
		        insertedRecipe.setAuthor(this.username);
		        int inserted_id = DBController.insertRecipe(insertedRecipe);
		        System.out.println(inserted_id);
		       
		        
		        // This line gets the Stage information.
		        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
		        
		        window.setScene(createViewScene);
		        window.show();
		        
				// Access the controller and call a method.
		        CreateViewController controller = loader.getController();
		        controller.initData(DBController.getRecipeById(inserted_id),username);
		        
		        stage.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
	        
		});
		
		stage.setScene(scene);
		stage.show();
        
    }

	/**
	 * Initialize the view.
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
	}

}
