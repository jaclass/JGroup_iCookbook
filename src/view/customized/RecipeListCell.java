package view.customized;

import java.io.IOException;

import controller.view.RecipeViewController;
import entity.Recipe;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Cell to place the recipe list.
 * 
 * @author JGroup
 *
 */
public class RecipeListCell extends ListCell<Recipe>{

	private String username;

	/**
	 * Constructor to receive the username.
	 * 
	 * @param username Username.
	 */
	public RecipeListCell(String username) {
		super();
		this.username = username;
	}

	/**
	 * Generate the customized listcell.
	 */
	@Override
	public void updateItem(Recipe item, boolean empty) {
		super.updateItem(item, empty);
		
		setText(null);
		setGraphic(null);
		
        if (item != null && !empty) {
        	ImageView photo = new ImageView();
        	if (item.getImage() == null) {
				photo.setImage(new Image("/image/demo.jpg"));
			} else {
				photo.setImage(item.getImage());
			}
        	photo.setFitHeight(110);
        	photo.setFitWidth(120);
        	
        	Label recipeNameLabel = new Label();
        	Label authorTitle = new Label(); 
        	Label authorLabel = new Label();
        	Label serveNumTitle = new Label();
        	Label serveNumLabel = new Label();
        	Label preparationTimeTitle = new Label();
        	Label preparationTimeLabel = new Label();
        	Label cookingTimeTitle = new Label();
        	Label cookingTimeLabel = new Label();
        	Label unit = new Label();
        	
        	recipeNameLabel.setText(item.getRecipeName());
        	recipeNameLabel.setId("recipe-name");
        	
        	authorLabel.setText(item.getAuthor());
        	authorTitle.setText("Author: ");
        	HBox authorHBox = new HBox();
        	authorHBox.getChildren().addAll(authorTitle, authorLabel);
        	authorHBox.setId("recipe-infomation-box");
        	
        	serveNumTitle.setText("Serve Number: ");
        	serveNumLabel.setText(String.valueOf(item.getServeNum()));
        	preparationTimeTitle.setText(" | Preparation Time: ");
        	preparationTimeLabel.setText(String.valueOf(item.getPreparationTime()));
        	cookingTimeTitle.setText(" Min | CookingTime: ");
        	cookingTimeLabel.setText(String.valueOf(item.getCookingTime()));
        	unit.setText(" Min");
        	
        	HBox amountHBox = new HBox();
        	amountHBox.getChildren().addAll(serveNumTitle, serveNumLabel, preparationTimeTitle, preparationTimeLabel, cookingTimeTitle, cookingTimeLabel, unit);
        	amountHBox.setId("recipe-infomation-box");
        	
        	Button viewButton = new Button("View");
        	viewButton.setPrefWidth(60);
        	viewButton.setOnAction(e -> {
        		FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("view/fxml/RecipeView.fxml"));
        		Parent recipeViewParent = null;
				try {
					recipeViewParent = loader.load();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
        		
        		Scene recipeViewScene = new Scene(recipeViewParent);
        		
        		// This line gets the Stage information.
        		Stage window = (Stage)((Node)e.getSource()).getScene().getWindow();
        		
        		window.setScene(recipeViewScene);
        		window.show();
        		
        		// Access the controller and call a method.
                RecipeViewController controller = loader.getController();
                controller.initData(item, username);
        	});
        	
        	HBox buttonBox = new HBox(10);
        	buttonBox.setPrefWidth(400);
        	buttonBox.setPadding(new Insets(10, 0, 0, 0));
        	buttonBox.setAlignment(Pos.BOTTOM_RIGHT);
        	buttonBox.getChildren().addAll(viewButton);
        	
        	VBox infoVBox = new VBox();
        	infoVBox.setAlignment(Pos.CENTER_LEFT);
        	infoVBox.getChildren().addAll(recipeNameLabel, authorHBox, amountHBox, buttonBox);
        	
        	HBox recipeHBox = new HBox(10);
        	recipeHBox.setId("recipe-box");
        	recipeHBox.setAlignment(Pos.CENTER_LEFT);
        	recipeHBox.setPadding(new Insets(10, 10, 10, 10));
        	recipeHBox.getChildren().addAll(photo, infoVBox);
        	
        	setGraphic(recipeHBox);
        }
	}

}
