package view.Components;

import java.util.List;

import javafx.scene.control.Button;
import model.CommandHistoryUpdate;

public class UserDefinedCommandsWindow extends ListWindow implements
		CommandsObserver {

	public UserDefinedCommandsWindow(int w, int h) {
		super(w, h, "User Defined Commands");
	}

	@Override
	public void update(CommandHistoryUpdate chu) {
		// TODO Auto-generated method stub
		myList.getChildren().clear();
		List<String> myUDlist = chu.getUDCommand();
		if (myUDlist != null) {
			for (String command : myUDlist) {
				Button commandButton = new Button(command);
				// commandButton.setOnAction(e->parseCommand(command));
				myList.getChildren().add(commandButton);
			}
		}

	}

}
