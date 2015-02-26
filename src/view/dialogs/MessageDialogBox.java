package view.dialogs;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;

public class MessageDialogBox extends DialogBox {
	
	// graphics properties
	private static final int WIDTH  = 500;
	private static final int HEIGHT = 200;
	private static final Insets TEXT_INSETS = new Insets(20, 0, 10, 0);
	
	public MessageDialogBox(String message) {
		super();
		addText(message);
		myPane.setPrefSize(WIDTH, HEIGHT);
	}

	
	private void addText(String message) {
		Text text = new Text(message);
		myPane.setTop(text);
		BorderPane.setAlignment(text, Pos.CENTER);
		BorderPane.setMargin(text, TEXT_INSETS);
	}
	
	
}
