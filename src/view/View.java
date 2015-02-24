package view;

import java.util.ArrayList;
import java.util.List;

import model.Variable;
import model.VariableInt;
import view.Components.CommandHistoryWindow;
import view.Components.CommandPort;
import view.Components.SLogoMenuBar;
import view.Components.TurtleWindow;
import view.Components.UserDefinedCommandsWindow;
import view.Components.VariablesWindow;
import view.toolbar.Toolbar;
import javafx.geometry.Insets;
import javafx.scene.Scene;
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
	private Toolbar myToolbar;
	private VBox myListWindows;
	private HBox myUDListWindows;
	private UserDefinedCommandsWindow myUDCommandsWindow;
	private VariablesWindow myVariablesWindow;
	private CommandHistoryWindow myCommandHistWindow;

	private CommandPort myCommandPort;
	private SLogoMenuBar myMenuBar;

	
	private static final String TITLE = "SLOGO";
	private static final Color BACKGROUND_COLOR = Color.LIGHTGREY;


	public View(Stage stage) {
		
		// set up the main border pane and stage	
		myBorderPane = new BorderPane();
		myBorderPane.setBackground(new Background(new BackgroundFill(BACKGROUND_COLOR, null, null)));
		myScene = new Scene(myBorderPane, Main.SIZE.width, Main.SIZE.height);
		
		myStage = stage;
		myStage.setTitle(TITLE);
		myStage.setScene(myScene);
		
		// place window items in their place
		addListWindows();
		addTopBars();
		addTurtleWindow();
		addCommandPortWindow();
		
		myToolbar.setTurtleWindow(myTurtleWindow);
		
		instance = this;
		
	}


	
	private void addTurtleWindow() {
		//TODO: Dimensions hard coded for now
		myTurtleWindow = new TurtleWindow(Main.SIZE.height * 4/5, Main.SIZE.height * 3/5);
		myBorderPane.setLeft(myTurtleWindow);
		BorderPane.setMargin(myTurtleWindow, new Insets(5));
	}
	
	
	
	/**
	 * Constructs a menu bar and a tool bar to be placed at the top
	 */
	
	private void addTopBars() {
		VBox top = new VBox();
		myMenuBar = new SLogoMenuBar();
		myToolbar = new Toolbar();
		top.getChildren().addAll(myMenuBar, myToolbar);
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
	
		
	private void addCommandPortWindow() {
		//TODO: Dimensions hard coded for now
		myCommandPort = new CommandPort(500, 50);
		myBorderPane.setBottom(myCommandPort);
		BorderPane.setMargin(myCommandPort, new Insets(5));
	}

	
	
	public void showDialog(String message) {
		DialogBox dialog = new DialogBox(message);
		dialog.show();
	}
	 

	public void showView() {
		myStage.show();
	}


	public Stage getStage() {
		return myStage;
	}
	
	public TurtleWindow getTurtleWindow() {
		return myTurtleWindow;
	}
	
	
	public VariablesWindow getVariablesWindow() {
		return myVariablesWindow;
	}
	
	
	public CommandHistoryWindow getCommandHistoryWindow() {
		return myCommandHistWindow;
	}
	
	
	public static View getInstance(){
		if(instance == null){
			throw new RuntimeException("View accessed before instantiation");
		}
		return instance;
	}
}
