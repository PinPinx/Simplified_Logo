package view.Components;

import javafx.scene.control.Button;

public class CommandHistoryWindow extends ListWindow {

	public CommandHistoryWindow(int w, int h) {
		super(w, h, "Command History");
		// test
		for (int i = 0; i < 20; i++) {
			myList.getChildren().add(new Button(("yo")));
		}
	}

	@Override
	public void UIEvent() {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(Object updateObject) {
		// TODO Auto-generated method stub

	}

}
