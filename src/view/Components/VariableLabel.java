package view.Components;

import Exceptions.CommandNameNotFoundException;
import Exceptions.SyntaxErrorWrongFormat;
import model.Model;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;

public class VariableLabel extends HBox{
	Label myVarLabel;
	Label myValueLabel;
	TextField myTextField;
	
	public VariableLabel(String myName, String myValue){
		myVarLabel = new Label(myName+" = ");
		myValueLabel = new Label(myValue);
		myValueLabel.setOnMouseClicked(e->takeEdit());
        this.getChildren().addAll(myVarLabel, myValueLabel);
	}
	
    public void takeEdit(){
    	myTextField = new TextField();
    	myTextField.setOnKeyPressed(e->processInput(e));
    	this.getChildren().remove(myValueLabel);
    	this.getChildren().add(myTextField);
    }
	
    public void processInput(KeyEvent e){
    	if (e.getCode()==KeyCode.ENTER){
    		//myValueLabel.setText(myTextField.getText());
    		//Re parsing the command for now, we can later use another API per backend updates. 
    		String command = "MAKE "+myVarLabel.getText()+myTextField.getText();
    		try {
				Model.getInstance().parse(command);
			} catch (CommandNameNotFoundException | SyntaxErrorWrongFormat e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
    		this.getChildren().remove(myTextField);
    		this.getChildren().add(myValueLabel);
    	}
    }
	
	

}
