package view.components;

import view.View;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class DebuggerPort extends ScrollPane{
	private Label currentCommand;
	private Button stepCommand;
	private Button exitDebug;
	private VBox vb;
	
	public DebuggerPort(){
		vb = new VBox(5);
		currentCommand = new Label();
		stepCommand = new Button("STEP");
		stepCommand.setOnAction(e->{
			stepDebug();
		});
		
		exitDebug = new Button("EXIT");
		exitDebug.setOnAction(e->{
			exitDebugMode();
		});
		
		vb.getChildren().addAll(stepCommand, currentCommand, exitDebug);
		this.setContent(vb);
	}
	
	private void exitDebugMode(){
		View.getInstance().removeDebuger();
	}
	
	private void stepDebug(){
		View.getInstance().runDebugger();
	}
	
	public void setCurrentCommand(String command){
		currentCommand.setText(command);
	}
}
