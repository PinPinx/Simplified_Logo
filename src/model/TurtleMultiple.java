package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import exceptions.TurtleNotFoundException;
import view.View;
import view.components.Observer;

public class TurtleMultiple implements Turtle {
	private Map<Integer,TurtleSingle> myTurtleMap;
	private TurtleSingle lastActingTurtle;

	public TurtleMultiple(){
		myTurtleMap = new HashMap<>();
		myTurtleMap.put(0,new TurtleSingle(0));
		lastActingTurtle = myTurtleMap.get(0);
	}

	public void addNewTurtle(int turtleID){
		TurtleSingle newTurtle = new TurtleSingle(turtleID);
		myTurtleMap.put(turtleID,newTurtle);
		setLastTurtle(newTurtle);
		newTurtle.addObserver(View.getInstance().getTurtleWindow());
		newTurtle.notifyObservers();
	}

	public int numTurtles(){
		return myTurtleMap.size();
	}

	public void deactiveAll(){
		for(TurtleSingle t : myTurtleMap.values()){
			t.setInactive(true);
		}
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

	public void toggleTurtleActive(int turtleID, boolean inactive){
		Turtle t = myTurtleMap.get(turtleID);
		t.setInactive(inactive);
	}

	@Override
	public void addObserver(Observer o) {
		for(TurtleSingle t : myTurtleMap.values()){
			t.addObserver(o);
		}
	}

	@Override
	public void removeObserver(Observer o) {
		for(TurtleSingle t : myTurtleMap.values()){
			t.removeObserver(o);
		}
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
	public void addDegree(Double d) {
		for(TurtleSingle t : myTurtleMap.values()){
			if(!t.getInactive()){
				t.addDegree(d);
				setLastTurtle(t);
			}
		}
	}

	@Override
	public void moveDistance(double distance) {
		for(TurtleSingle t : myTurtleMap.values()){
			if(!t.getInactive()){
				t.moveDistance(distance);
				setLastTurtle(t);
			}
		}
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
		for(TurtleSingle t : myTurtleMap.values()){
			if(!t.getInactive()){
				t.setHidden(b);
				setLastTurtle(t);
			}
		}
	}

	@Override
	public void setInactive(boolean b) {
		for(TurtleSingle t : myTurtleMap.values()){
			t.setInactive(b);
			setLastTurtle(t);
		}		
	}

	@Override
	public void setPenUp(boolean b) {
		for(TurtleSingle t : myTurtleMap.values()){
			if(!t.getInactive()){
				t.setPenUp(b);
				setLastTurtle(t);
			}
		}		
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

	//TODO: Kaighn, tell me if you're happy with these additions
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

}
