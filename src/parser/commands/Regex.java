package parser.commands;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.AbstractMap.SimpleEntry;
import java.util.Enumeration;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.regex.Pattern;

import Exceptions.CommandNameNotFoundException;
import model.Model;


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
	
	public void changeLanguage(String filePath){
		languageFileString = filePath;
		commandPatterns.clear();
		commandPatterns.addAll(makePatterns(languageFileString));
	}
	
	private boolean match (String input, Pattern regex) {
		return regex.matcher(input).matches();
	}

	public GeneralType getType(String s){
		for (Entry<String, Pattern> p : this.generalPatterns) {
			if (match(s, p.getValue())) {
				return GeneralType.findType(p.getKey());
			}
		}
		return GeneralType.OTHER;
	}
	
	public String getCommandType(String s) throws CommandNameNotFoundException{
		for (Entry<String, Pattern> p : this.commandPatterns) {
			if (match(s, p.getValue())) {
				return p.getKey();
			}
		}
		throw new CommandNameNotFoundException();
	}

	private void testMatches (String[] tests, List<Entry<String, Pattern>> patterns) {
		for (String s : tests) {
			boolean matched = false;
			if (s.trim().length() > 0) {
				for (Entry<String, Pattern> p : patterns) {
					if (match(s, p.getValue())) {
						System.out.println(String.format("%s matches %s", s, p.getKey()));
						matched = true;
						break;
					}
				}
				if (! matched) {
					System.out.println(String.format("%s not matched", s));
				}
			}
		}
		System.out.println();
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


