package model;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import exceptions.TurtleNotFoundException;
import view.View;
import view.components.Observer;

public class TurtleMultiple implements Turtle {
	private Map<Integer,TurtleSingle> myTurtleMap;
	private TurtleSingle lastActingTurtle;
	private boolean stampsOut;

	public TurtleMultiple(){
		myTurtleMap = new HashMap<>();
		addNewTurtle(0);
	}

	public void addNewTurtle(int turtleID){
		TurtleSingle newTurtle = new TurtleSingle(turtleID);
		myTurtleMap.put(turtleID,newTurtle);
		setLastTurtle(newTurtle);
		newTurtle.addObserver(View.getInstance().getTurtleWindow());
		newTurtle.createPen();
		newTurtle.notifyObservers();
	}

	public int numTurtles(){
		return myTurtleMap.size();
	}

	public void deactiveAll(){
		getStream().forEach(s -> s.setInactive(true));		
	}

	public TurtleSingle getTurtleSingle(int turtleID) throws TurtleNotFoundException{
		TurtleSingle t;
		t = myTurtleMap.get(turtleID);
		setLastTurtle(t);
		if(t==null){
			throw new TurtleNotFoundException("");
		}
		return t;
	}

	@Override
	public void addObserver(Observer o) {
		getStream().forEach(s -> s.addObserver(o));		
	}

	@Override
	public void removeObserver(Observer o) {
		getStream().forEach(s -> s.removeObserver(o));		
	}

	@Override
	public void notifyObservers() { }

	@Override
	public Angle getAngle() {
		return lastActingTurtle.getAngle();
	}

	@Override
	public Coordinates getCoordinates() {
		return lastActingTurtle.getCoordinates();
	}

	@Override
	public Coordinates getOldCoordinates() {
		return lastActingTurtle.getOldCoordinates();
	}
	
	

	@Override
	public double addDegree(double degree){
		double ret = 0;
		for(TurtleSingle t : myTurtleMap.values()){
			if(!t.getInactive()){
				setLastTurtle(t);
				ret = t.addDegree(degree);
			}
		}
		return ret;
	}

	@Override
	public double moveDistance(double distance){
		double ret = 0;
		for(TurtleSingle t : myTurtleMap.values()){
			if(!t.getInactive()){
				setLastTurtle(t);
				ret = t.moveDistance(distance);
			}
		}
		return ret;
	}

	@Override
	public double setHeading(double degrees){
		double ret = 0;
		for(TurtleSingle t : myTurtleMap.values()){
			if(!t.getInactive()){
				ret = t.setHeading(degrees);
				setLastTurtle(t);
			}
		}
		return ret;
	}
	
	@Override
	public double setTowards(double x, double y){
		double ret = 0;
		for(TurtleSingle t : myTurtleMap.values()){
			if(!t.getInactive()){
				ret = t.setTowards(x, y);
				setLastTurtle(t);
			}
		}
		return ret;
	}
	
	@Override
	public double moveToPosition(double x, double y) {
		double d = 0;
		for(TurtleSingle t : myTurtleMap.values()){
			if(!t.getInactive()){
				d = t.moveToPosition(x, y);
				setLastTurtle(t);
			}
		}
		return d;
	}

	@Override
	public void setHidden(boolean b) {
		getStream().forEach(s -> s.setHidden(b));		
	}

	@Override
	public void setInactive(boolean b) {
		getStream().forEach(s -> s.setInactive(b));				
	}

	@Override
	public void setPenUp(boolean b) {
		getStream().forEach(s -> s.setPenUp(b));			
	}

	@Override
	public boolean getHidden() {
		return lastActingTurtle.getHidden();
	}

	@Override
	public boolean getInactive() {
		return lastActingTurtle.getInactive();
	}

	@Override
	public boolean getPenUp() {
		return lastActingTurtle.getPenUp();
	}
	
	@Override
	public int getShapeID() {
		return lastActingTurtle.getShapeID();
	}

	@Override
	public boolean getStamp() {
		boolean b = lastActingTurtle.getStamp();
		if(b){
			stampsOut = true;
		}
		return b;
	}

	public List<Integer> activeTurtleIDs(){
		ArrayList<Integer> returner = new ArrayList<Integer>();
		for(TurtleSingle t : myTurtleMap.values()){
			if (!t.getInactive()){
				returner.add(t.getID());
			}
		}
		return returner;
	}
	
	public int getID(){
		return lastActingTurtle.getID();
	}
	
	private void setLastTurtle(TurtleSingle t){
		lastActingTurtle = t;
	}
	
	public void changePen(PenChanger pc) {
		getStream().forEach(s -> s.changePen(pc));		
	}
	
	public void change(TurtleSingleChanger tsc){
		getStream().forEach(s -> s.change(tsc));		
	}

	@Override
	public void setStamp(boolean b) {
		getStream().forEach(s -> s.setStamp(b));		
	}

	@Override
	public void setShapeID(int ID) {
		getStream().forEach(s -> s.setShapeID(ID));		
	}

	@Override
	public int getPenColor() {
		return lastActingTurtle.getPenColor();
	}
	
	private Stream<TurtleSingle> getStream(){
		Stream<TurtleSingle> stream =  this.myTurtleMap.values().stream() 
	      .filter(s -> !s.getInactive());
		setLastTurtle(stream.max((a,b)-> {return Integer.compare(a.getID(), b.getID());}).get());
		stream =  this.myTurtleMap.values().stream() 
			      .filter(s -> !s.getInactive());
		return stream;
	}
}
