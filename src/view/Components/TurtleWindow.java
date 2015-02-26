package view.Components;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import model.TurtleUpdate;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class TurtleWindow extends Group implements TurtleObserver {

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

	private Point2D mathCoordsToCanvasCoords(Point2D mathCoords) {
		return new Point2D(myWidth / 2 + mathCoords.getX(), myHeight / 2
				- mathCoords.getY());
	}

	public void addTurtle() {

		addTurtle(0, 0);
	}

	public void addTurtle(double xPos, double yPos) {
		TurtleImage turtle = new TurtleImage();
		Point2D pos = mathCoordsToCanvasCoords(new Point2D(xPos, yPos));
		turtle.moveTo(pos.getX(), pos.getY());
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
	public void update(TurtleUpdate tu) {
		TurtleImage ti = myTurtles.get(0);

		Point2D oldPos = mathCoordsToCanvasCoords(new Point2D(tu
				.getTurtleOldCoordinates().getX(), tu.getTurtleOldCoordinates()
				.getY()));
		Point2D newPos = mathCoordsToCanvasCoords(new Point2D(tu
				.getTurtleNewCoordinates().getX(), tu.getTurtleNewCoordinates()
				.getY()));

		ti.setRotate(-tu.getTurtleAngle().getAngleValue());
		ti.moveTo(newPos.getX(), newPos.getY());
		ti.hide(tu.isTurtleHidden());

		if (!tu.isTurtlePenUp()) {
			gc.get(0).strokeLine(oldPos.getX(), oldPos.getY(), newPos.getX(),
					newPos.getY());
		}
	}

}
