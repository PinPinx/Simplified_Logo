package view.Components;

import java.util.List;

import exceptions.CommandNameNotFoundException;
import exceptions.SyntaxErrorWrongFormat;
import model.CommandHistoryUpdate;
import model.Model;
import javafx.scene.control.Button;

public class CommandHistoryWindow extends ListWindow implements
		CommandsObserver {
	
	private static final String COMMAND_HISTORY = "Command History";
	
	public CommandHistoryWindow(int w, int h) {
		super(w, h, COMMAND_HISTORY);
	}

	public void update(CommandHistoryUpdate chu) {
		myList.getChildren().clear();
		List<String> myHistory = chu.getCommandHistory();
		for (int i = myHistory.size() - 1; i >= 0; i--) {
			String command = myHistory.get(i);
			Button commandButton = new Button(command);
			commandButton.setOnAction(e -> parseCommand(command));
			commandButton.setPrefWidth(this.width);
			myList.getChildren().add(commandButton);
		}
	}

	public void parseCommand(String command) {
		try {
			Model.getInstance().parse(command);
		} catch (CommandNameNotFoundException | SyntaxErrorWrongFormat e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
