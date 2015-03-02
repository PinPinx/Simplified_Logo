package view.components;

import java.io.File;

import javax.swing.JFileChooser;

import model.TurtleUpdate;
import javafx.animation.RotateTransition;
import javafx.animation.TranslateTransition;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class TurtleImage extends ImageView {
	private Image myImage;
	private GraphicsContext gc;
	private double[] dashLengthArray = SOLID;
	private final static double DEFAULT_XPOS = 0.0;
	private final static double DEFAULT_YPOS = 0.0;
	private static double myWidth = 30.0;
	private static double myHeight = 30.0;
	private final static String DEFAULT_IMAGEPATH = "/resources/images/turtle-top-view.png";
	private Boolean visible = true;
	private Boolean penUp = false;

	private MenuItem changeImage;
	private MenuItem toggle;
	private MenuItem penColor;
	private Menu lineStyle;
	private Menu lineWidth;
	private MenuItem penUpDown;
	private ContextMenu contextMenu;
	
	private static final double[] SOLID = {1, 0};
	private static final double[] DASHED = {7, 4};
	private static final double[] DOTTED = {1.5, 7.5};
	private static final double[] DASHDOT = {7, 3, 1.5, 3};
	
	private double animationSpeed = 0.6;

	public TurtleImage(GraphicsContext gcon) {
		this(gcon, DEFAULT_IMAGEPATH, DEFAULT_XPOS, DEFAULT_YPOS, myWidth,
				myHeight);
	}

	public TurtleImage(GraphicsContext gcon, double xPos, double yPos) {
		this(gcon, DEFAULT_IMAGEPATH, xPos, yPos, myWidth, myHeight);
	}

	public TurtleImage(GraphicsContext gcon, String imagePath, double xPos,
			double yPos, double width, double height) {

		myImage = new Image(getClass().getResourceAsStream(imagePath));
		gc = gcon;

		Point2D startingPos = mathCoordsToCanvasCoords(new Point2D(xPos, yPos));
		this.setImage(myImage);
		this.setTranslateX(startingPos.getX());
		this.setTranslateY(startingPos.getY());
		this.setFitWidth(width);
		this.setFitHeight(height);

		initializeMenuItems();

		this.setOnMouseClicked(e -> popMyMenu());
		this.setOnMouseEntered(e -> installStateTooltip());

	}

	public void hide(boolean hidden) {
		if (hidden) {
			this.setImage(null);
			visible = !hidden;
			return;
		}
		this.setImage(myImage);
		visible = !hidden;
	}

	public void changeImage(String imagePath) {
		myImage = new Image(imagePath);
	}

	public void changeImage(File file) {
		myImage = new Image(file.toURI().toString());
		this.setImage(myImage);
	}

	public void resize(double width, double height) {
		myWidth = width;
		myHeight = height;
		this.setFitWidth(width);
		this.setFitHeight(height);
	}

	public void update(TurtleUpdate tu) {
		Point2D oldPos = mathCoordsToCanvasCoords(new Point2D(tu
				.getTurtleOldCoordinates().getX(), tu.getTurtleOldCoordinates()
				.getY()));
		Point2D newPos = mathCoordsToCanvasCoords(new Point2D(tu
				.getTurtleNewCoordinates().getX(), tu.getTurtleNewCoordinates()
				.getY()));
		animatedRotate(-tu.getTurtleAngle().getAngleValue());
		animatedMove(newPos);
		
		this.hide(tu.isTurtleHidden());

		// need to bind penUp to the back end,
		// penUp = tu.isTurtlePenUp();

		if (!penUp & !oldPos.equals(newPos)) {
			drawGappedLine(new Point2D(oldPos.getX() + myWidth / 2, oldPos.getY() + myHeight / 2),
						   new Point2D(newPos.getX() + myWidth / 2, newPos.getY() + myHeight / 2));
					 
		}

		if (tu.isTurtleClear()) {
			gc.clearRect(0, 0, gc.getCanvas().getWidth(), gc.getCanvas()
					.getHeight());
		}
	}

	private void popMyMenu() {
		contextMenu.show(this, this.getTranslateX(), this.getTranslateY());

	}

	private void initializeMenuItems() {
		changeImage = new MenuItem("Change Turtle Image");
		toggle = new MenuItem("Toggle Off");
		penColor = new MenuItem("Choose Turtle Pen Color");
		lineStyle = new Menu("Choose Turtle Line Style");
		setLineStyleMenu();
		lineWidth = new Menu("Choose Turtle Line Width");
		setLineWidthMenu();

		penUpDown = new MenuItem("Pen Up");

		contextMenu = new ContextMenu(changeImage, toggle, penColor, lineWidth,
				lineStyle, penUpDown);

		changeImage.setOnAction(e -> {
			selectImageFile();
		});

		toggle.setOnAction(e -> {
			toggleTurtle();
		});

		penUpDown.setOnAction(e -> {
			setPenUpDown();
		});
		
		// TODO
		penColor.setOnAction(e -> {

		});

	}

	private void animatedRotate(double destination) {
		double distance = Math.abs(this.getRotate() - destination);
		RotateTransition rt = new RotateTransition(Duration.millis(distance
				/ animationSpeed), this);
		rt.setToAngle(destination);
		rt.play();
	}

	private void animatedMove(Point2D destination) {
		double distance = destination.distance(this.getX(), this.getY());
		TranslateTransition tt = new TranslateTransition(
				Duration.millis(distance / animationSpeed), this);
		tt.setToX(destination.getX());
		tt.setToY(destination.getY());
		tt.play();
		
	}

	
	private void drawGappedLine(Point2D start, Point2D end) {
		if (dashLengthArray == SOLID) {
			gc.strokeLine(start.getX(), start.getY(), end.getX(), end.getY());
		} 
		
		else {
			Point2D lineVector = new Point2D(end.getX() - start.getX(), end.getY() - start.getY());
			Point2D[] dashVectorArray = generateDashVectorArray(lineVector);
			
			boolean on = true;
			Point2D curr = start;
			int currDash = 0;
			
			gc.moveTo(start.getX(), start.getY());
			
			while (((end.getX() - (curr.getX() + dashVectorArray[currDash].getX())) * (end.getX() - start.getX()) >= 0) && 
				   ((end.getY() - (curr.getY() + dashVectorArray[currDash].getY())) * (end.getY() - start.getY()) >= 0)) {
				Point2D next = new Point2D(curr.getX() + dashVectorArray[currDash].getX(), 
						  				   curr.getY() + dashVectorArray[currDash].getY());
				
				if (on) {
					gc.strokeLine(curr.getX(), curr.getY(), next.getX(), next.getY());
				} else {
					gc.moveTo(next.getX(), next.getY());
				}
				
				curr = next;
				on = !on;
				currDash = (currDash == dashVectorArray.length-1) ? 0 : (currDash + 1);
			}
			
			//edge case
			if (on) {
				gc.strokeLine(curr.getX(), curr.getY(), end.getX(), end.getY());
			}
			
		}	
	}
	
	private Point2D[] generateDashVectorArray(Point2D directionVector) {
		Point2D unitVector = getUnitVector(directionVector);
		Point2D[] dashVectorArray = new Point2D[dashLengthArray.length];
		
		for (int i=0; i<dashLengthArray.length; i++) {
			dashVectorArray[i] = new Point2D(
					(dashLengthArray[i] * gc.getLineWidth() * unitVector.getX()), 
					(dashLengthArray[i] * gc.getLineWidth() * unitVector.getY()));
			
		}

		return dashVectorArray;
		
	}
	

	private Point2D getUnitVector(Point2D vector) {
		double length = Math.sqrt(vector.getX()*vector.getX() + 
								  vector.getY()*vector.getY());
		return new Point2D(vector.getX()/length, vector.getY()/length);
	}
	
	
	private void installStateTooltip() {
		StringBuilder turtleInfo = new StringBuilder();
		turtleInfo.append("Turtle Info: \n");
		Point2D Pos = canvasCoordsToMathCoords(new Point2D(
				this.getTranslateX(), this.getTranslateY()));
		turtleInfo.append("X position: " + Pos.getX() + "\n");
		turtleInfo.append("Y position: " + Pos.getY() + "\n");
		turtleInfo.append("Heading: " + this.getRotate() + "\n");
		turtleInfo.append("Pen Up: " + penUp.toString() + "\n");
		turtleInfo.append("Visiblity: " + visible.toString() + "\n");
		Tooltip t = new Tooltip(turtleInfo.toString());
		Tooltip.install(this, t);
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

	private void setLineStyleMenu() {
		RadioMenuItem solid = new RadioMenuItem("Solid line");
		RadioMenuItem dashed = new RadioMenuItem("Dashed line");
		RadioMenuItem dotted = new RadioMenuItem("Dotted line");
		RadioMenuItem dashdot = new RadioMenuItem("Dash-dot line");

		lineStyle.getItems().addAll(solid, dashed, dotted, dashdot);

		ToggleGroup styleGroup = new ToggleGroup();

		solid.setToggleGroup(styleGroup);
		dashed.setToggleGroup(styleGroup);
		dotted.setToggleGroup(styleGroup);
		dashdot.setToggleGroup(styleGroup);
		
		solid.setOnAction(chooseLineStyle -> {
			dashLengthArray = SOLID;
		});
		
		dashed.setOnAction(chooseLineStyle -> {
			dashLengthArray = DASHED;
		});

		dotted.setOnAction(chooseLineStyle -> {
			dashLengthArray = DOTTED;
		});
		
		dashdot.setOnAction(chooseLineStyle -> {
			dashLengthArray = DASHDOT;
		});
		

	}

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

	
	private Point2D mathCoordsToCanvasCoords(Point2D mathCoords) {

		return new Point2D(mathCoords.getX() + (gc.getCanvas().getWidth() / 2 - myWidth / 2),
				          -mathCoords.getY() + (gc.getCanvas().getHeight() / 2 - myHeight / 2));
	}

	private Point2D canvasCoordsToMathCoords(Point2D canvasCoords) {
		return new Point2D(canvasCoords.getX() - (gc.getCanvas().getWidth() / 2 - myWidth / 2),
				          -canvasCoords.getY() + (gc.getCanvas().getHeight() / 2 - myHeight / 2));
	}

	public void setAnimationSpeed(double speed) {
		animationSpeed = speed;
	}

}
