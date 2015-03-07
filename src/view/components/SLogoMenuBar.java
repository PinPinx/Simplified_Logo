package view.components;

import java.io.IOException;

import view.View;
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

	private View myView;


	// items under File
	public static final String FILE = "File";
	public static final String NEW_WORKSPACE = "New workspace";
	public static final String LOAD_LIBRARY = "Load commands library";
	public static final String SAVE_LIBRARY = "Save commands library";

	// items under Preferences
	public static final String PREFERENCES = "Settings";
	public static final String LANGUAGE = "Change language";
	public static final String LOAD_WORKSPACE = "Load preferences";
	public static final String SAVE_WORKSPACE = "Save preferences";	

	// items under HELP
	public static final String HELP = "Help";
	public static final String OPEN_HELP = "Open Help";
	public static final String ABOUT = "About";

	// graphic options
	private static final Color BAR_COLOR = Color.BLACK;

	public SLogoMenuBar(View view) {

		super();
		myView = view;
		this.setBackground(new Background(new BackgroundFill(BAR_COLOR, null, null)));
		this.setHover(true);

		makeFileMenu();
		makePreferencesMenu();
		makeHelpMenu();

	}

	private MenuItem makeMenuItem(String label, EventHandler<ActionEvent> event) {
		MenuItem item = new MenuItem(label);
		item.setOnAction(event);
		return item;
	}

	private void makeFileMenu() {
		Menu file = new Menu(FILE);

		MenuItem create = makeMenuItem(NEW_WORKSPACE, e->{
			myView.addNewTab();
		});
		
		MenuItem load = makeMenuItem(LOAD_LIBRARY, e->{
			myView.loadLibrary();
		});

		MenuItem save = makeMenuItem(SAVE_LIBRARY, e->{
			myView.saveCurrentLibrary();
		});
		
		file.getItems().addAll(create, load, save);
		this.getMenus().add(file);
	}

	private void makePreferencesMenu() {
		Menu pref = new Menu(PREFERENCES);

		MenuItem language = makeMenuItem(LANGUAGE, event -> {
			myView.showAndChangeLanguage();
		});
		
		MenuItem load = makeMenuItem(LOAD_WORKSPACE, e->{
			WorkspaceLoader loader = new WorkspaceLoader();
	        try {
				loader.loadWorkspace();
				WorkspaceFile myWorkspace = loader.getWorkspace();
		        myView.getTurtleWindow().changeBackground(myWorkspace.getColor());
		        myView.setTabTitle(myWorkspace.getTitle());
			} catch (Exception e1) {
			}
		});

		MenuItem save = makeMenuItem(SAVE_WORKSPACE, e->{
			WorkspaceFile myWorkspace = new WorkspaceFile(myView.getTurtleWindow().getBackgroundColor(), myView.getTurtleWindow().getNumOfTurtles());
			try {
				WorkspaceLoader loader = new WorkspaceLoader();
				loader.saveWorkspace(myWorkspace, myView.getTabTitle());
			} catch (IOException exc) {
				System.out.println("Couldn't save Workspace");
			}
		});
		
		pref.getItems().addAll(language, load, save);
		this.getMenus().add(pref);
	}


	private void makeHelpMenu() {
		Menu help = new Menu(HELP);

		MenuItem openHelp = makeMenuItem(OPEN_HELP, event -> {
			myView.showHelp();
		});
		MenuItem about = makeMenuItem(ABOUT, null);

		help.getItems().addAll(openHelp, about);
		this.getMenus().add(help);
	}

}
