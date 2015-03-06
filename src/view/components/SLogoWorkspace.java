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
	
	private UserDefinedCommandsWindow myUDCommandsWindow;
	private VariablesWindow myVariablesWindow;
	private CommandsHistoryWindow myCommandHistWindow;
	private VBox myListWindows;
	private HBox myUDListWindows;
	
	private String myTitle;
	
	private int myID;
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
		// TODO: Dimensions hard coded for now
		myCommandPort = new CommandPort(500, 50);
		myBorderPane.setBottom(myCommandPort);
		BorderPane.setMargin(myCommandPort, new Insets(5));
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
