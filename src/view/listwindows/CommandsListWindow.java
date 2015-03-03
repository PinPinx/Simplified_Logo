package view.listwindows;

import java.util.List;

import view.View;
import view.components.CommandsObserver;
import exceptions.BadArgumentException;
import exceptions.CommandNameNotFoundException;
import exceptions.SyntaxErrorWrongFormat;
import javafx.scene.control.Button;
import model.CommandHistoryUpdate;
import model.Model;

public abstract class CommandsListWindow extends ListWindow implements CommandsObserver {

	public CommandsListWindow(int w, int h, String label) {
		super(w, h, label);
	}

	@Override
	public void update(CommandHistoryUpdate chu) {
		myList.getChildren().clear();
		
		List<String> myHistory = getCommandsList(chu);
		
		for (int i = myHistory.size() - 1; i >= 0; i--) {
			String command = myHistory.get(i);
			Button commandButton = new Button(command);
			setButtonAction(commandButton);
			commandButton.setPrefWidth(this.width);
			myList.getChildren().add(commandButton);
		}
		
		
	}
	
	public void parseCommand(String command) {
		try {
			Model.getInstance().parse(command);
		} catch (CommandNameNotFoundException | SyntaxErrorWrongFormat
				| BadArgumentException e) {
			View.getInstance().showDialog(e.getMessage());
		}
	}
	
	protected abstract List<String> getCommandsList(CommandHistoryUpdate chu);
	
	protected abstract void setButtonAction(Button commandButton);
	
}
