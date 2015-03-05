package view.components;

import java.io.File;

import javafx.scene.image.Image;

public class ImageIndex {
	private int myIndex;
	private Image myImage;
	private String myName;
	
	
	public ImageIndex(int ind, String name, Image img){
		myIndex = ind;
		myName = name;
		myImage = img;
	}

	public void rename(String newName){
		myName = newName;
	}
	
	public void changeImage(Image newImage){
		myImage = newImage;
	}
	
	public void changeImage(File file){
		myImage = new Image(file.toURI().toString());
	}
	
	public Image getImage(){
		return myImage;
	}
	
	public String getName(){
		return myName;
	}
	
	public int getIndex(){
		return myIndex;
	}
}
