package view.toolbar;

import view.components.TurtleWindow;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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

	public static final String BACKGROUND_COLOR = "Background color";
	public static final String ANIMATION_SPEED = "Animation speed";
	public static final String ADD_TURTLE = "Add turtle";
	public static final String TURTLE_IMAGE = "Turtle image";
	public static final String PEN_COLOR = "Pen color";

	public Toolbar() {
		myToolbar = new HBox(2);
		this.getChildren().add(myToolbar);

		// create and add items to toolbar
		ToolbarItem bgcolor = new BackgroundColorToolbarItem(
				BACKGROUND_COLOR, this);
		ToolbarItem speedSlider = new SpeedSliderToolbarItem(
				ANIMATION_SPEED, this);
		ToolbarItem addTurtle = new AddTurtleToolbarItem(
				ADD_TURTLE, this);
		ToolbarItem pencolor = new TurtlePenColorToolbarItem(
				PEN_COLOR, this);
		ToolbarItem turtleimg = new TurtleImageToolbarItem(
				TURTLE_IMAGE, this);
		
		((SpeedSliderToolbarItem) speedSlider).addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> ov,
                    Number old_val, Number new_val) {
                        myTurtleWindow.updateAnimationSpeed((double) new_val);
                }
            });
		
		myToolbar.getChildren().addAll(bgcolor, speedSlider, addTurtle, pencolor, turtleimg);

	}

	public void setTurtleWindow(TurtleWindow tw) {
		myTurtleWindow = tw;
	}

	public TurtleWindow getTurtleWindow() {
		return myTurtleWindow;
	}

}
