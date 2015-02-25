package model;

import exceptions.VariableCreationException;
import exceptions.VariableCreationInvalidValueException;
import parser.commands.Regex;

public class VariableFactory {
	public static Variable createVariable(String varName, String varValue) throws VariableCreationException, VariableCreationInvalidValueException{
		switch(Regex.getInstance().getType(varValue)){
		case COMMAND: case COMMENT: case OTHER:
			return new VariableString(varName, varValue);		
		case CONSTANT:
			try{
				int i = Integer.parseInt(varValue);
				return new VariableInt(varName, i);
			} catch(NumberFormatException e){
				try{
					double d = Double.parseDouble(varValue);
					return new VariableDouble(varName, d);
				} catch(Exception e2){
					throw new VariableCreationException();
				}
			}
		case GROUPEND: case GROUPSTART: case LISTEND: case LISTSTART: default:
			throw new VariableCreationInvalidValueException();
		
		}
	}
}
