package view.components;


import java.io.File;





import javax.swing.JFileChooser;

import model.TurtleUpdate;
import sun.applet.Main;
import view.View;
import javafx.event.ActionEvent;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class TurtleImage extends ImageView {
	private Image myImage;
	private GraphicsContext gc;
	private final static double DEFAULT_XPOS = 0.0;
	private final static double DEFAULT_YPOS = 0.0;
	private static double myWidth = 30.0;
	private static double myHeight = 30.0;
	private final static String DEFAULT_IMAGEPATH = "/resources/images/turtle-top-view.png";
	private boolean visible = true;
	private boolean penUp = false;
	
	private MenuItem changeImage;
	private MenuItem toggle;
	private MenuItem penColor;
	private MenuItem lineStyle;
	private MenuItem penUpDown;
	private ContextMenu contextMenu;
	

	public TurtleImage(GraphicsContext gcon) {
		this(gcon, DEFAULT_IMAGEPATH, DEFAULT_XPOS, DEFAULT_YPOS, myWidth,
				myHeight);
	}

	public TurtleImage(GraphicsContext gcon, String imagePath) {
		this(gcon, imagePath, DEFAULT_XPOS, DEFAULT_YPOS, myWidth, myHeight);
	}

	public TurtleImage(GraphicsContext gcon, String imagePath, double xPos, double yPos) {
		this(gcon, imagePath, xPos, yPos, myWidth, myHeight);
	}

	public TurtleImage(GraphicsContext gcon, String imagePath, double xPos, double yPos,
			double width, double height) {
		myImage = new Image(getClass().getResourceAsStream(imagePath));
		gc = gcon;
		this.setImage(myImage);
		this.setTranslateX(xPos - myWidth / 2);
		this.setTranslateY(yPos - myHeight / 2);
		this.setFitWidth(width);
		this.setFitHeight(height);
		
		
		initializeMenuItems();
		
		this.setOnMouseClicked(e->popMyMenu());
		
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

	public void moveTo(double xPos, double yPos) {
		this.setTranslateX(xPos - myWidth / 2);
		this.setTranslateY(yPos - myHeight / 2);
	}

	public void resize(double width, double height) {
		myWidth = width;
		myHeight = height;
		this.setFitWidth(width);
		this.setFitHeight(height);
	}
	
	public void update(TurtleUpdate tu){
		Point2D oldPos = mathCoordsToCanvasCoords(new Point2D(tu
				.getTurtleOldCoordinates().getX(), tu.getTurtleOldCoordinates()
				.getY()));
		Point2D newPos = mathCoordsToCanvasCoords(new Point2D(tu
				.getTurtleNewCoordinates().getX(), tu.getTurtleNewCoordinates()
				.getY()));

		this.setRotate(-tu.getTurtleAngle().getAngleValue());
		this.moveTo(newPos.getX(), newPos.getY());
		this.hide(tu.isTurtleHidden());
		
		//need to bind penUp to the back end, 
		//penUp = tu.isTurtlePenUp();

		if (!penUp) {
			gc.strokeLine(oldPos.getX(), oldPos.getY(), newPos.getX(),
					newPos.getY());
		}

		if(tu.isTurtleClear()){
			gc.clearRect(0, 0, myWidth, myHeight);
		}
	}
	
	private void popMyMenu(){
		contextMenu.show(this, this.getTranslateX(), this.getTranslateY());
		
	}
	
	private void initializeMenuItems(){
		changeImage = new MenuItem("Change Turtle Image");
		toggle = new MenuItem("Toggle Off");
		penColor = new MenuItem("Choose Turtle Pen Color");
		lineStyle = new MenuItem("Choose Turtle Line Style");
		penUpDown = new MenuItem("Pen Up");
		
		contextMenu = new ContextMenu(changeImage, toggle, penColor, lineStyle, penUpDown);
		
		changeImage.setOnAction(e-> {
			selectImageFile();
		});
		
		toggle.setOnAction(e-> {
			toggleTurtle();
		});
		
		penUpDown.setOnAction(e-> {
			setPenUpDown();
		});
		
	}
	
	private void selectImageFile() {
		JFileChooser imageChooser = new JFileChooser(System.getProperties().getProperty("user.dir")+"/src/resources/images");
		imageChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		int retval = imageChooser.showOpenDialog(null);
        if (retval != JFileChooser.APPROVE_OPTION) {
            return;
        }
        changeImage(imageChooser.getSelectedFile());
        return;
	}
	
	private void toggleTurtle(){
		if (visible){
			toggle.setText("Toggle On");
			visible = false;
			hide(true);
			return;
		}
		toggle.setText("Toggle Off");
		visible = true;
		hide(false);
	}
	
	private void setPenUpDown(){
		if (penUp){
			penUpDown.setText("Pen Up");
			penUp = false;
			return;
		}
		penUpDown.setText("Pen Down");
		penUp = true;
		return;
	}
	
	private Point2D mathCoordsToCanvasCoords(Point2D mathCoords) {
		return new Point2D(gc.getCanvas().getWidth() / 2 + mathCoords.getX(), gc.getCanvas().getHeight() / 2
				- mathCoords.getY());
	}
	
}
