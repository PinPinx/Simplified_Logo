package view;

import java.util.Arrays;

import view.Components.CommandHistoryWindow;
import view.Components.CommandPort;
import view.Components.SLogoMenuBar;
import view.Components.TurtleWindow;
import view.Components.UserDefinedCommandsWindow;
import view.Components.VariablesWindow;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.ColorPicker;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class View {
	private static View instance;
	
	private Stage myStage;
	private Scene myScene;
	private BorderPane myBorderPane;
	
	private TurtleWindow myTurtleWindow;
	private VBox myListWindows;
	private HBox myUDListWindows;
	private UserDefinedCommandsWindow myUDCommandsWindow;
	private VariablesWindow myVariablesWindow;
	private CommandHistoryWindow myCommandHistWindow;
	
	private CommandPort myCommandPort;
	private SLogoMenuBar myMenuBar;
	private ColorPicker myColorPicker;
	
	private static final String TITLE = "SLOGO";
	private static final Color BACKGROUND_COLOR = Color.LIGHTGREY;
	
	
	public View(Stage stage) {
		
		myStage = stage;
		myStage.setTitle(TITLE);
		
		myBorderPane = new BorderPane();
		myBorderPane.setBackground(new Background(new BackgroundFill(BACKGROUND_COLOR, null, null)));
		myScene = new Scene(myBorderPane, Main.SIZE.width, Main.SIZE.height);
		myStage.setScene(myScene);
		
		addTop();
		addListWindows();
		addTurtleWindow();
		addCommandPortWindow();
		
		instance = this;
	}
	
	
	public static View getInstance(){
		if(instance == null){
			throw new RuntimeException("View accessed before instantiation");
		}
		return instance;
	}
	
	
	private void addTop() {
		VBox top = new VBox();
		myMenuBar = new SLogoMenuBar(this);
		initializeColorPicker();
		top.getChildren().addAll(myMenuBar, myColorPicker);
		myBorderPane.setTop(top);
		
	}
	
	
	private void addListWindows() {
		initLists();
		
		myUDListWindows = new HBox();
		myUDListWindows.getChildren().addAll(myVariablesWindow, myUDCommandsWindow);
		
		myListWindows = new VBox();
		myListWindows.getChildren().addAll(myUDListWindows, myCommandHistWindow);
		myBorderPane.setCenter(myListWindows);
		
	}
	
	
	private void initLists() {
		myCommandHistWindow = new CommandHistoryWindow(Main.SIZE.width * 2/5, Main.SIZE.height * 2/5);
		myUDCommandsWindow = new UserDefinedCommandsWindow(Main.SIZE.width * 1/5, Main.SIZE.height * 2/5);
		myVariablesWindow = new VariablesWindow(Main.SIZE.width * 1/5, Main.SIZE.height * 2/5);		
	}
	
	
	
	private void addTurtleWindow() {
		//TODO: Dimensions hard coded for now
		myTurtleWindow = new TurtleWindow(Main.SIZE.height * 4/5, Main.SIZE.height * 4/5);
		myBorderPane.setLeft(myTurtleWindow);
		BorderPane.setMargin(myTurtleWindow, new Insets(5));
	}
	
	
	private void addCommandPortWindow() {
		//TODO: Dimensions hard coded for now
		myCommandPort = new CommandPort(500, 50);
		myBorderPane.setBottom(myCommandPort);
		BorderPane.setMargin(myCommandPort, new Insets(5));
	}
	
	private void initializeColorPicker() {
		myColorPicker = new ColorPicker();
		myColorPicker.setStyle("-fx-color-label-visible: false ;");
		myColorPicker.setOnAction((ActionEvent t) -> {
		      changeBackgroundColor(myColorPicker.getValue());
	    });
	}
	
	
	private void changeBackgroundColor(Color c) {
		myTurtleWindow.changeBackground(c);
	}
	
	/*
	private void showMessage(String message) {
		MessageBox messageBox = new MessageBox(message);
		
	}
	*/
	
	public void showView() {
		myStage.show();
	}
	
	
	public Stage getStage() {
		return myStage;
	}

}
