package view.toolbar;

import javafx.scene.Node;
import javafx.scene.control.ColorPicker;import javafx.scene.paint.Color;


public class TurtlePenColorToolbarItem extends TurtleSpecificToolbarItem {
	
	private Color color;
	
	protected TurtlePenColorToolbarItem(String label, Toolbar tb) {
		super(label, tb);
	}

	@Override
	public void changeTurtleProperties(int turtleID) {
		myToolbar.getTurtleWindow().changePenColor(color, turtleID);
		
	}

	@Override
	protected Node createNode() {
		ColorPicker cp = new ColorPicker();
		cp.setStyle("-fx-color-label-visible: false ;");
		cp.setOnAction(event -> {
			color = cp.getValue();
			getAndModifyTurtles();
		});
		return cp;
	}

}
