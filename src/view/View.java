package view;

import view.Components.SLogoMenuBar;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class View {
	
	private Stage myStage;
	private Scene myScene;
	private BorderPane myPane;
	
	private TurtleWindow myTurtleWindow;
	private CommandPort myCommandPort;
	private SLogoMenuBar myMenuBar;
	
	private static final String TITLE = "SLOGO";
	
	public View(Stage stage) {
		myStage = stage;
		myStage.setTitle(TITLE);
		myPane = new BorderPane();
		
	}
	
	
	/*
	public void getTurtleIDs() {
		
	}
	*/
	
	public void changeBackgroundColor() {
		
	}
	
	/*
	private void showMessage(String message) {
		MessageBox messageBox = new MessageBox(message);
		
	}
	*/
	
	
	public Stage getStage() {
		return myStage;
	}

}
