package view;

import java.awt.geom.Point2D;
import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import exceptions.BadArgumentException;
import exceptions.CommandNameNotFoundException;
import exceptions.SyntaxErrorWrongFormat;
import model.Model;
import parser.parser.Regex;
import view.components.SLogoMenuBar;
import view.components.SLogoWorkspace;
import view.components.TurtleWindow;
import view.dialogs.DialogBox;
import view.dialogs.HelpDialogBox;
import view.dialogs.InputDialogBox;
import view.dialogs.LanguagesDialogBox;
import view.dialogs.MessageDialogBox;
import view.dialogs.TextInputDialogBox;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import view.listwindows.CommandsHistoryWindow;
import view.listwindows.UserDefinedCommandsWindow;
import view.listwindows.VariablesWindow;
import javafx.scene.Scene;
import javafx.scene.control.TabPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
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

	private SLogoMenuBar myMenuBar;
	private TabPane myTabPane;
	
	private int myActiveTab;	//index of the currently active tab
	private int numTabs;
	
	private HelpDialogBox myHelpBox;
	private LanguagesDialogBox myLanguagesBox;

	private static final String TITLE = "SLOGO";
	private static final Color BACKGROUND_COLOR = Color.LIGHTGREY;

	public View(Stage stage) {
		
		instance = this;
		
		// set up the main border pane and stage
		myBorderPane = new BorderPane();
		myBorderPane.setBackground(new Background(new BackgroundFill(
				BACKGROUND_COLOR, null, null)));
		myScene = new Scene(myBorderPane, Main.SIZE.width, Main.SIZE.height);

		myStage = stage;
		myStage.setTitle(TITLE);
		myStage.setScene(myScene);
		
		numTabs = 0;
		
		// set up top bars and create dialogs (help, change language)
		addTopBars();
		generateProgramDialogs();
		
		// add a default tab
		addNewTab();
	}

	
	/**
	 * Constructs a menu bar and a tabpane to be placed at the top
	 */
	private void addTopBars() {
		VBox top = new VBox();
		
		myMenuBar = new SLogoMenuBar(this);
		myTabPane = new TabPane();
		myTabPane.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
		    @Override
		    public void changed(ObservableValue<? extends Number> ov, Number oldValue, Number newValue) {
		        myActiveTab = (int) newValue;
		        tabChangeUpdate();
		    }
		}); 
		
		top.getChildren().addAll(myMenuBar, myTabPane);
		myBorderPane.setTop(top);
	}
	
	private void generateProgramDialogs() {
		myHelpBox = new HelpDialogBox();
		myLanguagesBox = new LanguagesDialogBox();
	}

	public void showHelp() {
		myHelpBox.show();
	}

	public void showAndChangeLanguage() {
		String newLanguage = (String) myLanguagesBox.showInputDialog();
		((SLogoWorkspace) myTabPane.getTabs().get(myActiveTab)).setLanguage(newLanguage);
		Regex.getInstance().changeLanguage(newLanguage);
	}

	public void showDialog(String message) {
		DialogBox dialog = new MessageDialogBox(message);
		dialog.show();
	}
	
	public void addNewTab() {
		SLogoWorkspace newTab = new SLogoWorkspace(numTabs);
		newTab.setOnClosed(closed->{
			tabChangeUpdate();
		});
		
		myTabPane.getTabs().add(newTab);
		numTabs++;
	}
	
	private void tabChangeUpdate() {
		Regex.getInstance().changeLanguage(((SLogoWorkspace) myTabPane.getTabs().get(myActiveTab)).getLanguage());
        Model.getInstance().setState(((SLogoWorkspace) myTabPane.getTabs().get(myActiveTab)).getWorkspaceID());
	}
	
	
	public void showView() {
		myStage.show();
	}
		
	public void saveCurrentLibrary() {
		
		String fullPath = promptFileToSave();
		
		BufferedWriter writer = null;
		try
		{
		    writer = new BufferedWriter( new FileWriter(fullPath));
		    writer.write(Model.getInstance().saveLibrary(((SLogoWorkspace) myTabPane.getTabs().get(myActiveTab)).getWorkspaceID()));
		}
		catch ( IOException e)
		{
		}
		
		finally
		{
		    try
		    {
		        if ( writer != null)
		        writer.close();
		    }
		    catch ( IOException e)
		    {
		    }
		}
        
		showDialog("UD commands saved to " + fullPath);
	}
	
	public void loadLibrary() {
		FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select library file to load");
        File selectedFile = fileChooser.showOpenDialog(myStage);
        
        byte[] buffer = new byte[(int) selectedFile.length()];
        BufferedInputStream f = null;
        try {
            f = new BufferedInputStream(new FileInputStream(selectedFile.getAbsolutePath()));
            f.read(buffer);
            if (f != null) try { f.close(); } catch (IOException ignored) { }
        } catch (IOException ignored) { System.out.println("File not found or invalid path.");}
        
        String read = new String(buffer);
        try {
			Model.getInstance().parse(read);
		} catch (CommandNameNotFoundException | SyntaxErrorWrongFormat
				| BadArgumentException e) {
		}
	}
	
	private String promptFileToSave() {
		DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("Choose directory to save library");
		File selectedDirectory = directoryChooser.showDialog(myStage);
		
		InputDialogBox fileNamePrompt = new TextInputDialogBox("Enter file name to save:");
		String fileName = (String) fileNamePrompt.showInputDialog();
		
		String fullPath = selectedDirectory.getAbsolutePath() + "/" + fileName + ".txt";
		return fullPath;
	}
	
	
	
	public void addDebuger(){
		SLogoWorkspace myActivePane = (SLogoWorkspace) (myTabPane.getTabs().get(myActiveTab));
		myActivePane.startDebugger();
	}
	
	public void removeDebuger(){
		SLogoWorkspace myActivePane = (SLogoWorkspace) (myTabPane.getTabs().get(myActiveTab));
		myActivePane.stopDebugger();
	}
	
	public void runDebugger(){
		SLogoWorkspace myActivePane = (SLogoWorkspace) (myTabPane.getTabs().get(myActiveTab));
		myActivePane.runDebugger();
	}
	
	public TurtleWindow getTurtleWindow() {
		return ((SLogoWorkspace) myTabPane.getTabs().get(myActiveTab)).getTurtleWindow();
	}

	public VariablesWindow getVariablesWindow() {
		return ((SLogoWorkspace) myTabPane.getTabs().get(myActiveTab)).getVariablesWindow();
	}

	public CommandsHistoryWindow getCommandHistoryWindow() {
		return ((SLogoWorkspace) myTabPane.getTabs().get(myActiveTab)).getCommandHistoryWindow();
	}

	public UserDefinedCommandsWindow getUDCommandsWindow() {
		return ((SLogoWorkspace) myTabPane.getTabs().get(myActiveTab)).getUDCommandsWindow();
	}
	
	public String getTabTitle(){
		return ((SLogoWorkspace) myTabPane.getTabs().get(myActiveTab)).getTitle();
	}
	
	public void setTabTitle(String title){
		SLogoWorkspace myActivePane = (SLogoWorkspace) myTabPane.getTabs().get(myActiveTab);
		myActivePane.setTitle(title);
	}
	
	public static View getInstance() {
		if (instance == null) {
			throw new RuntimeException("View accessed before instantiation");
		}
		return instance;
	}
}
