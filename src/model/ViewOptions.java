package model;

import java.util.ArrayList;
import java.util.List;

import exceptions.InvalidViewSettingException;
import view.components.Observer;

public class ViewOptions implements ViewUpdate, Observable{
	private int backgroundID, penColorID, shapeID,
		paletteR, paletteG, paletteB;
	private boolean isClear, isStamp, isClearStamps;
	private double penSize;
	private List<Observer> myObservers;
	
	public ViewOptions(){
		this.myObservers = new ArrayList<>();
	}
	
	public int getBackgroundID() {
		return backgroundID;
	}
	public int getPaletteB() {
		return paletteB;
	}
	public int getPaletteG() {
		return paletteG;
	}
	public int getPaletteR() {
		return paletteR;
	}
	public int getPenColorID() {
		return penColorID;
	}
	public double getPenSize() {
		return penSize;
	}
	public int getShapeID() {
		return shapeID;
	}
	public boolean isClear() {
		boolean b = isClear;
		isClear = false;
		return b;	}
	public boolean isClearStamps() {
		boolean b = isClearStamps;
		isClearStamps = false;
		return b;	}
	public boolean isStamp() {
		boolean b = isStamp;
		isStamp = false;
		return b;
	}
	public void setBackgroundID(int backgroundID) {
		this.backgroundID = backgroundID;
		notifyObservers();
	}
	public void setClear(boolean isClear) {
		this.isClear = isClear;
		notifyObservers();
	}
	public void setClearStamps(boolean isClearStamps) {
		this.isClearStamps = isClearStamps;
		notifyObservers();
	}
	public void setPaletteB(int r, int g, int b) throws InvalidViewSettingException {
		if(!((r>=0&&r<256)&&(b>=0&&b<256)&&(g>=0&&g<256))){
			throw new InvalidViewSettingException("RGB values must be between 0 and 255 inclusive.");
		}
		this.paletteB = b;
		this.paletteG = g;
		this.paletteR = r;
		notifyObservers();
	}
	public void setPenColorID(int penColorID) {
		this.penColorID = penColorID;
		notifyObservers();
	}
	public void setPenSize(double penSize) {
		this.penSize = penSize;
		notifyObservers();
	}
	public void setShapeID(int shapeID) {
		this.shapeID = shapeID;
		notifyObservers();
	}
	public void setStamp(boolean isStamp) {
		this.isStamp = isStamp;
		notifyObservers();
	}
	
	@Override
	public void addObserver(Observer o) {
		myObservers.add(o);
	}

	@Override
	public void removeObserver(Observer o) {
		myObservers.remove(o);
	}

	@Override
	public void notifyObservers() {
		for(Observer o : myObservers){
			o.update((ViewUpdate)this);
		}
	}
	
	


}
