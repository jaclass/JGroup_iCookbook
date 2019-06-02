package view.customized;

import entity.Recipe;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class RecipeListCell extends ListCell<Recipe>{

	@Override
	public void updateItem(Recipe item, boolean empty) {
		super.updateItem(item, empty);
		
		setText(null);
		setGraphic(null);
		
        if (item != null && !empty) {
        	ImageView photo = new ImageView();
        	photo.setImage(new Image("/image/demo.jpg"));
        	photo.setFitHeight(110);
        	photo.setFitWidth(170);
        	
        	Label recipeNameLabel = new Label();
        	Label authorLabel = new Label();
        	Label serveNumTitle = new Label();
        	Label serveNumLabel = new Label();
        	Label preparationTimeTitle = new Label();
        	Label preparationTimeLabel = new Label();
        	Label cookingTimeTitle = new Label();
        	Label cookingTimeLabel = new Label();
        	
        	recipeNameLabel.setFont(new Font(30));
        	
        	recipeNameLabel.setText(item.getRecipeName());
        	authorLabel.setText(item.getAuthor());
        	serveNumTitle.setText("Serve Number: ");
        	serveNumLabel.setText(String.valueOf(item.getServeNum()));
        	preparationTimeTitle.setText(" | Preparation Time: ");
        	preparationTimeLabel.setText(String.valueOf(item.getPreparationTime()));
        	cookingTimeTitle.setText(" | CookingTime: ");
        	cookingTimeLabel.setText(String.valueOf(item.getCookingTime()));
        	
        	HBox amountHBox = new HBox();
        	amountHBox.getChildren().addAll(serveNumTitle, serveNumLabel, preparationTimeTitle, preparationTimeLabel, cookingTimeTitle, cookingTimeLabel);
        	
        	VBox infoVBox = new VBox(5);
        	infoVBox.setAlignment(Pos.CENTER_LEFT);
        	infoVBox.getChildren().addAll(recipeNameLabel, authorLabel, amountHBox);
        	
        	HBox recipeHBox = new HBox(10);
        	recipeHBox.setAlignment(Pos.CENTER_LEFT);
        	recipeHBox.setPadding(new Insets(5, 10, 5, 10));
        	recipeHBox.getChildren().addAll(photo, infoVBox);
        	
        	setGraphic(recipeHBox);
        }
	}

}
