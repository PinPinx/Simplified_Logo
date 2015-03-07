package view.components;

import view.Main;
import view.listwindows.CommandsHistoryWindow;
import view.listwindows.UserDefinedCommandsWindow;
import view.listwindows.VariablesWindow;
import view.toolbar.Toolbar;
import javafx.geometry.Insets;
import javafx.scene.control.Tab;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class SLogoWorkspace extends Tab {
	
	private BorderPane myBorderPane;
	
	private Toolbar myToolbar;
	private TurtleWindow myTurtleWindow;
	private CommandPort myCommandPort;
	private DebuggerPort myDebugPort;
	
	private UserDefinedCommandsWindow myUDCommandsWindow;
	private VariablesWindow myVariablesWindow;
	private CommandsHistoryWindow myCommandHistWindow;
	private VBox myListWindows;
	private HBox myUDListWindows;
	
	private String myTitle;
	private String myLanguage;
	
	private int myID;
	
	private static final String DEFAULT_LANGUAGE = "English";
	private static final Color BACKGROUND_COLOR = Color.LIGHTGREY;
	
	public SLogoWorkspace(int id) {
		this("Untitled Workspace " + Integer.toString(id), id);
		myTitle = new String("Untitled Workspace " + Integer.toString(id));
	}
	
	public SLogoWorkspace(String title, int id) {
		
		super(title);
		
		myBorderPane = new BorderPane();
		myBorderPane.setBackground(new Background(new BackgroundFill(
				BACKGROUND_COLOR, null, null)));
		
		addTurtleWindow();
		addToolbar();
		addListWindows();
		addCommandPortWindow();
		
		myID = id;
		myLanguage = DEFAULT_LANGUAGE;
		
		this.setContent(myBorderPane);
			
	}


	private void addToolbar() {
		myToolbar = new Toolbar();
		myToolbar.setTurtleWindow(myTurtleWindow);	
		myBorderPane.setTop(myToolbar);
	}

	private void addTurtleWindow() {
		myTurtleWindow = new TurtleWindow(Main.SIZE.height * 4 / 5,
				Main.SIZE.height * 3 / 5 );
		myBorderPane.setLeft(myTurtleWindow);
		BorderPane.setMargin(myTurtleWindow, new Insets(5));
	}
	

	private void addListWindows() {
		initLists();

		myUDListWindows = new HBox();
		myUDListWindows.getChildren().addAll(myVariablesWindow,
				myUDCommandsWindow);

		myListWindows = new VBox();
		myListWindows.getChildren()
				.addAll(myUDListWindows, myCommandHistWindow);
		myBorderPane.setCenter(myListWindows);

	}

	private void initLists() {
		myCommandHistWindow = new CommandsHistoryWindow(Main.SIZE.width * 2 / 5,
				Main.SIZE.height * 2 / 5);
		myUDCommandsWindow = new UserDefinedCommandsWindow(
				Main.SIZE.width * 1 / 5, Main.SIZE.height * 2 / 5);
		myVariablesWindow = new VariablesWindow(Main.SIZE.width * 1 / 5,
				Main.SIZE.height * 2 / 5);
	}

	private void addCommandPortWindow() {
		myCommandPort = new CommandPort(500, 50);
		myBorderPane.setBottom(myCommandPort);
		BorderPane.setMargin(myCommandPort, new Insets(5));
	}
	
	public void startDebugger(){
		myDebugPort = new DebuggerPort();
		myBorderPane.setCenter(myDebugPort);
		myTurtleWindow.enterDebugMode();
	}
	
	public void stopDebugger(){
		myBorderPane.getChildren().remove(myDebugPort);
		myBorderPane.setCenter(myListWindows);
		myTurtleWindow.exitDebugMode();
	}
	
	public void runDebugger(){
		myTurtleWindow.stepDebug();
	}
	
	public void setTitle(String title){
		myTitle = title;
		this.setText(myTitle);
	}
	
	public String getTitle(){
		return myTitle;
	}
	
	public int getWorkspaceID() {
		return myID;
	}
	
	public void setLanguage(String language) {
		myLanguage = language;
	}
	
	public String getLanguage() {
		return myLanguage;
	}
	
	public TurtleWindow getTurtleWindow() {
		return myTurtleWindow;
	}

	public VariablesWindow getVariablesWindow() {
		return myVariablesWindow;
	}

	public CommandsHistoryWindow getCommandHistoryWindow() {
		return myCommandHistWindow;
	}

	public UserDefinedCommandsWindow getUDCommandsWindow() {
		return myUDCommandsWindow;
	}
}
