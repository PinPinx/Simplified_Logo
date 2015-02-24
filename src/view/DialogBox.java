package view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
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
	
	// graphics properties
	private static final int width  = 500;
	private static final int height = 200;
	private static final Insets TEXT_INSETS = new Insets(20, 0, 10, 0);
	private static final Insets BUTTON_INSETS = new Insets(10, 0, 20, 0);

	public DialogBox(String message) {
		myMessage = message;
		myPane = new BorderPane();
		myPane.setPrefSize(width, height);
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
		for (Node node: myPane.getChildren()) {
			BorderPane.setAlignment(node,Pos.CENTER);
		}
	}

	
	private void addText() {
		Text text = new Text(myMessage);
		myPane.setTop(text);
		BorderPane.setMargin(text, TEXT_INSETS);
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
		BorderPane.setMargin(myOKButton, BUTTON_INSETS);
		

	}

}
