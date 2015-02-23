package view.Components;

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
	public static final String TURTLE_IMAGE = "Change turtle image";
	public static final String TOGGLE_ACTIVE = "Toggle active/inactive";
	public static final String PEN_COLOR = "Change pen color";
	public static final String PEN_WIDTH = "Change pen width";
	public static final String BACKGROUND_COLOR = "Change background color";

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

	// TODO: add Label
	private void addBackgroundColorPicker() {

		ColorPicker bgColorPicker = new ColorPicker();
		bgColorPicker.setStyle("-fx-color-label-visible: false ;");
		bgColorPicker.setOnAction(event -> {
			myTurtleWindow.changeBackground(bgColorPicker.getValue());
		});

		myToolbar.getChildren().add(bgColorPicker);
	}

	// TODO:
	private void addPenColorPicker() {

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
