package view.toolbar;

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
public abstract class ToolbarItem extends HBox {
	
	protected Toolbar myToolbar; // where the item is held
	protected Node myNode;
	
	protected ToolbarItem(String label, Toolbar container) {
		myToolbar = container;
		myNode = createNode();
		
		this.setAlignment(Pos.CENTER);
		this.setPadding(new Insets(3));
		this.setBackground(new Background(new BackgroundFill(Color.DARKGREY, new CornerRadii(5), null)));
		
		Label myLabel = new Label(label);
		this.getChildren().addAll(myLabel, myNode);
	}
	
	protected abstract Node createNode();

}
