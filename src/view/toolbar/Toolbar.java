package view.toolbar;

import view.Components.TurtleWindow;
import javafx.scene.Group;
import javafx.scene.control.ColorPicker;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

public class Toolbar extends Group {

	private HBox myToolbar;
	private TurtleWindow myTurtleWindow;

	// TODO: labels temporarily hardcoded to English - will be drawn from
	// reference libraries
	// once locales are set up

	// items under File
	public static final String TURTLE_IMAGE = "Turtle image";
	public static final String TOGGLE_ACTIVE = "Toggle active/inactive";
	public static final String PEN_COLOR = "Pen color";
	public static final String BACKGROUND_COLOR = "Background color";

	public Toolbar() {
		myToolbar = new HBox();
		this.getChildren().add(myToolbar);
		
		addBackgroundColorPicker();
		addTurtleImageSelector();
		addPenColorPicker();
		addPenWidthEditor();

	}
	
	
	public void setTurtleWindow(TurtleWindow tw) {
		myTurtleWindow = tw;
	}

	
	private void addBackgroundColorPicker() {
		BackgroundColorToolbarItem bgcolor = new BackgroundColorToolbarItem(BACKGROUND_COLOR, myTurtleWindow);
		myToolbar.getChildren().add(bgcolor);
	}
	

	
	private void addPenColorPicker() {
		TurtlePenColorToolbarItem pencolor = new TurtlePenColorToolbarItem(PEN_COLOR, myTurtleWindow);
		myToolbar.getChildren().add(pencolor);
	}

	// TODO:
	private void addPenWidthEditor() {

	}

	private void addTurtleImageSelector() {
		// TODO Auto-generated method stub

	}

	private FileChooser makeImageChooser() {
		FileChooser imageChooser = new FileChooser();
		imageChooser.setTitle("Choose Image File");
		imageChooser.getExtensionFilters().addAll(
				new ExtensionFilter("JPG Images", "*.jpg"),
				new ExtensionFilter("PNG Images", "*.png"));
		return imageChooser;
	}

}
