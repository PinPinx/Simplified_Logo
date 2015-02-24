package view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Pop-up box to display a message.
 * 
 * @author lien
 *
 */
public class DialogBox {

	protected Stage myStage;
	protected Scene myScene;
	private String myMessage;
	protected BorderPane myPane;
	protected Button myOKButton;

	public DialogBox(String message) {
		myMessage = message;
		myPane = new BorderPane();
		myPane.setPrefSize(500, 200);
		createDialogBox();
		initStage();
	}
	
	/**
	 * Initialize the stage to display the dialog.
	 */
	private void initStage() {
		
		myStage = new Stage();
		myStage.initModality(Modality.WINDOW_MODAL);

		myScene = new Scene(myPane);

		myStage.setScene(myScene);
	}
	
	public void show() {
		myStage.show();
	}

	
	protected void createDialogBox() {
		addText();
		addOKButton();
	}

	
	private void addText() {
		Text text = new Text(myMessage);
		myPane.setTop(text);
	}

	
	private void addOKButton() {
		myOKButton = new Button("OK");
		myOKButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				myStage.close();
			}
		});
		myPane.setBottom(myOKButton);

	}

}
