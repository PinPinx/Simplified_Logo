package view.components;

import java.util.List;

import exceptions.BadArgumentException;
import exceptions.CommandNameNotFoundException;
import exceptions.SyntaxErrorWrongFormat;
import view.View;
import view.dialogs.TextInputDialogBox;
import javafx.scene.control.Button;
import model.CommandHistoryUpdate;
import model.Model;

public class UserDefinedCommandsWindow extends ListWindow implements
		CommandsObserver {
	
	private static final String UD_COMMANDS = "User Defined Commands";
	private static final String PARAM_PROMPT = "Add parameter(s) to complete the command:";
	private TextInputDialogBox myParamPromptBox;
			
	public UserDefinedCommandsWindow(int w, int h) {
		super(w, h, UD_COMMANDS);
		myParamPromptBox = new TextInputDialogBox(PARAM_PROMPT);
	}

	// TODO: refactor. lots of duplicate codes with CommandHistWindow
	@Override
	public void update(CommandHistoryUpdate chu) {
		myList.getChildren().clear();
		
		List<String> myHistory = chu.getUDCommand();
		for (String command: myHistory) {
			Button commandButton = new Button(command);
			commandButton.setOnAction(e -> {
				myParamPromptBox.setText(command);
				String userInput = (String) myParamPromptBox.showInputDialog();
				parseCommand(userInput);
			});
			commandButton.setPrefWidth(this.width);
			myList.getChildren().add(commandButton);
		}
		
		

	}
	
	public void parseCommand(String command) {
		try {
			Model.getInstance().parse(command);
		} catch (CommandNameNotFoundException | SyntaxErrorWrongFormat | BadArgumentException e) {
			// TODO DUPLICATED CODE
			View.getInstance().showDialog(e.getMessage());
		}
	}

}
