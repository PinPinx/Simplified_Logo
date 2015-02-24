package view.toolbar;

import java.util.ArrayList;
import java.util.List;

import view.Components.TurtleWindow;

public abstract class TurtleSpecificToolbarItem extends ToolbarItem {
	
	protected TurtleSpecificToolbarItem(String label, TurtleWindow tw) {
		super(label, tw);
		myNode.setOnMouseClicked(event -> {
			List<Integer >turtleIDs = getTurtleIDs();
			for (int turtleID: turtleIDs) {
				changeTurtleProperties(turtleID);
			}
		});
	}
	
	
	private List<Integer> getTurtleIDs() {
		List<Integer> turtleIDs = new ArrayList<Integer>();
		//TODO: pop up box prompting user input
		return new ArrayList<Integer>(0);
	}
	
	
	public abstract void changeTurtleProperties(int turtleID);

}
