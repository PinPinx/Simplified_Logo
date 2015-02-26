package view.Components;

import exceptions.CommandNameNotFoundException;
import exceptions.SyntaxErrorWrongFormat;
import model.Model;
import javafx.beans.property.StringProperty;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;

public class VariableLabel extends HBox{
	Label myVarLabel;
	StringProperty myName;
	Label myValueLabel;
	StringProperty myValue;
	TextField myTextField;
	
	public VariableLabel(StringProperty myName, StringProperty myValue){
		myVarLabel = new Label(myName.getValue()+" = ");
		myValueLabel = new Label(myValue.getValue());
		myValueLabel.setOnMouseClicked(e->takeEdit());
		this.myValue = myValue;
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
    		
    		String value = myTextField.getText();
    		myTextField.clear();
    		
    		myValue.setValue(value);
    		myValueLabel.setText(myValue.getValue());
    	
  
    		
    		this.getChildren().remove(myTextField);
    		this.getChildren().add(myValueLabel);
    		
    		
    	}
    }
	
	

}
