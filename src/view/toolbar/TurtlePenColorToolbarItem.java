package view.toolbar;

import javafx.scene.Node;
import javafx.scene.control.ColorPicker;

public class TurtlePenColorToolbarItem extends TurtleSpecificToolbarItem {

	protected TurtlePenColorToolbarItem(String label, Toolbar tb) {
		super(label, tb);
	}

	@Override
	public void changeTurtleProperties(int turtleID) {
		myToolbar.getTurtleWindow().changePenColor(((ColorPicker) myNode).getValue(), turtleID);
		
	}

	@Override
	protected Node createNode() {
		ColorPicker cp = new ColorPicker();
		cp.setStyle("-fx-color-label-visible: false ;");
		return cp;
	}

}
