package tp1.control.commands;


import tp1.logic.Action;
import tp1.logic.ActionList;
import tp1.logic.GameModel;
import tp1.view.GameView;
import tp1.view.Messages;

public class ActionCommand extends AbstractCommand {
	private static final String NAME = Messages.COMMAND_ACTION_NAME;
    private static final String SHORTCUT = Messages.COMMAND_ACTION_SHORTCUT;
    private static final String DETAILS = Messages.COMMAND_ACTION_DETAILS;
    private static final String HELP = Messages.COMMAND_ACTION_HELP;
    

    public ActionCommand() {
		super(NAME, SHORTCUT, DETAILS, HELP);
		actions = new ActionList();
	}
    
    private ActionList actions;
    
    public void addAction(Action a) {
    	this.actions.add(a);
    }
    
    public Command parse(String[] commandWords) {
    	if(commandWords.length >= 2 && matchCommandName(commandWords[0])) {
    		ActionCommand resul = new ActionCommand();
    		for(int i= 1; i < commandWords.length; i++) {
				Action a = Action.parseAction(commandWords[i]);
				if(a != null) {
					resul.addAction(a);
				}
				/*else {
					view.showMessage(String.format(Messages.ERROR, String.format(Messages.UNKNOWN_ACTION, words[i])));
				}*/
			} 
    		actions.clear(); //establecer contadores de acciones a 0
    		return resul;
    	}
    	//view.showMessage(String.format(Messages.ERROR, Messages.COMMAND_INCORRECT_PARAMETER_NUMBER));
    	return null;
    }
    
    @Override
	public void execute(GameModel game, GameView view) {
	    boolean movido = false;
	    

	    for (Action a : actions) {
	        movido = game.move(a) || movido;
	    }
    	game.update(movido);
    	game.reduceTime();
	    view.showGame();
	}

}