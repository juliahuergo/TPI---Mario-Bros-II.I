package tp1.control.commands;

import tp1.logic.GameModel;
import tp1.view.GameView;
//No tocar
public interface Command {
	
	//editar game y gameview para volver a dibujar estado o mostrar error
	public void execute(GameModel game, GameView view);	  
	public Command parse(String[] commandWords);
	public String helpText();
}

