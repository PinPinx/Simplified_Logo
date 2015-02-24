package view.toolbar;

import java.util.ArrayList;
import java.util.List;

import view.InputDialogBox;

public abstract class TurtleSpecificToolbarItem extends ToolbarItem {

	protected TurtleSpecificToolbarItem(String label, Toolbar tb) {
		super(label, tb);
	}

	protected void getAndModifyTurtles() {
		List<Integer> turtleIDs = new ArrayList<Integer>();
		
		//TODO: input checking
		InputDialogBox dialog = new InputDialogBox("Please enter turtle ID");
		String userInput = dialog.showInputDialog();
		turtleIDs.add(Integer.parseInt(userInput));
		
		for (int turtleID : turtleIDs) {
			changeTurtleProperties(turtleID);
		}
	}

	public abstract void changeTurtleProperties(int turtleID);

}
