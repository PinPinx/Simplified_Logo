package view.Buttons;

import javafx.scene.control.Button;

public abstract class customButton extends Button{

	Object savedDataType;
	
	public customButton(String l, Object o){
		savedDataType = o;
		this.setText(l);
	}

}
