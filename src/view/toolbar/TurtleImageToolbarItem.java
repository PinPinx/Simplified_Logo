package view.toolbar;

import javafx.scene.Node;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

public class TurtleImageToolbarItem extends TurtleSpecificToolbarItem {

	protected TurtleImageToolbarItem(String label, Toolbar tb) {
		super(label, tb);
	}

	@Override
	protected Node createNode() {
		
		return null;
	}
	
	@Override
	public void changeTurtleProperties(int turtleID) {
		// TODO Auto-generated method stub
		
	}


	private FileChooser makeImageChooser() {
		FileChooser imageChooser = new FileChooser();
		imageChooser.setTitle("Choose Image File");
		imageChooser.getExtensionFilters().addAll(
				new ExtensionFilter("JPG Images", "*.jpg"),
				new ExtensionFilter("PNG Images", "*.png"));
		return imageChooser;
	}
	
}
