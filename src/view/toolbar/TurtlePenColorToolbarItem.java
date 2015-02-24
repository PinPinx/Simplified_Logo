package view.toolbar;

import view.Components.TurtleWindow;
import javafx.scene.Node;
import javafx.scene.control.ColorPicker;

public class TurtlePenColorToolbarItem extends TurtleSpecificToolbarItem {

	protected TurtlePenColorToolbarItem(String label, TurtleWindow tw) {
		super(label, tw);
	}

	@Override
	public void changeTurtleProperties(int turtleID) {
		myTurtleWindow.changePenColor(((ColorPicker) myNode).getValue(), turtleID);
		
	}

	@Override
	protected Node createNode() {
		ColorPicker cp = new ColorPicker();
		cp.setStyle("-fx-color-label-visible: false ;");
		return cp;
	}

}
