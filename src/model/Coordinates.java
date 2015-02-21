package model;

import javafx.geometry.Point2D;

public class Coordinates {
	private Point2D myPoint;
	
	public Coordinates(double x, double y){
		this.myPoint = new Point2D(x,y);
	}
	public Coordinates(Coordinates c){
		this.myPoint = new Point2D(c.getX(), c.getY());
	}
	public void addCoordinates(Coordinates p){
		this.myPoint = new Point2D(myPoint.getX() + p.getX(), myPoint.getY() + p.getY());
	}
	public void add(double x, double y){
		this.myPoint = new Point2D(myPoint.getX() + x, myPoint.getY() + y);
	}
	public double getX(){
		return this.myPoint.getX();
	}
	public double getY(){
		return this.myPoint.getY();
	}
}
