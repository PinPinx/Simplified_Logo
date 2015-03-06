package view.components;

import java.io.File;
import java.io.Serializable;

import javafx.scene.paint.Color;

public class WorkspaceFile implements Serializable{
	private String myBgColor;
	private Integer myTurtles;
	private File myLanguage;
	private String myTitle;
	
	public WorkspaceFile(){
		myBgColor = null;
		myTurtles = 0;
		myLanguage = null;
		myTitle = new String();
	}
	
	public WorkspaceFile(Color c){
		myBgColor = String.valueOf(c.toString());
		myTurtles = 1;
		myLanguage = null;
	}
	
	public WorkspaceFile(Color c, int turtles){
		myBgColor = String.valueOf(c.toString());
		myTurtles = turtles;
		myLanguage = null;
	}
	
	public WorkspaceFile(Color c, int turtles, File lang){
		myBgColor = String.valueOf(c.toString());
		myTurtles = turtles;
		myLanguage = lang;
	}
	
	public void setLanguage(File lang){
		myLanguage = lang;
	}
	
	public File getLanguage(){
		return myLanguage;
	}
	
	public void setColor(Color c){
		myBgColor = String.valueOf(c.toString());
	}
	
	public Color getColor(){
		Color c = Color.web(myBgColor);
		return c;
	}
	
	public void setTitle(String title){
		myTitle = title;
	}
	
	public String getTitle(){
		return myTitle;
	}
	
	public int getTurtles(){
		return myTurtles;
	}
	
	public void addTurtles(){
		myTurtles++;
	}
	
	public void setTurtles(int turtles){
		myTurtles = turtles;
	}

}
