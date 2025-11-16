package tp1.logic.gameobjects;
import tp1.logic.Action;
import tp1.logic.Game;
//SOLO PUEDE HABER UNA EXITDOOR EN TODO EL JUEGO
import tp1.logic.Position;
import tp1.view.Messages;
public class ExitDoor extends GameObject {
	// If you can, make it private.
	
	public ExitDoor(Game game, Position pos) {
		super(game, pos);
	}
	
	
	@Override
	public String getIcon() { //antes static
		return Messages.EXIT_DOOR;
	}
	

	@Override
	public boolean isExitDoor() {
		return true;
	}
	

	@Override
	public boolean interactWith(GameItem other) {
		if(!other.isAlive()) return false;
		boolean canInteract = other.isInPosition(this.pos);
	     if (canInteract) {
	    	 other.receiveInteraction(this);
	     }
	     return canInteract;
	}

	
	
	@Override
	public boolean receiveInteraction(Mario obj) {
		game.marioExited(); 
		return true;
	}
	
	
	@Override
	protected boolean move(Action dir) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public void update() { 
		// TODO Auto-generated method stub
		
	}
	@Override
	public int getDir() { //NO VA A HACER NADA 
		// TODO Auto-generated method stub
		return 0;
	}
	
	public boolean getBig() {
		return false;
	}

}