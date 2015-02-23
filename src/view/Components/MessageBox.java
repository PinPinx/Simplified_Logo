package view.Components;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MessageBox {
	
    private Stage myStage;
    private String myMessage;
    private VBox myMessageBox;
    private Text myMessageText;
    private Button myOkayButton;
	
    public MessageBox(String message) {
    	myMessage = message;
    }
    
    public void show() {
    	
    }
    
    private void createOkayButton () {
        myOkayButton = new Button("OK");
        myOkayButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle (ActionEvent event) {
                myStage.close();
            }
        });
    }
}
