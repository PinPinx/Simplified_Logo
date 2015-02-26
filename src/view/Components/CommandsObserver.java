package view.Components;

import model.CommandHistoryUpdate;

public interface CommandsObserver {

	public void update(CommandHistoryUpdate chu);

}
