package view;

import javafx.geometry.Pos;
import javafx.scene.layout.BorderPane;
import javafx.scene.web.WebView;

public class HelpDialogBox extends DialogBox {
	
	private String myURL = "http://www.cs.duke.edu/courses/compsci308/spring15/assign/03_slogo/commands.php";
	
	public HelpDialogBox() {
		super();
		addWebView();
	}
	
	private void addWebView() {
		WebView page = new WebView();
		page.getEngine().load(myURL.toString());
		myPane.setCenter(page);
		BorderPane.setAlignment(page, Pos.CENTER);
	}
}
