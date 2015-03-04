package view.components;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import view.turtle.TurtleImage;
import model.TurtleUpdate;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class TurtleWindow extends Group implements TurtleObserver {
	
	private Canvas mainCanvas;
	private GraphicsContext mainGC;
	private double myWidth;
	private double myHeight;
	private int numTurtles;
	private boolean showInactiveTurtles;
	
	private Group myLayers = new Group();
	private HashMap<Integer, GraphicsContext> gc = new HashMap<>();
	private HashMap<Integer, TurtleImage> myTurtles = new HashMap<>();
	private Group myTImages =  new Group();

	public TurtleWindow(double width, double height) {

		myWidth = width;
		myHeight = height;
		numTurtles = 0;
		
		mainCanvas = new Canvas(myWidth, myHeight);
		mainGC = mainCanvas.getGraphicsContext2D();
		showInactiveTurtles = true;
		
		this.getChildren().add(mainCanvas);
		this.getChildren().addAll(myLayers, myTImages);
		
		addTurtle();
		
	}

	public void addTurtle() {
		int i=0;
		while (myTurtles.keySet().contains(i)) {
			i++;
		}
		addTurtle(i);
	}
	

	public void addTurtle(int id) {
		numTurtles++;
		Canvas layer = new Canvas(mainCanvas.getWidth(), mainCanvas.getWidth());
		GraphicsContext layerGC = layer.getGraphicsContext2D();
		gc.put(id, layerGC);
		
		TurtleImage turtle = new TurtleImage(layerGC, id);
		
		myTurtles.put(id, turtle);
		myLayers.getChildren().add(layer);
		myTImages.getChildren().add(turtle);
		
	}

	
	@Override
	public void update(TurtleUpdate tu) {
		int id = tu.getTurtleID();
		if (!myTurtles.keySet().contains(id)) {
			addTurtle(id);
		}
		myTurtles.get(id).update(tu);
	}
	
	
	public void updateAnimationSpeed(double speed) {
		for (int turtleID: myTurtles.keySet()) {
			myTurtles.get(turtleID).setAnimationSpeed(speed);
		}
	}
	
	
	public void changeBackground(Color c) {
		mainGC.setFill(c);
		mainGC.fillRect(0, 0, mainCanvas.getWidth(), mainCanvas.getHeight());
	}
	
	public Color getBackgroundColor() {
		return (Color) mainGC.getFill();
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
	
	public int getNumOfTurtles(){
		return numTurtles;
	}
	
	public void toggleInactiveTurtles(boolean show) {
		showInactiveTurtles = show;
		for (int id: myTurtles.keySet()) {
			myTurtles.get(id).toggleShowHidden();
		}
	}
	
	public boolean showInactiveTurtles() {
		return showInactiveTurtles;
	}

	
}
