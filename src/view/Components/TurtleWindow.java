package view.Components;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import model.TurtleUpdate;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class TurtleWindow extends Group implements ViewComponent {

	private Canvas mainCanvas;
	private GraphicsContext mainGC;
	private double myWidth;
	private double myHeight;
	private HashMap<Integer, GraphicsContext> gc = new HashMap<>();
	private HashMap<Integer, TurtleImage> myTurtles = new HashMap<>();

	public TurtleWindow(double width, double height) {
		
		myWidth = width;
		myHeight = height;
		mainCanvas = new Canvas(width, height);
		mainGC = mainCanvas.getGraphicsContext2D();
		
		this.getChildren().add(mainCanvas);
		
		addTurtle();

	}
	

	public void addTurtle() {
		addTurtle(myWidth/2, myHeight/2);
	}

	public void addTurtle(double xPos, double yPos) {
		TurtleImage turtle = new TurtleImage();
		turtle.moveTo(xPos, yPos);
		myTurtles.put(myTurtles.size(), turtle);
		Canvas layer = new Canvas(mainCanvas.getWidth(), mainCanvas.getWidth());
		GraphicsContext layerGC = layer.getGraphicsContext2D();
		gc.put(gc.size(), layerGC);
		this.getChildren().addAll(turtle, layer);
	}

	public void changeBackground(Color c) {
		mainGC.setFill(c);
		mainGC.fillRect(0, 0, mainCanvas.getWidth(), mainCanvas.getHeight());
	}

	public void changePenColor(Color c, int turtleID) {
		gc.get(turtleID).setStroke(c);

	}

	public void changeTurtleImage(File file, int turtleID) {
		myTurtles.get(turtleID).changeImage(file);

	}
	
	public List<Integer> getAvailableTurtles() {
		List<Integer> turtleIDs = new ArrayList<Integer>(myTurtles.keySet());
		Collections.sort(turtleIDs);
		return turtleIDs;
	}
	
	@Override
	public void update(Object updateObject) {
		TurtleUpdate tu = (TurtleUpdate) updateObject;
		TurtleImage ti = myTurtles.get(0);
		ti.setRotate(tu.getTurtleAngle().getAngleValue());
		ti.moveTo(tu.getTurtleNewCoordinates().getX()+myWidth/2, tu
				.getTurtleNewCoordinates().getY()+myHeight/2);
		ti.hide(tu.isTurtleHidden());

		if (!tu.isTurtlePenUp()) {
			gc.get(0).strokeLine(tu.getTurtleOldCoordinates().getX()+myWidth/2,
					tu.getTurtleOldCoordinates().getY()+myHeight/2, ti.getTranslateX()+myWidth/2,
					ti.getTranslateY()+myHeight/2);
		}
	}

	@Override
	public void UIEvent() {
		// TODO Auto-generated method stub

	}


	@Override
	public void update(Object updateObject1, Object updateObject2) {
		// TODO Auto-generated method stub
		
	}

}
