package view.dialogs;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
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

    private static final String OKAY = "OK";

    public DialogBox () {
        myPane = new BorderPane();
        myPane.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.ENTER) {
                myStage.close();
            }
        });
        addOKButton();
        initStage();
    }

    public DialogBox (String title) {
        this();
        this.setTitle(title);
    }

    public void addItem (Node node) {
        myPane.setCenter(node);
    }

    /**
     * Initialize the stage to display the dialog.
     */
    private void initStage () {

        myStage = new Stage();
        myStage.initModality(Modality.APPLICATION_MODAL);
        myScene = new Scene(myPane);
        myStage.setScene(myScene);
    }

    protected void setTitle (String title) {
        myTitle = title;
        myStage.setTitle(myTitle);
    }

    private void addOKButton () {
        Button okButton = new Button(OKAY);
        okButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle (ActionEvent event) {
                myStage.close();
            }
        });
        myPane.setBottom(okButton);
        BorderPane.setAlignment(okButton, Pos.CENTER);
    }

    public void show () {
        myStage.show();
    }

}
