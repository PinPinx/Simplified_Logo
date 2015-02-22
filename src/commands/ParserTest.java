package commands;

import static org.junit.Assert.*;
import model.CommandHistory;
import model.Coordinates;
import model.State;
import model.TurtleSingle;
import model.VariablesCollection;

import org.junit.Test;

public class ParserTest {	
	@Test
	public void testParse() {
		State myState = new State(new TurtleSingle(), new VariablesCollection(), new CommandHistory());
		Parser myParser = new Parser(myState);
		CommandRoot root = myParser.parse("blah blah blah");
		root.execute();
		Coordinates result = myState.getTurtle().getCoordinates();
		Coordinates desired = new Coordinates(50,0);
		assertTrue(result.equals(desired));
	}

}
