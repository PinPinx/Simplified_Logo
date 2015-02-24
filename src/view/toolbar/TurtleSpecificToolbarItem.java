package view.toolbar;

import java.util.ArrayList;
import java.util.List;

import view.InputDialogBox;

public abstract class TurtleSpecificToolbarItem extends ToolbarItem {
	
	protected static final String PROMPT = "Please enter turtle ID(s) separated by spaces,\n"
											+ "or 'all' for all turtles.\n"
											+ "Available IDs: ";
			
	protected TurtleSpecificToolbarItem(String label, Toolbar tb) {
		super(label, tb);
	}

	protected void getAndModifyTurtles() {
		
		String userInput = (getUserInput()).trim();
		List<Integer> turtleIDs = new ArrayList<Integer>();
		
		
		//TODO: input checking
		if (userInput.equals("all")) {
			turtleIDs.addAll(myToolbar.getTurtleWindow().getAvailableTurtles());
		}
		else {
			String[] IDs = userInput.split(" ");
			for (String ID: IDs) {
				turtleIDs.add(Integer.parseInt(ID));
			}
		}		
		
		for (int turtleID : turtleIDs) {
			changeTurtleProperties(turtleID);
		}
	}
	
	
	
	private String getUserInput() {
		InputDialogBox dialog = new InputDialogBox(PROMPT + getAvailableTurtles());
		return dialog.showInputDialog();		
	}
	
	
	private String getAvailableTurtles() {
		List<Integer> turtleIDs = myToolbar.getTurtleWindow().getAvailableTurtles();
		
		StringBuilder IDstring = new StringBuilder();
		for(int i=0; i<turtleIDs.size(); i++) {
			IDstring.append((i==0? "":", ") + turtleIDs.get(i).toString());
		}
		
		return IDstring.toString();
	}
	
	
	public abstract void changeTurtleProperties(int turtleID);

}
