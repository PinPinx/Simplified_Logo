// This entire file is part of my masterpiece.
// Lien Hoang

package view.listwindows;

import java.util.List;

import model.CommandHistoryUpdate;
import javafx.scene.control.Button;

public class CommandsHistoryWindow extends CommandsListWindow {

	private static final String COMMAND_HISTORY = "Command History";

	public CommandsHistoryWindow(int w, int h) {
		super(w, h, COMMAND_HISTORY);
	}

	@Override
	protected List<String> getCommandsList(CommandHistoryUpdate chu) {
		return chu.getCommandHistory();
	}

	@Override
	protected void setButtonAction(Button commandButton) {
		commandButton.setOnAction(e->{
			parseCommand(commandButton.getText());
		});
		
	}

}
