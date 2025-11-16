package tp1.control.commands;

import tp1.logic.GameModel;
import tp1.view.GameView;
import tp1.view.Messages;

public class HelpCommand extends NoParamsCommand {

    private static final String NAME = Messages.COMMAND_HELP_NAME;
    private static final String SHORTCUT = Messages.COMMAND_HELP_SHORTCUT;
    private static final String DETAILS = Messages.COMMAND_HELP_DETAILS;
    private static final String HELP = Messages.COMMAND_HELP_HELP;

    public HelpCommand() {
		super(NAME, SHORTCUT, DETAILS, HELP);
	}
    
	
	@Override
	public void execute(GameModel game, GameView view) {
		view.showMessage(CommandGenerator.commandHelp());
		//view.showMessage(Messages.HELP);
	}

}

/*//Antes:
case "h":
case "help": {
	if(words.length > 1)
		view.showMessage(String.format(Messages.ERROR, Messages.COMMAND_INCORRECT_PARAMETER_NUMBER));
	else
		view.showMessage(Messages.HELP);
	break;
}*/
