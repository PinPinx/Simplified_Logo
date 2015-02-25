package view.Components;

import model.CommandHistoryUpdate;

public class UserDefinedCommandsWindow extends ListWindow implements CommandsObserver{

	public UserDefinedCommandsWindow(int w, int h) {
		super(w, h, "User Defined Commands");
	}

	@Override
	public void update(CommandHistoryUpdate chu) {
		// TODO Auto-generated method stub

	}


}
