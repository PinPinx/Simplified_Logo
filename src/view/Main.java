package view;
import java.awt.Dimension;

import javafx.application.Application;
import javafx.stage.Stage;


public class Main extends Application{
	public static final Dimension SIZE = new Dimension(1000, 700);
	
	
	@Override
	public void start (Stage stage) {
		View myView = new View(stage);
		myView.showView();
	}
	
	
	public static void main (String[] args) {
		launch(args);
	}

	

}
