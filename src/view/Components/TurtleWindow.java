package view.Components;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import view.ViewComponent;

public class TurtleWindow extends Canvas implements ViewComponent{
	GraphicsContext gc;
	
	public TurtleWindow(double width, double height){
	 this.setWidth(width);
	 this.setHeight(height);
	 gc = this.getGraphicsContext2D();
	 gc.setFill(Color.GRAY);
	 gc.fillRect(0, 0, this.getWidth(), this.getHeight());
	}

	@Override
	public void update(Object updateObject) {
		// TODO Auto-generated method stub
	}
	
	public void update(double x1, double y1, double x2, double y2){
		gc.strokeLine(x1, y1, x2, y2);
	}

	@Override
	public void UIEvent() {
		// TODO Auto-generated method stub
		
	}
	
	

	


}
