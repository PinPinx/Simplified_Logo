package view.Components;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import view.View;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;


/**
 * MenuBar for the SLogo program.
 * 
 * @author lien
 *
 */
public class SLogoMenuBar extends MenuBar {
	
	// TODO: temporarily hardcoded to English - will be drawn from reference libraries 
	// once locales are set up
	
	private View myView;
	
	// items under File
	public static final String FILE = "File";
	public static final String SAVE_WORKSPACE = "Save workspace";
	public static final String LOAD_WORKSPACe = "Load workspace";
	
	// items under Preferences
	public static final String PREFERENCES = "Preferences";
	public static final String BACKGROUND = "Background Color";
	public static final String TURTLE_IMAGE = "Turtle Image";
	public static final String PEN_COLOR = "Pen Color";
	public static final String LANGUAGE = "Language";
	
	// items under Edit
	public static final String EDIT = "Edit";
	public static final String UNDO = "Undo";
	public static final String REDO = "Redo";
	
	public static final String HELP = "Help";
	
	public SLogoMenuBar(View view) {
		
		super();
		myView = view;
		
		addMenuList(PREFERENCES, makePreferencesMenu());
		addMenuList(FILE, makeFileMenu());
		addMenuList(EDIT, makeEditMenu());
		//addMenuList(HELP, makeHelpMenu());
	}
	
	
	private MenuItem makeMenuItem(String label, EventHandler<ActionEvent> event) {
		MenuItem item = new MenuItem(label);
		item.setOnAction(event);
		return item;
	}
	
	
	private void addMenuList(String label, List<MenuItem> itemsList) {
		Menu menuList = new Menu(label);
		menuList.getItems().addAll(itemsList);
		getMenus().add(menuList);
	}
	
	
	private List<MenuItem> makePreferencesMenu() {
		ArrayList<MenuItem> preferences = new ArrayList<MenuItem>();
		
		FileChooser turtleImageChooser = makeImageChooser();
		MenuItem myTurtleImage = makeMenuItem(TURTLE_IMAGE, event -> turtleImageChooser.showOpenDialog(myView.getStage()));
		//TODO: How to pass the image file to turtle?
		
		MenuItem myBackground  = makeMenuItem(BACKGROUND, event -> myView.changeBackgroundColor());
		//MenuItem myPenColor	 = makeMenuItem(PEN_COLOR, event -> );
		//MenuItem myLanguage 	 = makeMenuItem(LANGUAGE, event -> );
		preferences.addAll(Arrays.asList(myBackground, myTurtleImage));
		return new ArrayList<MenuItem>();
	}
	
	
	private FileChooser makeImageChooser() {
		FileChooser imageChooser = new FileChooser();
        imageChooser.setTitle("Choose Image File");
        imageChooser.getExtensionFilters().addAll(
        		new ExtensionFilter("JPG Images", "*.jpg"),
        		new ExtensionFilter("PNG Images", "*.png"));
        return imageChooser;
	}
	
	
	private List<MenuItem> makeFileMenu() {
		ArrayList<MenuItem> file = new ArrayList<MenuItem>();
		//MenuItem mySave = makeMenuItem(SAVE_WORKSPACE, event -> );
		//MenuItem myLoad = makeMenuItem(LOAD_WORKSPACE, event -> );
		//file.addAll(mySave, myLoad);
		return file;
	}
	
	
	private List<MenuItem> makeEditMenu() {
		ArrayList<MenuItem> edit = new ArrayList<MenuItem>();
		//MenuItem myUndo = makeMenuItem(UNDO, event -> );
		//MenuItem myRedo = makeMenuItem(REDO, event -> );
		//file.addAll(myUndo, myRedo);
		return edit;
	}
	
	
}
