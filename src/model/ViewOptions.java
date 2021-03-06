package model;

import java.util.ArrayList;
import java.util.List;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import exceptions.InvalidViewSettingException;
import view.components.Observer;

/**
 * Container class for information that is not particular to any one turtle.
 */
public class ViewOptions implements ViewUpdate, ViewInitializer, Observable{
	private IntegerProperty backgroundIDProperty;
	private int paletteR, paletteG, paletteB, paletteIndex;
	private boolean isClear, isClearStamps, isStampsOut;
	private List<Observer> myObservers;

	public ViewOptions(){
		this.myObservers = new ArrayList<>();
		backgroundIDProperty = new SimpleIntegerProperty(0);
	}
	
	public void initialize(){
		notifyObservers((ViewInitializer)this);
	}
	
	public IntegerProperty getBackgroundID() {
		IntegerProperty copy = new SimpleIntegerProperty(backgroundIDProperty.get());
		backgroundIDProperty.bindBidirectional(copy);
		return copy;
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

	public boolean isClear() {
		boolean b = isClear;
		isClear = false;
		return b;
	}
	public boolean isClearStamps() {
		return isClearStamps;
	}

	public void setBackgroundID(int backgroundID) {
		backgroundIDProperty.set(backgroundID);
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
	public void setPaletteB(int r, int g, int b, int index) throws InvalidViewSettingException {
		if(!((r>=0&&r<256)&&(b>=0&&b<256)&&(g>=0&&g<256))){
			throw new InvalidViewSettingException("RGB values must be between 0 and 255 inclusive.");
		}
		this.paletteB = b;
		this.paletteG = g;
		this.paletteR = r;
		this.paletteIndex = index;
		notifyObservers();
	}
	
	public void setStampsOut(boolean b){
		isStampsOut = b;
	}
	
	public boolean isStampsOut(){
		boolean b = isStampsOut;
		isStampsOut = false;
		return b;
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
	
	public void notifyObservers(Object obj) {
		for(Observer o : myObservers){
			o.update(obj);
		}
	}

	@Override
	public int getPaletteIndex() {
		return paletteIndex;
	}
	
	


}
