package view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
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
	protected BorderPane myPane;
	protected String myTitle;


	public DialogBox() {
		myPane = new BorderPane();
		addOKButton();
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
	

	protected void setTitle(String title) {
		myTitle = title;
		myStage.setTitle(myTitle);
	}
	
	private void addOKButton() {
		Button okButton = new Button("OK");
		okButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				myStage.close();
			}
		});
		myPane.setBottom(okButton);
		BorderPane.setAlignment(okButton, Pos.CENTER);
	}
	
	public void show() {
		myStage.show();
	}

}
