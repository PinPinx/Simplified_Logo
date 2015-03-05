package view.components;

import java.util.ArrayList;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class Palette {
	private ArrayList<ImageIndex> myImagePalettes;
	private ArrayList<ColorIndex> myColorPalettes;
	
	public Palette(){
		myImagePalettes = new ArrayList<>();
		myColorPalettes = new ArrayList<>();
	}
	
	public void addImage(ImageIndex...imageIndexs){
		for (ImageIndex imgx : imageIndexs){
			myImagePalettes.add(imgx);
		}
	}
	
	public void addColor(ColorIndex...colorIndexs){
		for (ColorIndex colx : colorIndexs){
			myColorPalettes.add(colx);
		}
	}
	
	public void updateImage(int index, ImageIndex imgx){
		if(index >= myImagePalettes.size()){
			addImage(imgx);
			return;
		}
		myImagePalettes.set(index, imgx);
	}
	
	public void updateColor(int index, ColorIndex colx){
		if(index >= myColorPalettes.size()){
			addColor(colx);
			return;
		}
		myColorPalettes.set(index, colx);
	}
	
	public Image getImage(int index){
		return myImagePalettes.get(index).getImage();
	}
	
	public Color getColor(int index){
		return myColorPalettes.get(index).getColor();
	}

	public ArrayList<ImageIndex> getImageList(){
		return myImagePalettes;
	}
	
	public ArrayList<ColorIndex> getColorList(){
		return myColorPalettes;
	}
	
	public int imageListSize(){
		return myImagePalettes.size();
	}
	
	public int colorListSize(){
		return myColorPalettes.size();
	}
	
	
}
