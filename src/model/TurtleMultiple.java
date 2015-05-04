package model;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import exceptions.TurtleNotFoundException;
import view.View;
import view.components.Observer;

/**
 * Has similar behavior to TurtleSingle following the composite pattern.
 */
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
		getStream().filter(s -> s.getID()!=lastActingTurtle.getID()).forEach(s->s.addDegree(degree));
		return lastActingTurtle.addDegree(degree);
	}

	@Override
	public double moveDistance(double distance){
		getStream().filter(s -> s.getID()!=lastActingTurtle.getID()).forEach(s->s.moveDistance(distance));
		return lastActingTurtle.moveDistance(distance);
	}

	@Override
	public double setHeading(double degrees){
		getStream().filter(s -> s.getID()!=lastActingTurtle.getID()).forEach(s->s.setHeading(degrees));
		return lastActingTurtle.setHeading(degrees);
	}
	
	@Override
	public double setTowards(double x, double y){
		getStream().filter(s -> s.getID()!=lastActingTurtle.getID()).forEach(s->s.setTowards(x, y));
		return lastActingTurtle.setTowards(x, y);
	}
	
	@Override
	public double moveToPosition(double x, double y) {
		getStream().filter(s -> s.getID()!=lastActingTurtle.getID()).forEach(s->s.moveToPosition(x, y));
		return lastActingTurtle.moveToPosition(x, y);
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
		return getStream().map(s-> s.getID()).collect(Collectors.toList());
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

	@Override
	public boolean isFence() {
		return lastActingTurtle.isFence();
	}

	@Override
	public void setFence(boolean b) {
		getStream().forEach(t -> t.setFence(b));
	}
}
