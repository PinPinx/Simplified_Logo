package model;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;

public interface PenUpdate {
	public IntegerProperty getPenColorIDProperty();
	public DoubleProperty getPenSizeProperty();
}
