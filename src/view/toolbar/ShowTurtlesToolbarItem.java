package view.toolbar;


import javafx.scene.Node;
import javafx.scene.control.Button;

public class ShowTurtlesToolbarItem extends ToolbarItem {
	
	private static final String SHOW = "Inactive turtles: shown ";
	private static final String HIDE = "Inactive turtles: hidden";
	private boolean show;
	
	protected ShowTurtlesToolbarItem(Toolbar container) {
		super(SHOW, container);
		show = true;
	}

	
	@Override
	protected Node createNode() {
		Button toggleTurtles = new Button("Toggle");
		toggleTurtles.setOnAction(e->{
			show = !show;
			String label = (show) ? SHOW:HIDE;
			setLabel(label);
			myToolbar.getTurtleWindow().toggleInactiveTurtles(show);
		});
		return toggleTurtles;
	}
	
	
}
