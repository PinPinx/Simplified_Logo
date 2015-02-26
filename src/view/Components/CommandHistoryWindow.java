package view.Components;

import java.util.List;

import exceptions.CommandNameNotFoundException;
import exceptions.SyntaxErrorWrongFormat;
import model.CommandHistoryUpdate;
import model.Model;
import javafx.scene.control.Button;



public class CommandHistoryWindow extends ListWindow implements CommandsObserver{

	public CommandHistoryWindow(int w, int h) {
		super(w, h, "Command History.");
		// test

	}


	public void update(CommandHistoryUpdate chu) {
		// TODO Auto-generated method stub
		myList.getChildren().clear();
		List<String> myHistory = chu.getCommandHistory();
		System.out.println("udpate requested");
		for (String command : myHistory){
			Button commandButton = new Button(command);
			commandButton.setOnAction(e->parseCommand(command));
			myList.getChildren().add(commandButton);
		}
	}
	
	public void parseCommand(String command){
		try {
			Model.getInstance().parse(command);
		} catch (CommandNameNotFoundException | SyntaxErrorWrongFormat e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



}
