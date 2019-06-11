package controller.view;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;

import controller.customized.IngredientBoxController;
import controller.db.DBController;
import entity.Ingredient;
import entity.Recipe;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
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
	
	public static Recipe selectedRecipe;
	private String username;
	
	private FileChooser imageChooser;
	private File imagePath;
	
	@FXML private Label authorLabel;
	@FXML private Label recipeNameLabel;
	@FXML private Label serveNumLabel;
	@FXML private Label preparationTimeLabel;
	@FXML private Label cookingTimeLabel;
	@FXML private VBox stepBox;
	@FXML private VBox ingredientBox;
	@FXML private ImageView imageView;
	
	/**
	 * Initiate the view.
	 * 
	 * @param item Recipe.
	 * @param username Username.
	 */
	public void initData(Recipe item, String username) {
		this.username = username;
		selectedRecipe = item;
    	
    	if (selectedRecipe.getImage() == null) {
    		imageView.setImage(new Image("/image/demo.jpg"));
    	} else {
    		imageView.setImage(selectedRecipe.getImage());
    	}
    	authorLabel.setText(selectedRecipe.getAuthor());
    	recipeNameLabel.setText(selectedRecipe.getRecipeName());
    	serveNumLabel.setText(String.valueOf(selectedRecipe.getServeNum()));
    	preparationTimeLabel.setText(String.valueOf(selectedRecipe.getPreparationTime()) + "");
    	cookingTimeLabel.setText(String.valueOf(selectedRecipe.getCookingTime()) + "");
    	(new PreparationStepBox(item.getPreparationSteps(), stepBox, item.getRecipeId())).generate();
    	(new IngredientBox(item.getIngredients(), ingredientBox, item.getRecipeId())).generate();
	}
	
	/**
	 * This method will allow the user to choose the image.
	 * 
	 * @param event
	 */
	public void chooseImageButtonPushed(ActionEvent event) {
		// This line gets the Stage information.
		Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
		
		imageChooser = new FileChooser();
		imageChooser.setTitle("Choose Image");
		
		// Set to user's directory or go to the default C drive if cannot access.
		String userDirectoryString = System.getProperty("user.home") + "\\Pictures";
		File userDirectory = new File(userDirectoryString);
		if (!userDirectory.canRead()) {
			userDirectory = new File("c:/");
		}
		imageChooser.setInitialDirectory(userDirectory);
		
		// Set filter.
		imageChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg"));
		
		this.imagePath = imageChooser.showOpenDialog(window);
		
		// Try to update the image by loading the new image.
		try {
			if (imagePath == null) {
				return;
			} else {
				BufferedImage bufferedImage = ImageIO.read(imagePath);
				Image image = SwingFXUtils.toFXImage(bufferedImage, null);
				// Insert the new image to the database.
				DBController.updateImage(selectedRecipe.getRecipeId(), image);
				imageView.setImage(image);
				return;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * When the button pushed, update the recipe name.
	 * 
	 * @param event
	 * @throws IOException
	 */
	public void updateName(ActionEvent event) throws IOException {
		String result = (new SetBox("Edit", "Edit the recipe name:", this.recipeNameLabel.getText())).display();
		if(result == null) {
			return;
		}
		if(result.trim().length() == 0) {
			AlertBox.display("Edit Failure", "The recipe name cannot be empty!");
			return;
		}
		DBController.updateRecipeName(selectedRecipe.getRecipeId(), result);
		this.recipeNameLabel.setText(result);
	}
	
	/**
	 * When the button clicked, update serve number.
	 * 
	 * @param event
	 * @throws IOException
	 */
	public void updateSerNum(ActionEvent event) throws IOException {
		String result = (new SetBox("Edit", "Edit the serve number:", this.serveNumLabel.getText())).display();
		if(result == null) {
			return;
		}
		if(result.trim().length() == 0) {
			AlertBox.display("Edit Failure", "The serve number cannot be empty!");
			return;
		}
		int ret;
		try {
			 ret = Integer.parseInt(result.trim());
		}catch(NumberFormatException exception) {
			AlertBox.display("Number Error", "You can just input the positive integer!");
			return;
		}
		if(ret <= 0) {
			AlertBox.display("Number Error", "You can just input the positive integer!");
			return ;
		}
		DBController.updateServeNum(selectedRecipe.getRecipeId(), ret);
		this.serveNumLabel.setText(result);
	}
	
	/**
	 * When the button clicked, update preparation time.
	 * 
	 * @param event
	 * @throws IOException
	 */
	public void updatePreparationTime(ActionEvent event) throws IOException {
		String result = (new SetBox("Edit", "Edit the preparation time:", this.preparationTimeLabel.getText())).display();
		if(result == null) {
			return;
		}
		if(result.trim().length() == 0) {
			AlertBox.display("Edit Failure", "The preparation time cannot be empty!");
			return;
		}
		int ret;
		try {
			 ret = Integer.parseInt(result.trim());
		}catch(NumberFormatException exception) {
			AlertBox.display("Number Error", "You can just input the positive integer!");
			return;
		}
		if(ret <= 0) {
			AlertBox.display("Number Error", "You can just input the positive integer!");
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
		String result = (new SetBox("Edit", "Edit the cooking time:", this.cookingTimeLabel.getText())).display();
		if(result == null) {
			return;
		}
		if(result.trim().length() == 0) {
			AlertBox.display("Edit Failure", "The preparation time cannot be empty!");
			return;
		}
		int ret;
		try {
			 ret = Integer.parseInt(result.trim());
		}catch(NumberFormatException exception) {
			AlertBox.display("Number Error", "You can just input the positive integer!");
			return;
		}
		if(ret <= 0) {
			AlertBox.display("Number Error", "You can just input the positive integer!");
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
		List<Ingredient> ings = IngredientBoxController.add(selectedRecipe.getRecipeId(), selectedRecipe.getIngredients(), ingredientBox);
		if(ings != null) {
			selectedRecipe.setIngredients(ings);
		}
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
