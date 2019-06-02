package controller.view;

import java.io.IOException;

import entity.Recipe;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class RecipeListCellItemController {

	@FXML private HBox recipeHBox;
	@FXML private ImageView photo;
	@FXML private Label recipeNameLabel;
	@FXML private Label authorLabel;
	@FXML private Label serveNumLabel;
	@FXML private Label preparationTimeLabel;
	@FXML private Label cookingTimeLabel;
	
	public RecipeListCellItemController() {
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("view/fxml/RecipeListCellItem.fxml"));
        try {
        	loader.load();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	public void setInfo(Recipe recipe) {
		recipeNameLabel.setText(recipe.getRecipeName());
	}
	
	public HBox getBox() {
		return recipeHBox;
	}
	
}
