package view.toolbar;

import view.Components.LabelledComponent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

/**
 * Item to be placed in the toolbar.
 * Includes a label and a node.
 * 
 * @author lien
 *
 */
public abstract class ToolbarItem extends HBox implements LabelledComponent {
	
	protected Toolbar myToolbar; // where the item is held
	protected Node myNode;
	protected Label myLabel;
	
	// Graphic properties
	private static final Color ITEM_COLOR = Color.DARKGREY;
	private static final int PADDING = 3;
	private static final int CORNER_RADIUS = 5;
	private static final int SPACING = 3;
	
	protected ToolbarItem(String label, Toolbar container) {
		myToolbar = container;
		myNode = createNode();
		
		this.setAlignment(Pos.CENTER);
		this.setPadding(new Insets(PADDING));
		this.setBackground(new Background(new BackgroundFill(ITEM_COLOR, new CornerRadii(CORNER_RADIUS), null)));
		this.setSpacing(SPACING);
		
		Label myLabel = new Label(label);
		this.getChildren().addAll(myLabel, myNode);
	}
	
	public void changeLanguage(String language) {
	//	myLabel.setText(value);
	}
	
	protected abstract Node createNode();

}
