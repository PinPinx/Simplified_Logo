package view;
import java.util.ArrayList;
import java.util.List;

import model.*;
import view.Components.*;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application{
	
	   @Override
	   public void start(Stage stage) {
	       Group root = new Group();
	       Scene scene = new Scene(root, 1000, 700);
	       ScrollPane sp = new ScrollPane();
	       HBox content2 = new HBox();
	       VBox content1 = new VBox();
	       
	       
	       
	       TurtleWindow tw = new TurtleWindow(500, 500);
	       CommandPort cp = new CommandPort(500, 50);
	       content1.getChildren().addAll(tw, cp);
	       
	       VariablesWindow vw = new VariablesWindow();
	       content2.getChildren().addAll(content1, vw);
	       content2.setSpacing(50);
	       
	       
	       sp.setContent(content2);
	       root.getChildren().add(sp);
	       
	       
	       tw.update(0, 0, 100, 100);
	       List<Variable> vList = new ArrayList<Variable>();
	      /* Variable v1 = new VariableInt("var1" , 2);
	       Variable v2 = new VariableInt("var2" , 1);
	       vList.add(v1);
	       vList.add(v2);*/
	       vw.update(vList);
	       

	       stage.setTitle("SLogo Test Window");
	       stage.setScene(scene);
	       stage.show();
	   }
	 
	   public static void main(String[] args) {
	       launch(args);
	   }
	
	

}
