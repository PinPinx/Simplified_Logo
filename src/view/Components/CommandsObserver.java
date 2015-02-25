package view.Components;

import java.util.List;

import model.CommandHistoryUpdate;

public interface CommandsObserver {
	
	public void update(CommandHistoryUpdate chu);

}
