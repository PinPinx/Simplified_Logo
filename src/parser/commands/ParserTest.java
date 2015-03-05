package parser.commands;

import static org.junit.Assert.*;
import model.Angle;
import model.CommandHistory;
import model.Coordinates;
import model.State;
import model.TurtleMultiple;
import model.TurtleSingle;
import model.VariablesCollection;

import org.junit.Test;

import exceptions.BadArgumentException;
import exceptions.CommandNameNotFoundException;
import exceptions.SyntaxErrorWrongFormat;
import parser.nodes.CommandRoot;
import parser.parser.Parser;

public class ParserTest {	
	@Test
	public void testParse() throws BadArgumentException, CommandNameNotFoundException, SyntaxErrorWrongFormat {
		State myState = new State();
		Parser myParser = new Parser();
		myParser.setActiveState(myState);
		CommandRoot root = myParser.parse("fd 50");
		root.execute(myState);
		Coordinates result = myState.getTurtle().getCoordinates();
		Coordinates desired = new Coordinates(50,0);
		assertTrue(result.equals(desired));
		
		root = myParser.parse("hi");
		root.execute(myState);
		Angle result2 = myState.getTurtle().getAngle();
		Angle desired2 = new Angle(90);
		assertTrue(result2.equals(desired2));
		
		root = myParser.parse("blah blah blah");
		root.execute(myState);
		result = myState.getTurtle().getCoordinates();
		desired = new Coordinates(50,50);
		assertTrue(result.equals(desired));
		
		
	}

}
