package view.toolbar;

import javafx.scene.Node;
import javafx.scene.control.ColorPicker;

/**
 * Toolbar Item to change background color for the turtle window.
 * 
 * @author lien
 *
 */
public class BackgroundColorToolbarItem extends ToolbarItem {

	protected BackgroundColorToolbarItem(String label, Toolbar tb) {
		super(label, tb);
	}

	@Override
	protected Node createNode() {

		ColorPicker bgColorPicker = new ColorPicker();
		bgColorPicker.setStyle("-fx-color-label-visible: false ;");
		bgColorPicker.setOnAction(event -> {
			myToolbar.getTurtleWindow().changeBackground(
					bgColorPicker.getValue());
		});

		return bgColorPicker;
	}

}
