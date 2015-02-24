package view.toolbar;

import java.util.ArrayList;
import java.util.List;

import view.InputDialogBox;


/**
 * Class for toolbar items whose action(s) affect specific turtle, or turtle group.
 * 
 * @author lien
 *
 */
public abstract class TurtleSpecificToolbarItem extends ToolbarItem {
	
	List<Integer> selectedTurtles;
	
	//TODO: Hard coded to English
	protected static final String PROMPT = "Please enter turtle ID(s) separated by spaces,\n"
											+ "or 'all' for all turtles.\n"
											+ "Available IDs: ";
			
	protected TurtleSpecificToolbarItem(String label, Toolbar tb) {
		super(label, tb);
	}

	
	/**
	 * Get list of turtles to modify and apply the change.
	 */
	protected void getAndModifyTurtles() {
		
		getUserInput();
		
		for (int turtleID : selectedTurtles) {
			changeTurtleProperties(turtleID);
		}
	}
	
	
	/**
	 * Ask for user input through a dialog box.
	 * Parse user input and update the list of selected turtles.
	 * 
	 */
	private void getUserInput() {
		selectedTurtles = new ArrayList<Integer>();
		InputDialogBox dialog = new InputDialogBox(PROMPT + getAvailableTurtles());
		String userInput = (dialog.showInputDialog()).trim();
		
		//TODO: input checking
		if (userInput.equals("all")) {
			selectedTurtles.addAll(myToolbar.getTurtleWindow().getAvailableTurtles());
		}
		else {
			String[] IDs = userInput.split(" ");
			for (String ID: IDs) {
				selectedTurtles.add(Integer.parseInt(ID));
			}
		}	
	}
	
	
	/**
	 * Gets an (unmodifiable) list of currently available turtles
	 * and arrange it in a String to be displayed in the prompt.
	 * 
	 * @return String listing currently available turltes.
	 */
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
