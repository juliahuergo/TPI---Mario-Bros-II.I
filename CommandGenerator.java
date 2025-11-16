package tp1.control.commands;

import java.util.Arrays;
import java.util.List;

import tp1.view.Messages;

public class CommandGenerator {

	private static final List<Command> AVAILABLE_COMMANDS = Arrays.asList(
	    new ActionCommand(),
	    new UpdateCommand(),
	    new ResetCommand(),
	    new HelpCommand(),
	    new ExitCommand()
	);
	
	

	public static Command parse(String[] commandWords) {		
		for (Command c: AVAILABLE_COMMANDS) {
			if(commandWords.length == 0 ||
			  (commandWords.length == 1 && commandWords[0].isBlank()))
				return new UpdateCommand();
			
			Command parsed = c.parse(commandWords);
			if (parsed != null) 
				return parsed; 
		}
		return null;
	}
		
	public static String commandHelp() {
		StringBuilder commands = new StringBuilder();
		
		commands.append(Messages.HELP_AVAILABLE_COMMANDS).append(Messages.LINE_SEPARATOR);
		
		for (Command c: AVAILABLE_COMMANDS) {
			//invoca al helpText() de cada subclase de Command
			commands.append(c.helpText());
		}
		
		return commands.toString();
	}

}
