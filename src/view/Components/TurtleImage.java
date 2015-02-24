package view.Components;

import java.io.File;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class TurtleImage extends ImageView{
	private Image myImage;
	private final static double default_xPosition = 0.0;
	private final static double default_yPosition = 0.0;
	private final static double default_width = 30.0;
	private final static double default_height = 30.0;
	
	
	public TurtleImage(String imagePath){
		this(imagePath, default_xPosition, default_yPosition, default_width, default_height);
	}
	
	public TurtleImage(String imagePath, double xPos, double yPos){
		this(imagePath, xPos, yPos, default_width, default_height);
	}
	
	public TurtleImage(String imagePath, double xPos, double yPos, double width, double height){
		myImage = new Image(imagePath);
		this.setImage(myImage);
		this.setTranslateX(xPos);
		this.setTranslateY(yPos);
		this.setFitWidth(width);
		this.setFitHeight(height);
	}
	
	public void hide(boolean hidden){
		if (hidden){
			this.setImage(null);
			return;
		}
		this.setImage(myImage);
	}
	
	
	public void changeImage(File file) {
		myImage = new Image(file.toURI().toString());
		this.setImage(myImage);
		
	}
	
	public void moveTo(double xPos, double yPos){
		this.setTranslateX(xPos);
		this.setTranslateY(yPos);
	}

	public void resize(double width, double height){
		this.setFitWidth(width);
		this.setFitHeight(height);
	}
}
