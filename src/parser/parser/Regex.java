package parser.parser;

import java.util.AbstractMap.SimpleEntry;
import java.util.Enumeration;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

import view.components.LanguageController;
import exceptions.CommandNameNotFoundException;;


public class Regex {
	String languageFileString;
	List<Entry<String, Pattern>> generalPatterns = new ArrayList<>();
	List<Entry<String, Pattern>> commandPatterns = new ArrayList<>();
	
	private static Regex instance;

	private Regex(){
		languageFileString = "resources/languages/English";
		commandPatterns.addAll(makePatterns(languageFileString));
		generalPatterns.addAll(makePatterns("resources/languages/Syntax"));
	}
	
	public static Regex getInstance(){
		if(instance == null){
			instance = new Regex();
		}
		return instance;
	}
	
	/**
	 * Given file path, will clear the current language patterns and load the new ones from the file.
	 */
	public void changeLanguage(String language){
		languageFileString = LanguageController.getCommandLanguagesFilePath(language);
		commandPatterns.clear();
		commandPatterns.addAll(makePatterns(languageFileString));
	}
	
	private boolean match (String input, Pattern regex) {
		return regex.matcher(input).matches();
	}
	
	/**
	 * Returns the general type of a string, general type being eg. comment, command, variable, etc.
	 */
	public GeneralType getType(String s){
		for (Entry<String, Pattern> p : this.generalPatterns) {
			if (match(s, p.getValue())) {
				return GeneralType.findType(p.getKey());
			}
		}
		return GeneralType.OTHER;
	}
	
	/**
	 * For a string that's of generalType.Command, this will return the string class name of the command.
	 */
	public String getCommandType(String s) throws CommandNameNotFoundException{
		for (Entry<String, Pattern> p : this.commandPatterns) {
			if (match(s, p.getValue())) {
				return p.getKey();
			}
		}
		throw new CommandNameNotFoundException("This command "+s+" is not a preset command.");
	}
	
	/**
	 * If given the class name of a command, like "MakeVariable", will return a string of
	 * the current language's command string (eg. "make" if the language is english).
	 */
	public String getCommandString(String commandName) throws CommandNameNotFoundException{
		for (Entry<String, Pattern> p : this.commandPatterns) {
			if (commandName.equalsIgnoreCase(p.getKey())) {
				String regString = p.getValue().toString();
				return regString.substring(0,regString.indexOf('|'));
			}
		}
		throw new CommandNameNotFoundException("This command "+commandName+" is not a preset command.");
	}
	
	private List<Entry<String, Pattern>> makePatterns (String syntax) {
		ResourceBundle resources = ResourceBundle.getBundle(syntax);
		List<Entry<String, Pattern>> patterns = new ArrayList<>();
		Enumeration<String> iter = resources.getKeys();
		while (iter.hasMoreElements()) {
			String key = iter.nextElement();
			String regex = resources.getString(key);
			patterns.add(new SimpleEntry<String, Pattern>(key,
					Pattern.compile(regex, Pattern.CASE_INSENSITIVE)));
		}
		return patterns;
	}
}


