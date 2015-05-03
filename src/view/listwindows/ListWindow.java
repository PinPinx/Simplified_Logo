package view.listwindows;

import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

/**
 * Generic window to display boxes, with a scroll bar as needed.
 * 
 * @author lien
 *
 */
public abstract class ListWindow extends Group {

	protected BorderPane myBorderPane;
	
	protected Label myLabel;
	protected VBox myList;
	protected ScrollPane myScrollPane;
	protected int width;
	protected int height;

	public ListWindow(int w, int h, String label) {
		width = w;
		height = h;
		myBorderPane = new BorderPane();
		myBorderPane.setPrefSize(width, height);
		getChildren().add(myBorderPane);
		addLabel(label);
		initScrollList();
	}

	private void addLabel(String label) {
		myLabel = new Label(label);
		myBorderPane.setTop(myLabel);
	}

	private void initScrollList() {
		myList = new VBox();
		myScrollPane = new ScrollPane(myList);
		myScrollPane.setVbarPolicy(ScrollBarPolicy.AS_NEEDED);
		myBorderPane.setCenter(myScrollPane);
	}

}
