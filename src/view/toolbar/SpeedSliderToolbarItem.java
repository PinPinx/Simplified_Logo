package view.toolbar;

import javafx.beans.value.ChangeListener;
import javafx.scene.Node;
import javafx.scene.control.Slider;

public class SpeedSliderToolbarItem extends ToolbarItem {
	
	private static final double MIN_VALUE = 0.05;
	private static final double MAX_VALUE = 0.5;
	private static final double DEFAULT_VALUE = .3;

	protected SpeedSliderToolbarItem(String label, Toolbar container) {
		super(label, container);
	}
	
	public void addListener(ChangeListener<Number> changeListener) {
		((Slider) myNode).valueProperty().addListener(changeListener);
	}
	
	@Override
	protected Node createNode() {
		Slider slider = new Slider();
		slider.setMin(MIN_VALUE);
		slider.setMax(MAX_VALUE);
		slider.setValue(DEFAULT_VALUE);		
		return slider;
	}

}
