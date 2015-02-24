package view.toolbar;

import javafx.scene.Node;
import javafx.scene.control.ColorPicker;

public class BackgroundColorToolbarItem extends ToolbarItem {

	protected BackgroundColorToolbarItem(String label, Toolbar tb) {
		super(label, tb);
	}

	@Override
	protected Node createNode() {
		
		ColorPicker bgColorPicker = new ColorPicker();
		bgColorPicker.setStyle("-fx-color-label-visible: false ;");
		bgColorPicker.setOnAction(event -> {
			myToolbar.getTurtleWindow().changeBackground(bgColorPicker.getValue());
		});
		
		return bgColorPicker;
	}

}
