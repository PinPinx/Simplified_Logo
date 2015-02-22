package view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;

public class TurtleContextMenu extends ContextMenu {

	private TurtleImage myTurtle;

	// TODO: labels temporarily hardcoded to English - will be drawn from
	// reference libraries
	// once locales are set up

	// items under File
	public static final String TURTLE_IMAGE = "Change turtle image";
	public static final String TOGGLE_ACTIVE = "Toggle Active/Inactive";
	public static final String PEN_COLOR = "Change pen color";
	public static final String PEN_WIDTH = "Change pen width";

	public TurtleContextMenu(TurtleImage turtle) {
		this.myTurtle = turtle;
		
		MenuItem penWidth = makeMenuItem(PEN_WIDTH, null);
		
		getItems().add(penWidth);
	}

	private MenuItem makeMenuItem(String label, EventHandler<ActionEvent> event) {
		MenuItem item = new MenuItem(label);
		item.setOnAction(event);
		return item;
	}
}
