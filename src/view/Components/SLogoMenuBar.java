package view.Components;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;


/**
 * MenuBar for the SLogo program.
 * 
 * @author lien
 *
 */
public class SLogoMenuBar extends MenuBar {
	
	// TODO: labels temporarily hardcoded to English - will be drawn from reference libraries 
	// once locales are set up
	
	// items under File
	public static final String FILE = "File";
	public static final String SAVE_WORKSPACE = "Save workspace";
	public static final String LOAD_WORKSPACE = "Load workspace";
	
	// items under Preferences
	public static final String PREFERENCES = "Preferences";
	public static final String LANGUAGE = "Language";
	
	// items under Edit
	public static final String EDIT = "Edit";
	public static final String UNDO = "Undo";
	public static final String REDO = "Redo";
	
	public static final String HELP = "Help";
	
	
	// graphic options
	private static final Color BAR_COLOR = Color.GREY;
	
	
	
	public SLogoMenuBar() {
		
		super();
		
		this.setBackground(new Background(
				new BackgroundFill(BAR_COLOR, null, null)));

		addMenuList(FILE, makeFileMenu());
		addMenuList(PREFERENCES, makePreferencesMenu());
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
		
		//MenuItem myLanguage 	 = makeMenuItem(LANGUAGE, event -> );
		//preferences.addAll(Arrays.asList(myTurtleImage));
		return preferences;
	}
	
	
	private List<MenuItem> makeFileMenu() {
		ArrayList<MenuItem> file = new ArrayList<MenuItem>();
		
		//TODO
		MenuItem mySave = makeMenuItem(SAVE_WORKSPACE, null);
		MenuItem myLoad = makeMenuItem(LOAD_WORKSPACE, null);
		
		file.addAll(Arrays.asList(mySave, myLoad));
		return file;
	}
	
	
	private List<MenuItem> makeEditMenu() {
		ArrayList<MenuItem> edit = new ArrayList<MenuItem>();
		
		//TODO
		MenuItem myUndo = makeMenuItem(UNDO, null);
		MenuItem myRedo = makeMenuItem(REDO, null);
		
		edit.addAll(Arrays.asList(myUndo, myRedo));
		return edit;
	}

}
