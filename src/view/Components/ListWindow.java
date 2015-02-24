package view.Components;

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
public abstract class ListWindow extends Group implements ViewComponent {
	
	protected BorderPane myPane;
	protected Label myLabel;
	protected VBox myList;
	protected ScrollPane myScrollPane;
	protected int width;
	protected int height;
	
	public ListWindow(int w, int h, String label) {
		width = w;
		height = h;
		myPane = new BorderPane();
		myPane.setPrefSize(width, height);
		getChildren().add(myPane);
		addLabel(label);
		initScrollList();
	}
	
	
	private void addLabel(String label) {
		myLabel = new Label(label);
		myPane.setTop(myLabel);		
	}

	
	private void initScrollList() {
		myList = new VBox();
		myScrollPane = new ScrollPane(myList);
		myScrollPane.setVbarPolicy(ScrollBarPolicy.AS_NEEDED);
		myPane.setCenter(myScrollPane);
	}
	

}
