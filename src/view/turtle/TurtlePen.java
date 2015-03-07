package view.turtle;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * Constructs lines according to set styles.
 * 
 * @author lien
 *
 */
public class TurtlePen {
	private GraphicsContext gc;
	
	private IntegerProperty penColorIndex;
	private DoubleProperty penSize;
	
	// dash length arrays for the different line styles
	private static final double[] SOLID = {1, 0};
	private static final double[] DASHED = {7, 4};
	private static final double[] DOTTED = {1, 4};
	private static final double[] DASHDOT = {7, 3, 1, 3};
	
	// defaulted to solid
	private double[] dashLengthArray = SOLID; 
	
	public TurtlePen(GraphicsContext graphicsContext) {
		gc = graphicsContext;
	}
	
	public void drawLine(Point2D start, Point2D end) {
		if (dashLengthArray == SOLID) {
			gc.strokeLine(start.getX(), start.getY(), end.getX(), end.getY());
		}
		
		else {
			drawGappedLine(start, end);
		}
	}
	
	
	private void drawGappedLine(Point2D start, Point2D end) {
		
		if (start.equals(end)) {
			return;
		}
		
		Point2D lineVector = new Point2D(end.getX() - start.getX(), end.getY() - start.getY());
		Point2D[] dashVectorArray = generateDashVectorArray(lineVector);
		
		boolean on = true;
		Point2D curr = start;
		int currDash = 0;
		
		gc.moveTo(start.getX(), start.getY());
		
		while (((end.getX() - (curr.getX() + dashVectorArray[currDash].getX())) * (end.getX() - start.getX()) >= 0) && 
			   ((end.getY() - (curr.getY() + dashVectorArray[currDash].getY())) * (end.getY() - start.getY()) >= 0)) {
			Point2D next = new Point2D(curr.getX() + dashVectorArray[currDash].getX(), 
					  				   curr.getY() + dashVectorArray[currDash].getY());
			
			if (on) {
				gc.strokeLine(curr.getX(), curr.getY(), next.getX(), next.getY());
			} else {
				gc.moveTo(next.getX(), next.getY());
			}
			
			curr = next;
			on = !on;
			currDash = (currDash == dashVectorArray.length-1) ? 0 : (currDash + 1);
		}
		
		//edge case
		if (on) {
			gc.strokeLine(curr.getX(), curr.getY(), end.getX(), end.getY());
		}
		
	}
	
	private Point2D[] generateDashVectorArray(Point2D directionVector) {
		Point2D unitVector = getUnitVector(directionVector);
		Point2D[] dashVectorArray = new Point2D[dashLengthArray.length];
		
		for (int i=0; i<dashLengthArray.length; i++) {
			dashVectorArray[i] = new Point2D(
					(dashLengthArray[i] * gc.getLineWidth() * unitVector.getX()), 
					(dashLengthArray[i] * gc.getLineWidth() * unitVector.getY()));
			
		}

		return dashVectorArray;
	}
	

	private Point2D getUnitVector(Point2D vector) {
		double length = Math.sqrt(vector.getX()*vector.getX() + 
								  vector.getY()*vector.getY());
		return new Point2D(vector.getX()/length, vector.getY()/length);
	}
	
	public void setSolid() {
		dashLengthArray = SOLID;
	}
	
	public void setDashed() {
		dashLengthArray = DASHED;
	}
	
	public void setDotted() {
		dashLengthArray = DOTTED;
	}
	
	public void setDashDot() {
		dashLengthArray = DASHDOT;
	}
	
	public void setPenColorIndex(int ind, Color c){
		gc.setStroke(c);
		penColorIndex.setValue(ind);
	}
	
	public void setPenSizeIndex(double size){
		gc.setLineWidth(size);
		penSize.setValue(size);
	}
	
	public void setProperties(IntegerProperty colorIndex, DoubleProperty size){
		penColorIndex = colorIndex;
		penSize = size;
	}
	
	
}
