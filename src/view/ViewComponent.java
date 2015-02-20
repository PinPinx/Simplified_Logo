package view;

import java.util.Observer;

public abstract interface ViewComponent extends Observer{
	
	abstract void UIEvent();

}
