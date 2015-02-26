package model;

import javafx.geometry.Point2D;

public class Coordinates {
	private Point2D myPoint;
	
	public Coordinates(double x, double y){
		myPoint = new Point2D(x,y);
	}
	public Coordinates(Coordinates c){
		myPoint = new Point2D(c.getX(), c.getY());
	}
	public void addCoordinates(Coordinates p){
		myPoint = new Point2D(this.myPoint.getX() + p.getX(), this.myPoint.getY() + p.getY());
	}
	public void add(double x, double y){
		myPoint = new Point2D(myPoint.getX() + x, myPoint.getY() + y);
	}
	public void scale(double factor){
		myPoint = new Point2D(myPoint.getX()*factor, myPoint.getY()*factor);
	}
	public double getX(){
		return myPoint.getX();
	}
	public double getY(){
		return myPoint.getY();
	}
	public boolean equals(Coordinates c){
		return this.getX() == c.getX() &&
				this.getY() == c.getY();
	}
	public double distance(Coordinates c){
		return Math.sqrt(Math.pow(this.getX() - c.getX(),2) 
				+ Math.pow(this.getY() - c.getY(),2)); 
	}
}
