package parser.commands;

import Exceptions.BadArgumentException;

public interface Command {
	public double execute() throws BadArgumentException;
}
