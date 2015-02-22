package view;

import javax.swing.JButton;

public abstract class customButton extends JButton{

	Object savedDataType;
	String label;
	
	public customButton(String l, Object o){
		label = l;
		savedDataType = o;
	}

}
