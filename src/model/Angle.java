package model;

public class Angle {
	private double myValue;
	
	public Angle(int ang){
		this.myValue = ang;
	}
	public Angle(Angle a){
		this.myValue = a.getAngleValue();
	}
	
	public double getAngleValue(){
		return this.myValue;
	}
	public double getAngleValueInRadians(){
		return this.myValue/360.0*2*Math.PI;
	}
	
	public void addAngleValue(Angle a){
		this.myValue += a.getAngleValue();
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
}
