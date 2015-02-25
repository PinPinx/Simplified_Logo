package view.Components;

import java.util.List;

import model.CommandHistoryUpdate;
import javafx.scene.control.Button;


public class CommandHistoryWindow extends ListWindow implements CommandsObserver{


	public CommandHistoryWindow(int w, int h) {
		super(w, h, "Command History");
		//test
		for (int i=0; i<20; i++) {
			myList.getChildren().add(new Button(("yo")));
		}
	}

	@Override
	public void update(CommandHistoryUpdate chu) {
		// TODO Auto-generated method stub
		
	}




}
