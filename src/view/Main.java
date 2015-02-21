package view;
import view.Button.*;
import view.Components.*;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application{
	
	   @Override
	   public void start(Stage stage) {
	       Group root = new Group();
	       Scene scene = new Scene(root, 500, 500);
	       
	       VariablesWindow window = new VariablesWindow();
	       variablesButton b1 = new variablesButton("var 1", 5.0);
	       variablesButton b2 = new variablesButton("var 2", 3.0);
	       window.addButton(b1);
	       window.addButton(b2);
	       root.getChildren().add(window);
	       

	       stage.setTitle("Test for view components");
	       stage.setScene(scene);
	       stage.show();
	   }
	 
	   public static void main(String[] args) {
	       launch(args);
	   }
	
	

}
