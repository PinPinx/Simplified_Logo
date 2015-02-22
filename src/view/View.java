package view;

import view.Components.CommandPort;
import view.Components.SLogoMenuBar;
import view.Components.TurtleWindow;
import view.Components.VariablesWindow;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.ColorPicker;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class View {
	
	private Stage myStage;
	private Scene myScene;
	private BorderPane myBorderPane;
	
	private TurtleWindow myTurtleWindow;
	private CommandPort myCommandPort;
	private SLogoMenuBar myMenuBar;
	private VariablesWindow myVariablesWindow;
	private ColorPicker myColorPicker;
	
	private static final String TITLE = "SLOGO";
	private static final Color BACKGROUND_COLOR = Color.LIGHTGREY;
	
	
	public View(Stage stage) {
		
		myStage = stage;
		myStage.setTitle(TITLE);
		
		myBorderPane = new BorderPane();
		myBorderPane.setBackground(new Background(
				new BackgroundFill(BACKGROUND_COLOR, null, null)));
		myScene = new Scene(myBorderPane, Main.SIZE.width, Main.SIZE.height);
		myStage.setScene(myScene);
		
		
		addTop();
		addTurtleWindow();
		addCommandPortWindow();
		addVariablesWindow();
		
	}
	
	
	private void addTop() {
		VBox top = new VBox();
		myMenuBar = new SLogoMenuBar(this);
		initializeColorPicker();
		
		top.getChildren().addAll(myMenuBar, myColorPicker);
		myBorderPane.setTop(top);
		
	}
	
	
	private void addTurtleWindow() {
		//TODO: Dimensions hard coded for now
		myTurtleWindow = new TurtleWindow(500, 500);
		myBorderPane.setLeft(myTurtleWindow);
		BorderPane.setMargin(myTurtleWindow, new Insets(5));
	}
	
	
	private void addCommandPortWindow() {
		//TODO: Dimensions hard coded for now
		myCommandPort = new CommandPort(500, 50);
		myBorderPane.setBottom(myCommandPort);
		BorderPane.setMargin(myCommandPort, new Insets(5));
	}
	
	
	private void addVariablesWindow() {
	    myVariablesWindow = new VariablesWindow();
	    myBorderPane.setRight(myVariablesWindow);
	    BorderPane.setMargin(myCommandPort, new Insets(5));
	}
	
	
	private void initializeColorPicker() {
		myColorPicker = new ColorPicker();
		myColorPicker.setStyle("-fx-color-label-visible: false ;");
		myColorPicker.setOnAction((ActionEvent t) -> {
		      changeBackgroundColor(myColorPicker.getValue());
	    });
	}
	
	
	public void showView() {
		myStage.show();
	}
	
	
	public void changeBackgroundColor(Color c) {
		myTurtleWindow.changeBackground(c);
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
