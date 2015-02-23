package model;

import Exceptions.VariableCreationException;
import commands.GeneralType;
import commands.Regex;

public class VariableFactory {
	public static Variable createVariable(String varName, String varValue) throws VariableCreationException{
		if(Regex.getInstance().getType(varValue) == GeneralType.CONSTANT){
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
		}
		throw new VariableCreationException();

	}
}
