package view.Components;

import java.util.List;

public class UserDefinedCommandsWindow extends ListWindow implements CommandsObserver{

	public UserDefinedCommandsWindow(int w, int h) {
		super(w, h, "User Defined Commands");
	}

	@Override
	public void update(List<String> commandList) {
		// TODO Auto-generated method stub
		
	}


}
