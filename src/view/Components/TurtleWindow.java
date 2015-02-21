package view.Components;

import java.util.HashMap;

import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import view.ViewComponent;

public class TurtleWindow extends Group implements ViewComponent{
	private Canvas mainCanvas;
	private GraphicsContext mainGC;
	private HashMap<Integer, GraphicsContext> gc = new HashMap<>();
	private HashMap<Integer, ImageView> myTurtles = new HashMap<>();
	
	public TurtleWindow(double width, double height){
	 mainCanvas = new Canvas(width, height);
	 mainGC = mainCanvas.getGraphicsContext2D();
	 
	 this.getChildren().addAll(mainCanvas);
	 
	 addTurtle();
	 gc.get(0).setStroke(Color.RED);
	 gc.get(0).strokeLine(100, 100, 300, 300);
	 myTurtles.get(0).setRotate(45);
	 
	 changeBackground();
	}

	@Override
	public void update(Object updateObject) {
		// TODO Auto-generated method stub
	}
	
	public void update(double x1, double y1, double x2, double y2){
		//gc.strokeLine(x1, y1, x2, y2);
	}
	
	public void addTurtle(){
		addTurtle(300,300);
	}
	
	public void addTurtle(double xPos, double yPos){
		ImageView turtle = new ImageView(new Image("images/duke.gif"));
		myTurtles.put(myTurtles.size(), turtle);
		Canvas layer = new Canvas(mainCanvas.getWidth(), mainCanvas.getWidth());
		GraphicsContext layerGC = layer.getGraphicsContext2D();
		gc.put(gc.size(), layerGC);
		this.getChildren().addAll(turtle, layer);
	}
	
	public void changeBackground(){
		mainGC.setFill(Color.GRAY);
		mainGC.fillRect(0, 0, mainCanvas.getWidth(), mainCanvas.getHeight());
	}

	@Override
	public void UIEvent() {
		// TODO Auto-generated method stub
		
	}
	
	

	


}
