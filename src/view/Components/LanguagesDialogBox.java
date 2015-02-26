package view.Components;

import java.util.ArrayList;
import java.util.List;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.scene.control.ChoiceBox;
import view.InputDialogBox;

public class LanguagesDialogBox extends InputDialogBox {
	
	private ChoiceBox<String> langChoiceBox;
	private List<String> availableLanguages;
	private LanguageController myLanguageController;
	
	
	public LanguagesDialogBox(LanguageController lc) {
		super("Please choose your language");
		myLanguageController = lc;
		availableLanguages = new ArrayList<String>(myLanguageController.getAvailableLanguages());
		populateChoiceBox();
	}
	


	@Override
	protected void addInputField() {
		langChoiceBox = new ChoiceBox<String>();
		
		langChoiceBox.getSelectionModel().selectedIndexProperty().addListener(new
				ChangeListener<Number>() {
			
					@Override
					public void changed(
							ObservableValue<? extends Number> observable,
							Number oldValue, Number newValue) {
						userInput = availableLanguages.get((int) newValue);
						
					}
		});
		
		myPane.setCenter(langChoiceBox);
		
	}
	
	private void populateChoiceBox() {
		langChoiceBox.setItems(FXCollections.observableArrayList(availableLanguages));
	}


	@Override
	protected void updateUserInput() {};

}
