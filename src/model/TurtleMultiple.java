package model;

import java.util.ArrayList;
import java.util.List;

import view.components.TurtleObserver;

public class TurtleMultiple implements Turtle {
	private List<TurtleSingle> myTurtleList;
	private TurtleSingle lastActingTurtle;
	
	public TurtleMultiple(){
		myTurtleList = new ArrayList<>();
		myTurtleList.add(new TurtleSingle());
		lastActingTurtle = myTurtleList.get(0);
	}
	
	public void addNewTurtle(){
		TurtleSingle newTurtle = new TurtleSingle();
		myTurtleList.add(newTurtle);
		setLastTurtle(newTurtle);
	}
	
	public void toggleTurtleActive(int turtleID){
		Turtle t = myTurtleList.get(turtleID);
		t.setInactive(!t.getInactive());
	}
	
	@Override
	public void addObserver(TurtleObserver o) {
		for(TurtleSingle t : myTurtleList){
			t.addObserver(o);
		}
	}

	@Override
	public void removeObserver(TurtleObserver o) {
		for(TurtleSingle t : myTurtleList){
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
		// TODO Auto-generated method stub
		return lastActingTurtle.getCoordinates();
	}

	@Override
	public Coordinates getOldCoordinates() {
		// TODO Auto-generated method stub
		return lastActingTurtle.getOldCoordinates();
	}

	@Override
	public void addDegree(Double d) {
		for(TurtleSingle t : myTurtleList){
			if(!t.getInactive()){
				t.addDegree(d);
				setLastTurtle(t);
			}
		}
	}

	@Override
	public void addCoordinates(Coordinates p) {
		for(TurtleSingle t : myTurtleList){
			if(!t.getInactive()){
				t.addCoordinates(p);
				setLastTurtle(t);
			}
		}
	}

	@Override
	public double moveToPosition(double x, double y) {
		double d = 0;
		for(TurtleSingle t : myTurtleList){
			if(!t.getInactive()){
				d = t.moveToPosition(x, y);
				setLastTurtle(t);
			}
		}
		return d;
	}

	@Override
	public void setHidden(boolean b) {
		for(TurtleSingle t : myTurtleList){
			if(!t.getInactive()){
				t.setHidden(b);
				setLastTurtle(t);
			}
		}
	}

	@Override
	public void setInactive(boolean b) {
		for(TurtleSingle t : myTurtleList){
			if(!t.getInactive()){
				t.setInactive(b);
				setLastTurtle(t);
			}
		}		
	}

	@Override
	public void setPenUp(boolean b) {
		for(TurtleSingle t : myTurtleList){
			if(!t.getInactive()){
				t.setPenUp(b);
				setLastTurtle(t);
			}
		}		
	}

	@Override
	public void setClear(boolean b) {
		for(TurtleSingle t : myTurtleList){
			if(!t.getInactive()){
				t.setClear(b);
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

	@Override
	public boolean getClear() {
		return lastActingTurtle.getClear();
	}
	
	private void setLastTurtle(TurtleSingle t){
		lastActingTurtle = t;
	}

}
