package view.customized;

import java.io.IOException;

import controller.db.DBController;
import controller.view.CreateViewController;
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
import view.pop.ConfirmBox;

/**
 * Cell to place the recipe list.
 * 
 * @author JGroup
 *
 */
public class RecipeModifyListCell extends ListCell<Recipe>{
	
	private String username;
	
	/**
	 * Constructor to receive the username.
	 * 
	 * @param username Username.
	 */
	public RecipeModifyListCell(String username) {
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
        	HBox authorBox = new HBox();
        	authorBox.getChildren().addAll(authorTitle, authorLabel);
        	authorBox.setId("recipe-infomation-box");
        	
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
        	
        	Button editButton = new Button("Edit");
        	editButton.setPrefWidth(60);
        	Button deleteButton = new Button("Delete");
        	deleteButton.setPrefWidth(60);
        	
        	HBox buttonBox = new HBox(10);
        	buttonBox.setPrefWidth(460);
        	buttonBox.setPadding(new Insets(10, 0, 0, 0));
        	buttonBox.setAlignment(Pos.BOTTOM_RIGHT);
        	buttonBox.getChildren().addAll(editButton, deleteButton);
        	
        	editButton.setOnAction(e -> {
            	FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("view/fxml/CreateView.fxml"));
        		Parent createViewParent = null;
				try {
					createViewParent = loader.load();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
        		
                Scene createViewScene = new Scene(createViewParent);
                
                // This line gets the Stage information.
                Stage window = (Stage)((Node)e.getSource()).getScene().getWindow();
                
                window.setScene(createViewScene);
                window.show();
                
        		// Access the controller and call a method.
                CreateViewController controller = loader.getController();
                controller.initData(item,username);
        	});
        	deleteButton.setOnAction(e -> {
            	Boolean answer = ConfirmBox.display("Delete Recipe", "Are you sure to delete the recipe?");
            	
            	if (answer) {
            		getListView().getItems().remove(getItem());
            		DBController.deleteRecipe(item);
        		}
        	});
        	
        	VBox infoVBox = new VBox(5);
        	infoVBox.setAlignment(Pos.CENTER_LEFT);
        	infoVBox.getChildren().addAll(recipeNameLabel, authorBox, amountHBox, buttonBox);
        	
        	HBox recipeHBox = new HBox(10);
        	recipeHBox.setId("recipe-box");
        	recipeHBox.setAlignment(Pos.CENTER_LEFT);
        	recipeHBox.setPadding(new Insets(10, 10, 10, 10));
        	recipeHBox.getChildren().addAll(photo, infoVBox);
        	
        	setGraphic(recipeHBox);
        }
	}

}
