package view.Components;


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
	// once Locales are set up
	
	// items under File
	public static final String FILE = "File";
	public static final String SAVE_WORKSPACE = "Save workspace";
	public static final String LOAD_WORKSPACE = "Load workspace";
	
	// items under Edit
	public static final String EDIT = "Edit";
	public static final String UNDO = "Undo";
	public static final String REDO = "Redo";
	
	// items under Settings
	public static final String SETTINGS = "Settings";
	
	public static final String HELP = "Help";
	
	
	// graphic options
	private static final Color BAR_COLOR = Color.BLACK;
	
	
	
	public SLogoMenuBar() {
		
		super();
		
		this.setBackground(new Background(
				new BackgroundFill(BAR_COLOR, null, null)));
		this.setHover(true);
		
		makeFileMenu();
		makeEditMenu();
		makeSettingsMenu();
		//addMenuList(HELP, makeHelpMenu());
		
	}
	
	
	private MenuItem makeMenuItem(String label, EventHandler<ActionEvent> event) {
		MenuItem item = new MenuItem(label);
		item.setOnAction(event);
		return item;
	}
	
	
	private void makeFileMenu() {
		Menu file = new Menu(FILE);
		
		//TODO: add action
		MenuItem save = makeMenuItem(SAVE_WORKSPACE, null);
		MenuItem load = makeMenuItem(LOAD_WORKSPACE, null);
		
		file.getItems().addAll(save, load);
		this.getMenus().add(file);
	}
	
	
	private void makeEditMenu() {
		Menu edit = new Menu(EDIT);
		
		//TODO: add action
		MenuItem undo = makeMenuItem(UNDO, null);
		MenuItem redo = makeMenuItem(REDO, null);
		
		edit.getItems().addAll(undo, redo);
		this.getMenus().add(edit);
	}
	
	private void makeSettingsMenu() {
		Menu settings = new Menu(SETTINGS);
	
		
		//settings.getItems().addAll(save, load);
		this.getMenus().add(settings);
	}



}
