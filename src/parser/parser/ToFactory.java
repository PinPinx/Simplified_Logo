package parser.parser;

import java.util.Stack;

import exceptions.CommandNameNotFoundException;
import exceptions.UDCommandNotFoundException;
import model.State;
import parser.commands.ToData;
import parser.commands.ToInstance;
import parser.nodes.SyntaxNode;

public class ToFactory {
	private static ToFactory instance;

	private ToFactory(){
	
	}
	
	public static ToFactory getInstance(){
		if(instance == null){
			instance = new ToFactory();
		}
		return instance;
	}
	
	public SyntaxNode createTo(String[] stream, State myState, Stack<SyntaxNode> input, int index)
			throws CommandNameNotFoundException{
		if(index>0 && stream[index-1].equalsIgnoreCase("to")){
			return new ToData(stream[index], isolateToDeclaration(stream,index-1), input);	
		}
		try{
			ToData data = myState.getCommandHistory().getUDCommand(stream[index]);
			return new ToInstance(data, input);
		} catch (UDCommandNotFoundException e1){
			throw new CommandNameNotFoundException("Command "+stream[index]+" is neither a valid preset command nor user defined command.");
		}
	}
	
	/**
	 * Kind of a hacky solution to go through the command stream starting at a command "To"
	 * and recreating the "To name [ variables ] [ commands ]" string. Author: Kaighn
	 */
	private String isolateToDeclaration(String[] commandStream, int toIndex){
		StringBuilder b = new StringBuilder();
		int listStartCounter = 0;
		int numLists = 0;
		for(int i = toIndex; i < commandStream.length; i++){
			b.append(commandStream[i]);
			b.append(" ");
			
			GeneralType type = Regex.getInstance().getType(commandStream[i]);
			if(type == GeneralType.LISTSTART){
				listStartCounter++;
			} else if (type == GeneralType.LISTEND){
				listStartCounter--;
				if(listStartCounter == 0){
					numLists++;
					if(numLists == 2){
						break;
					}
				}
			}
		}
		return b.toString();
	}
}
