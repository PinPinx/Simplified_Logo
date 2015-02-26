package view.Components;

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

	// TODO: labels temporarily hardcoded to English - will be drawn from
	// reference libraries
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
	public static final String LANGUAGE = "Language";

	// items under HELP
	public static final String HELP = "Help";
	public static final String OPEN_HELP = "Open Help";
	public static final String ABOUT = "About";

	// graphic options
	private static final Color BAR_COLOR = Color.BLACK;

	public SLogoMenuBar(View view) {

		super();
		myView = view;
		this.setBackground(new Background(new BackgroundFill(BAR_COLOR, null,
				null)));
		this.setHover(true);

		makeFileMenu();
		makeEditMenu();
		makeSettingsMenu();
		makeHelpMenu();

	}

	private MenuItem makeMenuItem(String label, EventHandler<ActionEvent> event) {
		MenuItem item = new MenuItem(label);
		item.setOnAction(event);
		return item;
	}

	private void makeFileMenu() {
		Menu file = new Menu(FILE);

		// TODO: add action
		MenuItem save = makeMenuItem(SAVE_WORKSPACE, null);
		MenuItem load = makeMenuItem(LOAD_WORKSPACE, null);

		file.getItems().addAll(save, load);
		this.getMenus().add(file);
	}

	private void makeEditMenu() {
		Menu edit = new Menu(EDIT);

		// TODO: add action
		MenuItem undo = makeMenuItem(UNDO, null);
		MenuItem redo = makeMenuItem(REDO, null);

		edit.getItems().addAll(undo, redo);
		this.getMenus().add(edit);
	}

	private void makeSettingsMenu() {
		Menu settings = new Menu(SETTINGS);

		MenuItem language = makeMenuItem(LANGUAGE, event -> {
			myView.showLanguages();
		});

		settings.getItems().addAll(language);
		this.getMenus().add(settings);
	}

	private void makeHelpMenu() {
		Menu help = new Menu(HELP);

		MenuItem openHelp = makeMenuItem(OPEN_HELP, event -> {
			myView.showHelp();
		});
		MenuItem about = makeMenuItem(ABOUT, null); // TODO: add action

		help.getItems().addAll(openHelp, about);
		this.getMenus().add(help);
	}

}
