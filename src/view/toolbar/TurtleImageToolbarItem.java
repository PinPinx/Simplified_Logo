package view.toolbar;

import java.io.File;
import java.util.Arrays;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

/**
 * Toolbar Item to change turtle image.
 * 
 * @author lien
 *
 */
public class TurtleImageToolbarItem extends TurtleSpecificToolbarItem {
	
	//TODO: Hardcoded to English for now
	private static final String CHOOSE = "Choose";
	private File myFile;
	
	protected TurtleImageToolbarItem(String label, Toolbar tb) {
		super(label, tb);
	}


	@Override
	public void changeTurtleProperties(int turtleID) {
		myToolbar.getTurtleWindow().changeTurtleImage(myFile, turtleID);
		
	}

	@Override
	protected Node createNode() {
		Button choose = new Button(CHOOSE);
		choose.setOnAction(event -> {
			
			myFile = makeImageChooser().showOpenDialog(null);
			
			getAndModifyTurtles();
		});
		
		return choose;
	}


	private FileChooser makeImageChooser() {
		FileChooser imageChooser = new FileChooser();
		imageChooser.setTitle("Choose Image File");
		imageChooser.getExtensionFilters().addAll(
				new ExtensionFilter("Images", Arrays.asList("*.jpg", "*.png", "*.gif")));
		return imageChooser;
	}
	
}
