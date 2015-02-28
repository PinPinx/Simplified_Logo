package view.components;

import java.io.File;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class TurtleImage extends ImageView {
	private Image myImage;
	private final static double DEFAULT_XPOS = 0.0;
	private final static double DEFAULT_YPOS = 0.0;
	private static double myWidth = 30.0;
	private static double myHeight = 30.0;
	private final static String DEFAULT_IMAGEPATH = "/resources/images/turtle-top-view.png";

	public TurtleImage() {
		this(DEFAULT_IMAGEPATH, DEFAULT_XPOS, DEFAULT_YPOS, myWidth,
				myHeight);
	}

	public TurtleImage(String imagePath) {
		this(imagePath, DEFAULT_XPOS, DEFAULT_YPOS, myWidth, myHeight);
	}

	public TurtleImage(String imagePath, double xPos, double yPos) {
		this(imagePath, xPos, yPos, myWidth, myHeight);
	}

	public TurtleImage(String imagePath, double xPos, double yPos,
			double width, double height) {
		myImage = new Image(getClass().getResourceAsStream(imagePath));
		this.setImage(myImage);
		this.setTranslateX(xPos - myWidth / 2);
		this.setTranslateY(yPos - myHeight / 2);
		this.setFitWidth(width);
		this.setFitHeight(height);
		this.setOnMouseClicked(e->hide(true));
	}

	public void hide(boolean hidden) {
		if (hidden) {
			this.setImage(null);
			return;
		}
		this.setImage(myImage);
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
}
