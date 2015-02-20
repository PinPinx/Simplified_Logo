package view;
import view.Button.*;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Button;

public class Main extends Application{
	
	   @Override
	   public void start(Stage stage) {
	       Group root = new Group();
	       Scene scene = new Scene(root, 500, 500);
	       
	       
	       variablesButton b = new variablesButton("testButton", 6.0);
	       //Button b = new Button("hi");
	       root.getChildren().add(b);
	       

	       stage.setTitle("Test for view components");
	       stage.setScene(scene);
	       stage.show();
	   }
	 
	   public static void main(String[] args) {
	       launch(args);
	   }
	
	

}
