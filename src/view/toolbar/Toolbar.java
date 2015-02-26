package view.toolbar;

import view.components.TurtleWindow;
import javafx.scene.Group;
import javafx.scene.layout.HBox;

/**
 * Toolbar for the SLogo Program. Creates and arranges items to manipulate
 * display options.
 * 
 * @author lien
 *
 */
public class Toolbar extends Group {

	private HBox myToolbar;
	private TurtleWindow myTurtleWindow; // bound TurtleWindow where changes are
											// effected

	// TODO: labels temporarily hardcoded to English - will be drawn from
	// reference libraries
	// once locales are set up

	// items under File
	public static final String TURTLE_IMAGE = "Turtle image";
	public static final String TOGGLE_ACTIVE = "Toggle active/inactive";
	public static final String PEN_COLOR = "Pen color";
	public static final String BACKGROUND_COLOR = "Background color";

	public Toolbar() {
		myToolbar = new HBox(2);
		this.getChildren().add(myToolbar);

		// create and add items to toolbar
		BackgroundColorToolbarItem bgcolor = new BackgroundColorToolbarItem(
				BACKGROUND_COLOR, this);
		TurtlePenColorToolbarItem pencolor = new TurtlePenColorToolbarItem(
				PEN_COLOR, this);
		TurtleImageToolbarItem turtleimg = new TurtleImageToolbarItem(
				TURTLE_IMAGE, this);

		myToolbar.getChildren().addAll(bgcolor, pencolor, turtleimg);

	}

	public void setTurtleWindow(TurtleWindow tw) {
		myTurtleWindow = tw;
	}

	public TurtleWindow getTurtleWindow() {
		return myTurtleWindow;
	}

}
