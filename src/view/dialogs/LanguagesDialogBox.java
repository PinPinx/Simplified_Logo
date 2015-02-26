package view.dialogs;

import java.util.ArrayList;
import java.util.List;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.scene.control.ChoiceBox;
import view.components.LanguageController;

public class LanguagesDialogBox extends InputDialogBox {

	private ChoiceBox<String> langChoiceBox;
	private List<String> availableLanguages;
	private static final String LANGUAGE_PROMPT = "Please choose your language:";

	public LanguagesDialogBox() {
		super(LANGUAGE_PROMPT);
		availableLanguages = new ArrayList<String>(
				LanguageController.getAvailableLanguages());
		populateChoiceBox();
	}

	@Override
	protected void addInputField() {
		langChoiceBox = new ChoiceBox<String>();

		langChoiceBox.getSelectionModel().selectedIndexProperty()
				.addListener(new ChangeListener<Number>() {

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
		langChoiceBox.setItems(FXCollections
				.observableArrayList(availableLanguages));
	}

	@Override
	protected void updateUserInput() {
	};

}
