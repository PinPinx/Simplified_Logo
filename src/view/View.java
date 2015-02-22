package view;

import view.Components.SLogoMenuBar;
import javafx.scene.Scene;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class View {
	
	private Stage myStage;
	private Scene myScene;
	private BorderPane myBorderPane;
	
	private TurtleWindow myTurtleWindow;
	private CommandPort myCommandPort;
	private SLogoMenuBar myMenuBar;
	
	private static final String TITLE = "SLOGO";
	private static final Color BACKGROUND_COLOR = Color.DARKSLATEGREY;
	
	public View(Stage stage) {
		myStage = stage;
		myStage.setTitle(TITLE);
		myBorderPane = new BorderPane();
		myBorderPane.setBackground(new Background(
				new BackgroundFill(BACKGROUND_COLOR, null, null)));
		myScene = new Scene(myBorderPane, Main.SIZE.width, Main.SIZE.height);
		myBorderPane = new BorderPane();
		
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
