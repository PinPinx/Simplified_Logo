package view.toolbar;

import view.Components.TurtleWindow;
import javafx.scene.Node;
import javafx.scene.control.ColorPicker;

public class BackgroundColorToolbarItem extends ToolbarItem {

	protected BackgroundColorToolbarItem(String label, TurtleWindow tw) {
		super(label, tw);
	}

	@Override
	protected Node createNode() {
		
		ColorPicker bgColorPicker = new ColorPicker();
		bgColorPicker.setStyle("-fx-color-label-visible: false ;");
		bgColorPicker.setOnAction(event -> {
			myTurtleWindow.changeBackground(bgColorPicker.getValue());
		});
		
		return bgColorPicker;
	}

}
