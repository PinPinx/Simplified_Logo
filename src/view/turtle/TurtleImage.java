package view.turtle;

import java.io.File;
import java.util.PriorityQueue;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JColorChooser;
import javax.swing.JFileChooser;

import view.components.Palette;
import model.PenUpdate;
import model.TurtleUpdate;
import model.ViewUpdate;
import javafx.animation.RotateTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.Transition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public class TurtleImage extends ImageView {
	
	private Image myImage;
	private GraphicsContext gc;
	private TurtlePen myPen;
	
	private int myID;
	private double myWidth;
	private double myHeight;
	private double mySpeed;
	private boolean busy;
	private boolean debugMode;
	private PriorityQueue<TurtleUpdate> pendingUpdates;
	private Palette myPalette;
	
	private Boolean active;
	private Boolean visible;
	private Boolean penUp;
	
	private Group myStamps;
	
	// items in the pop-up context menu
	private ContextMenu contextMenu;
	private MenuItem changeImage;
	private MenuItem toggle;
	private MenuItem penColor;
	private Menu lineStyle;
	private Menu lineWidth;
	private MenuItem penUpDown;	
	
	// default parameters for the constructor
	private final static double DEFAULT_XPOS = 0;
	private final static double DEFAULT_YPOS = 0;
	private final static String DEFAULT_IMAGEPATH = "/resources/images/turtle-top-view.png";
	private final static double DEFAULT_WIDTH = 30;
	private final static double DEFAULT_HEIGHT = 30;
	private final static double DEFAULT_SPEED = 0.6;
	
	public TurtleImage(GraphicsContext gcon, int id) {
		this(gcon, id, DEFAULT_IMAGEPATH, DEFAULT_XPOS, DEFAULT_YPOS, DEFAULT_WIDTH, DEFAULT_HEIGHT);
	}

	public TurtleImage(GraphicsContext gcon, int id, String imagePath, double xPos,
			double yPos, double width, double height) {

		myImage = new Image(getClass().getResourceAsStream(imagePath));
		gc = gcon;
		myID = id;
		mySpeed = DEFAULT_SPEED;
		myPen = new TurtlePen(gc);
		myStamps = new Group();
		
		active = false;
		visible = true;
		penUp = false;
		busy = false;
		debugMode = false;
		
		pendingUpdates = new PriorityQueue<TurtleUpdate>();
		
		this.setImage(myImage);
		resize(width, height);
		
		Point2D startingPos = mathCoordsToCanvasCoords(new Point2D(xPos, yPos));
		this.setTranslateX(startingPos.getX());
		this.setTranslateY(startingPos.getY());
		
		this.setOnMouseEntered(e -> installStateTooltip());
		initializeMenu();
		
	}

	
	public void hide(boolean hidden) {
		visible = !hidden;
		Image next = hidden? null:myImage;
		this.setImage(next);
	}

	
	public void changeImage(Image img){
		myImage = img;
		this.setImage(myImage);
	}

	public void changeImage(String imagePath) {
		this.changeImage(new Image(imagePath));
	}
	
	public void changeImage(File file) {
		this.changeImage(new Image(file.toURI().toString()));
	}
	
	public void resize(double width, double height) {
		myWidth = width;
		myHeight = height;
		this.setFitWidth(width);
		this.setFitHeight(height);
	}
	
	public void setDebugMode(){
		debugMode = true;
	}
	
	public void setRunMode(){
		debugMode = false;
	}
	

	public void setPenProperties(PenUpdate pu){
		myPen.setProperties(pu.getPenColorIDProperty(),pu.getPenSizeProperty());
		myPen.updatePen(myPalette.getColor(myPen.getColorIndex()));
	}
	
	public void updatePalette(Palette p){
		myPalette = p;
	}
	
	public void addUpdate(TurtleUpdate tu){
		pendingUpdates.add(tu);
		
		if (!busy){
			popUpdate();
		}
		
	}
	
	
	public void popUpdate(){
		
		if (pendingUpdates.size()==0){
			busy = false;
		}
		
		else {
			processUpdate(pendingUpdates.poll());
		}
	}
	
	
	public void processUpdate(TurtleUpdate tu) {
		busy = true;
		active = !tu.isTurtleInactive();
		
		if (tu.isStamp()) {
			leaveStamp();
		}
		
		if (tu.isTurtleClear()) {
			gc.clearRect(0, 0, gc.getCanvas().getWidth(), gc.getCanvas()
					.getHeight());
		}
		
		myPen.updatePen(myPalette.getColor(myPen.getColorIndex()));
		
		Point2D oldPos = mathCoordsToCanvasCoords(new Point2D(tu
				.getTurtleOldCoordinates().getX(), tu.getTurtleOldCoordinates()
				.getY()));
		Point2D newPos = mathCoordsToCanvasCoords(new Point2D(tu
				.getTurtleNewCoordinates().getX(), tu.getTurtleNewCoordinates()
				.getY()));
		
		
		this.hide(tu.isTurtleHidden());

		if (!penUp) {
			myPen.drawLine(new Point2D(oldPos.getX() + myWidth / 2, oldPos.getY() + myHeight / 2),
						   new Point2D(newPos.getX() + myWidth / 2, newPos.getY() + myHeight / 2));
		}
		
		this.animatedMove(createRotateTransition(-tu.getTurtleAngle().getAngleValue()), createTranslateTransition(newPos));
		
	}
	
	private void animatedMove(Transition rotationTransition, Transition translationTransition){
		
		SequentialTransition sequentialTransition = new SequentialTransition(this, rotationTransition, translationTransition);
		sequentialTransition.play();
		
		sequentialTransition.setOnFinished(moved->{
			if(!debugMode){
				popUpdate();
			}
		});
	}

	
	private void initializeMenu() {
		changeImage = makeMenuItem("Change Turtle Image", e -> {
			selectImageFile();
		});
		
		toggle = makeMenuItem("Toggle Off", e -> {
			toggleTurtle();
		});
		
		penUpDown = makeMenuItem("Pen Up", e -> {
			setPenUpDown();
		});

		penColor = makeMenuItem("Choose Turtle Pen Color", e -> {
			selectPenColor();
		});
		
		lineStyle = new Menu("Choose Turtle Line Style");
		setLineStyleMenu();
		lineWidth = new Menu("Choose Turtle Line Width");
		setLineWidthMenu();
		
		contextMenu = new ContextMenu(changeImage, toggle, penUpDown, lineWidth,
				lineStyle, penColor);
		
		this.setOnMouseClicked(clicked -> popMyMenu());
	}
	
	private void popMyMenu() {
		contextMenu.show(this, this.getTranslateX(), this.getTranslateY());
	}
	
	private MenuItem makeMenuItem(String label, EventHandler<ActionEvent> event) {
		MenuItem item = new MenuItem(label);
		item.setOnAction(event);
		return item;
	}
	

	
	private Transition createRotateTransition(double destination) {
		double distance = Math.abs(this.getRotate() - destination);
		RotateTransition rt = new RotateTransition(Duration.millis(distance / (2*mySpeed)), this);
		rt.setToAngle(destination);
		return rt;
	}
	
	
	private Transition createTranslateTransition(Point2D destination) {
		
		double distance = destination.distance(this.getX(), this.getY());
		TranslateTransition tt = new TranslateTransition(Duration.millis(distance / mySpeed), this);
		tt.setToX(destination.getX());
		tt.setToY(destination.getY());	
		return tt;
	}


	
	private void installStateTooltip() {
		StringBuilder turtleInfo = new StringBuilder();
		Point2D Pos = canvasCoordsToMathCoords(new Point2D(
				this.getTranslateX(), this.getTranslateY()));
		turtleInfo.append("ID: " + myID + "\n");
		turtleInfo.append("X position: " + Pos.getX() + "\n");
		turtleInfo.append("Y position: " + Pos.getY() + "\n");
		turtleInfo.append("Heading: " + (-this.getRotate()) + "\n");
		turtleInfo.append("Active: " + active.toString() + "\n");
		turtleInfo.append("Pen Up: " + penUp.toString() + "\n");
		turtleInfo.append("Visiblity: " + visible.toString() + "\n");
		Tooltip t = new Tooltip(turtleInfo.toString());
		Tooltip.install(this, t);
	}
	
	private void selectPenColor(){
		java.awt.Color awtColor = JColorChooser.showDialog(null, "Choose color to add to palette", null);
		Color fxColor = Color.rgb(awtColor.getRed(), awtColor.getGreen(), awtColor.getBlue());
		//TODO - replace this menu with a palette
		gc.setStroke(fxColor);
	}

	
	private void selectImageFile() {
		JFileChooser imageChooser = new JFileChooser(System.getProperties()
				.getProperty("user.dir") + "/src/resources/images");
		imageChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		int retval = imageChooser.showOpenDialog(null);
		if (retval != JFileChooser.APPROVE_OPTION) {
			return;
		}
		changeImage(imageChooser.getSelectedFile());
		return;
	}

	private void toggleTurtle() {
		if (visible) {
			toggle.setText("Toggle On");
			visible = false;
			hide(true);
			return;
		}
		toggle.setText("Toggle Off");
		visible = true;
		hide(false);
	}

	private void setPenUpDown() {
		if (penUp) {
			penUpDown.setText("Pen Up");
			penUp = false;
			return;
		}
		penUpDown.setText("Pen Down");
		penUp = true;
		return;
	}

	/**
	 * Generates a toggle group for line styles (solid, dashed, dotted, dash-dot)
	 */
	private void setLineStyleMenu() {
		
		RadioMenuItem solid = new RadioMenuItem("Solid line");
		RadioMenuItem dashed = new RadioMenuItem("Dashed line");
		RadioMenuItem dotted = new RadioMenuItem("Dotted line");
		RadioMenuItem dashdot = new RadioMenuItem("Dash-dot line");
	
		ToggleGroup styleGroup = new ToggleGroup();
		
		lineStyle.getItems().addAll(solid, dashed, dotted, dashdot);

		for (MenuItem item: lineStyle.getItems()) {
			((RadioMenuItem) item).setToggleGroup(styleGroup);
		}
		
		solid.setOnAction(chooseLineStyle -> {
			myPen.setSolid();
		});
		
		dashed.setOnAction(chooseLineStyle -> {
			myPen.setDashed();
		});

		dotted.setOnAction(chooseLineStyle -> {
			myPen.setDotted();
		});
		
		dashdot.setOnAction(chooseLineStyle -> {
			myPen.setDashDot();
		});

	}
	
	/**
	 * Generates a toggle group for line width from 1-10
	 */
	private void setLineWidthMenu() {
		ToggleGroup widthGroup = new ToggleGroup();
		
		for (Integer i = 1; i <= 10; i++) {
			final int j = i;
			RadioMenuItem sizeChoice = new RadioMenuItem(i.toString());
			sizeChoice.setToggleGroup(widthGroup);
			sizeChoice.setOnAction(chooseWidth -> {
				gc.setLineWidth(j);
			});
			lineWidth.getItems().add(sizeChoice);
		}
		
	}

	public void setAnimationSpeed(double speed) {
		mySpeed = speed;
	}
	
	private Point2D mathCoordsToCanvasCoords(Point2D mathCoords) {

		return new Point2D(mathCoords.getX() + (gc.getCanvas().getWidth() / 2 - myWidth / 2),
				          -mathCoords.getY() + (gc.getCanvas().getHeight() / 2 - myHeight / 2));
	}

	private Point2D canvasCoordsToMathCoords(Point2D canvasCoords) {
		return new Point2D(canvasCoords.getX() - (gc.getCanvas().getWidth() / 2 - myWidth / 2),
				          -canvasCoords.getY() + (gc.getCanvas().getHeight() / 2 - myHeight / 2));
	}
	
	private void leaveStamp() {
		ImageView stamp = new ImageView(myImage);
		stamp.setFitWidth(myWidth);
		stamp.setFitHeight(myHeight);
		stamp.setRotate(this.getRotate());
		stamp.setTranslateX(this.getTranslateX());
		stamp.setTranslateY(this.getTranslateY());
		myStamps.getChildren().add(stamp);
	}
	
	public void clearStamps() {
		myStamps.getChildren().remove(0, myStamps.getChildren().size());;
	}
	
	public Node getStamps() {
		return myStamps;
	}

}
