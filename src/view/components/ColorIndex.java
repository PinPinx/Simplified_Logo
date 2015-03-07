package view.components;

import javafx.scene.paint.Color;



public class ColorIndex {
	
	private int myIndex;
	private Color myColor;
	private String myName;
	
	
	public ColorIndex(int ind, String name, Color color){
		myIndex = ind;
		myName = name;
		myColor = color;
	}
	
	public ColorIndex(int ind, String name, java.awt.Color awtColor){
		myIndex = ind;
		myName = name;
		myColor = Color.rgb(awtColor.getRed(), awtColor.getGreen(), awtColor.getBlue());
	}
	
	public ColorIndex(int ind, int r, int g, int b){
		myIndex = ind;
		myName = "Palette: "+ind;
		myColor = Color.rgb(r, g, b);
	}

	public void rename(String newName){
		myName = newName;
	}
	
	public void changeColor(Color color){
		myColor = color;
	}
	
	public String getString(){
		return String.valueOf(myColor.toString());
	}
	
	public Color getColor(){
		return myColor;
	}
	
	public String getName(){
		return myName;
	}
	
	public int getIndex(){
		return myIndex;
	}

}
