package view.Components;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;


/**
 * Singleton class to facilitate localization.
 * 
 * @author lien
 *
 */
public class LanguageController {

	private static LanguageController instance;

	private static final List<String> AVAILABLE_LANGUAGES;
	static {
		AVAILABLE_LANGUAGES = new ArrayList<String>();
		AVAILABLE_LANGUAGES.addAll(Arrays.asList("Chinese", "English",
				"French", "German", "Italian", "Japanese", "Korean",
				"Portuguese", "Russian", "Spanish"));
	}

	private static final String RESOURCES_FOLDER_PATH = "/resources/languages/";
	private static final String RESOURCES_FILE_EXTENSION = ".properties";

	public String getCommandLanguagesFilePath(String language) {
		String filePath = RESOURCES_FOLDER_PATH + language
				+ RESOURCES_FILE_EXTENSION;
		return filePath;
	}

	public Collection<String> getAvailableLanguages() {
		return Collections.unmodifiableCollection(AVAILABLE_LANGUAGES);
	}

	private LanguageController() {
	};

	public static LanguageController getInstance() {
		if (instance == null) {
			instance = new LanguageController();
		}
		return instance;
	}

}
