package view;

import view.Components.CommandHistoryWindow;
import view.Components.CommandPort;
import view.Components.LanguageController;
import view.Components.SLogoMenuBar;
import view.Components.TurtleWindow;
import view.Components.UserDefinedCommandsWindow;
import view.Components.VariablesWindow;
import view.dialogs.DialogBox;
import view.dialogs.HelpDialogBox;
import view.dialogs.LanguagesDialogBox;
import view.dialogs.MessageDialogBox;
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

/**
 * Singleton class responsible for setting up the GUI, and handling user
 * interactions.
 * 
 * @author lien
 *
 */
public class View {
	private static View instance;

	private Stage myStage;
	private Scene myScene;
	private BorderPane myBorderPane;
	private LanguageController myLanguageController = LanguageController
			.getInstance();

	private SLogoMenuBar myMenuBar;
	private Toolbar myToolbar;

	private TurtleWindow myTurtleWindow;
	private CommandPort myCommandPort;

	private UserDefinedCommandsWindow myUDCommandsWindow;
	private VariablesWindow myVariablesWindow;
	private CommandHistoryWindow myCommandHistWindow;
	private VBox myListWindows;
	private HBox myUDListWindows;

	private HelpDialogBox myHelpBox;
	private LanguagesDialogBox myLanguagesBox;

	private static final String TITLE = "SLOGO";
	private static final Color BACKGROUND_COLOR = Color.LIGHTGREY;

	public View(Stage stage) {

		// set up the main border pane and stage
		myBorderPane = new BorderPane();
		myBorderPane.setBackground(new Background(new BackgroundFill(
				BACKGROUND_COLOR, null, null)));
		myScene = new Scene(myBorderPane, Main.SIZE.width, Main.SIZE.height);

		myStage = stage;
		myStage.setTitle(TITLE);
		myStage.setScene(myScene);

		// place window items in their place
		addTopBars();
		addTurtleWindow();
		addListWindows();
		addCommandPortWindow();
		generateProgramDialogs();

		myToolbar.setTurtleWindow(myTurtleWindow);

		instance = this;

	}

	private void addTurtleWindow() {
		// TODO: Dimensions hard coded for now
		myTurtleWindow = new TurtleWindow(Main.SIZE.height * 4 / 5,
				Main.SIZE.height * 4 / 5);
		myBorderPane.setLeft(myTurtleWindow);
		BorderPane.setMargin(myTurtleWindow, new Insets(5));
	}

	/**
	 * Constructs a menu bar and a tool bar to be placed at the top
	 */

	private void addTopBars() {
		VBox top = new VBox();
		myMenuBar = new SLogoMenuBar(this);
		myToolbar = new Toolbar();
		top.getChildren().addAll(myMenuBar, myToolbar);
		myBorderPane.setTop(top);
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

	private void generateProgramDialogs() {
		myHelpBox = new HelpDialogBox();
		myLanguagesBox = new LanguagesDialogBox(myLanguageController);

	}

	public void showHelp() {
		myHelpBox.show();
	}

	public String showLanguages() {
		return (String) myLanguagesBox.showInputDialog();
	}

	public void showDialog(String message) {
		DialogBox dialog = new MessageDialogBox(message);
		dialog.show();
	}

	public void showView() {
		myStage.show();
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

	public UserDefinedCommandsWindow getUDCommandsWindow() {
		return myUDCommandsWindow;
	}

	public static View getInstance() {
		if (instance == null) {
			throw new RuntimeException("View accessed before instantiation");
		}
		return instance;
	}
}
