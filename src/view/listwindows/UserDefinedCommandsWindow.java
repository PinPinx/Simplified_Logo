// This entire file is part of my masterpiece.
// Lien Hoang

package view.listwindows;

import java.util.List;

import view.dialogs.TextInputDialogBox;
import javafx.scene.control.Button;
import model.CommandHistoryUpdate;

public class UserDefinedCommandsWindow extends CommandsListWindow {
	
	private static final String UD_COMMANDS = "User Defined Commands";
	private static final String PARAM_PROMPT = "Add parameter(s) to complete the command:";
	private TextInputDialogBox myParamPromptBox;
			
	public UserDefinedCommandsWindow(int w, int h) {
		super(w, h, UD_COMMANDS);
		myParamPromptBox = new TextInputDialogBox(PARAM_PROMPT);
	}


	@Override
	protected List<String> getCommandsList(CommandHistoryUpdate chu) {
		
		return chu.getUDCommand();
	}

	@Override
	protected void setButtonAction(Button commandButton) {
		commandButton.setOnAction(e -> {
			myParamPromptBox.setText(commandButton.getText());
			String userInput = (String) myParamPromptBox.showInputDialog();
			parseCommand(userInput);
		});
		
	}

}
