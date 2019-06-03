package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;

/**
 * App entry point.
 * 
 * @author JGroup
 *
 */
public class Main extends Application {
	
	/**
	 * Show the first stage.
	 */
	@Override
	public void start(Stage primaryStage) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("view/fxml/LoginView.fxml"));
			Parent root = loader.load();
	        
	        Scene scene = new Scene(root);
	        primaryStage.setScene(scene);
	        
			primaryStage.setTitle("iCookbook --- JGroup");
			primaryStage.getIcons().add(new Image("/image/app_icon.png"));
			primaryStage.setResizable(false);
	        primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Main function to launch the first stage.
	 * 
	 * @param args Command line arguments.
	 */
	public static void main(String[] args) {
		launch(args);
	}
	
}
