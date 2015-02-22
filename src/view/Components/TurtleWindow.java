package view.Components;

import java.util.HashMap;

import model.TurtleUpdate;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import view.TurtleImage;
import view.ViewComponent;

public class TurtleWindow extends Group implements ViewComponent {
	private Canvas mainCanvas;
	private GraphicsContext mainGC;
	private HashMap<Integer, GraphicsContext> gc = new HashMap<>();
	private HashMap<Integer, TurtleImage> myTurtles = new HashMap<>();

	public TurtleWindow(double width, double height) {
		mainCanvas = new Canvas(width, height);
		mainGC = mainCanvas.getGraphicsContext2D();

		this.getChildren().addAll(mainCanvas);

		addTurtle();

		myTurtles.get(0).setRotate(45);

	}

	@Override
	public void update(Object updateObject) {
		// TODO Auto-generated method stub
	}

	public void update(TurtleUpdate tu) {
		TurtleImage ti = myTurtles.get(0);
		ti.setRotate(tu.getTurtleAngle().getAngleValue());
		ti.moveTo(tu.getTurtleNewCoordinates().getX(), tu
				.getTurtleNewCoordinates().getY());
		ti.hide(tu.isTurtleHidden());
		if (tu.isTurtlePenUp()) {
			gc.get(0).strokeLine(tu.getTurtleOldCoordinates().getX(),
					tu.getTurtleOldCoordinates().getY(), ti.getTranslateX(),
					ti.getTranslateY());
		}
	}

	public void addTurtle() {
		addTurtle(250, 250);
	}

	public void addTurtle(double xPos, double yPos) {
		TurtleImage turtle = new TurtleImage("images/duke.gif");
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

	public void changePenColor(Color c) {
		// this method will be expanded to using turtle IDs.
		gc.get(0).setStroke(c);
	}

	public void changeLineWidth(double width) {
		gc.get(0).setLineWidth(width);
	}

	@Override
	public void UIEvent() {
		// TODO Auto-generated method stub

	}

}
