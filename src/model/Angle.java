package model;

public class Angle {
	private double myValue;
	
	public Angle(double parameter){
		this.myValue = parameter;
		modAngleValue();
	}
	public Angle(Angle a){
		this.myValue = a.getAngleValue();
	}
	
	public double getAngleValue(){
		return myValue;
	}
	
	public double getAngleValueInRadians(){
		return Math.toRadians(this.myValue);
	}
	
	public void addAngle(Angle a){
		this.myValue += a.getAngleValue();
		modAngleValue();
	}
	
	public void addAngleValue(double a){
		this.myValue += a;
		modAngleValue();
	}
	
	public void setAngleValue(double d){
		this.myValue = d;
		modAngleValue();
	}
	
	private void modAngleValue(){
		if(this.myValue > 360){
			this.myValue = this.myValue % 360;
		}
		else if(this.myValue < 0){
			while(this.myValue < 0){
				this.myValue += 360;
			}
		}
	}
	
	public boolean equals(Angle a){
		return this.myValue == a.getAngleValue();
	}
}
