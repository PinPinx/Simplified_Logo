package view.toolbar;

import java.io.File;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;

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
			Stage stage = new Stage();
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.show();
			
			myFile = makeImageChooser().showOpenDialog(stage);
			
			getAndModifyTurtles();
		});
		
		return choose;
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
