package tp1.control.commands;

import tp1.logic.GameModel;
import tp1.view.GameView;
import tp1.view.Messages;

public class ResetCommand  extends AbstractCommand  {
	
	private static final String NAME = Messages.COMMAND_RESET_NAME;
    private static final String SHORTCUT = Messages.COMMAND_RESET_SHORTCUT;
    private static final String DETAILS = Messages.COMMAND_RESET_DETAILS;
    private static final String HELP = Messages.COMMAND_RESET_HELP;
    
    private int nLevel = -1; //anyadido por m  

    public ResetCommand() {
		super(NAME, SHORTCUT, DETAILS, HELP);
	}
    
    public ResetCommand(int Level) {
		super(NAME, SHORTCUT, DETAILS, HELP);
		this.nLevel = Level;
	}
	
	public Command parse(String[] commandWords) {
		if(matchCommandName(commandWords[0])) {
			if(commandWords.length >= 2) { //Reset con par metros
				try {
					int level = Integer.parseInt(commandWords[1]);
					return new ResetCommand(level); 
				}
				catch (NumberFormatException ex) {
					return null;
				}
			}
			else //Reset sin par metros
				return new ResetCommand(); //se mantiene en nivel actual
		}
		
		return null;
	}

	@Override
	public void execute(GameModel game, GameView view) {
		if(this.nLevel <= 2) {
			game.reset(this.nLevel);
			view.showGame();
		}
		else
			view.showError(Messages.INVALID_LEVEL_NUMBER);
		
	}
}

/*//Antes:
case "r":
case "reset":{
	Integer level = null; //Pq int no puede ser null y Integer s  (puede que no recibamos level)
	if(words.length >= 2) {
		try {
			level = Integer.parseInt(words[1]); //intenta convertir a un entero
		}
		catch (NumberFormatException ex) {
			view.showMessage("Error: Unknown command: " + rawline); //comando mal escrito
			break;
		}
	}
	game.reset(level);
	repaint = true;
	break;
}*/