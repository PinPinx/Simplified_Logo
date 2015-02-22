package parser.commands;

import Exceptions.BadArgumentException;

public interface Command {
	public void execute() throws BadArgumentException;
}
