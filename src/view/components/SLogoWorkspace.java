package view.components;

import view.Main;
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
	private CommandHistoryWindow myCommandHistWindow;
	private VBox myListWindows;
	private HBox myUDListWindows;
	
	private static final Color BACKGROUND_COLOR = Color.LIGHTGREY;
	
	public SLogoWorkspace() {
		this("Untitled Workspace");
	}
	
	public SLogoWorkspace(String title) {
		
		super(title);
		
		myBorderPane = new BorderPane();
		myBorderPane.setBackground(new Background(new BackgroundFill(
				BACKGROUND_COLOR, null, null)));
		
		addTurtleWindow();
		addToolbar();
		addListWindows();
		addCommandPortWindow();
		
		this.setContent(myBorderPane);
			
	}


	private void addToolbar() {
		myToolbar = new Toolbar();
		myToolbar.setTurtleWindow(myTurtleWindow);	
		myBorderPane.setTop(myToolbar);
	}

	private void addTurtleWindow() {
		myTurtleWindow = new TurtleWindow(Main.SIZE.height * 4 / 5,
				Main.SIZE.height * 4 / 5);
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
		myCommandHistWindow = new CommandHistoryWindow(Main.SIZE.width * 2 / 5,
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

}
