package tp1.control.commands;

import tp1.logic.GameModel;
import tp1.view.GameView;
import tp1.view.Messages;

public class ExitCommand extends NoParamsCommand{

	// Forman parte de atributos de estado
	private static final String NAME = Messages.COMMAND_EXIT_NAME;
	private static final String SHORTCUT = Messages.COMMAND_EXIT_SHORTCUT;
	private static final String DETAILS = Messages.COMMAND_EXIT_DETAILS;
	private static final String HELP = Messages.COMMAND_EXIT_HELP;

	public ExitCommand() {
		super(NAME, SHORTCUT, DETAILS, HELP); 
	}
	

	@Override
	public void execute(GameModel game, GameView view){
	    game.exit(); 	
	}

}

/*//Antes:
case "e":
case "exit":{
	if(words.length > 1)
		view.showMessage(String.format(Messages.ERROR, Messages.COMMAND_INCORRECT_PARAMETER_NUMBER));
	
	else {
		running = false;}
	
	break; //sale del switch y al llegar a while(running) termina
}*/