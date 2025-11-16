package tp1.logic.gameobjects;


import tp1.logic.Action;
import tp1.logic.Game;
import tp1.logic.Position;
import tp1.view.Messages;

public class Goomba extends MovingObject {
	
	
	public Goomba(Game game, Position pos) {
		super(game, pos);
	}
	


	@Override
	public String getIcon() { 
		if(isDead()) {
			return "";
		}
		return Messages.GOOMBA;
	}
	
	@Override
	public boolean isGoomba() {
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
	
	public boolean receiveInteraction(Mario other) {
        if (other == null || this.isDead()) return false;
        if(other.getBig() && other.getStopped() && !other.getFalling())
        	other.changeBig(false);
        
        if(other.getBig() && other.getSemiStopped() && !other.getFalling())
        	other.changeBig(false);
        
        this.die();
        game.addPoints(100); 
        
        return true;
	}

	public boolean getBig() {
		return false;
	}

	@Override
	protected boolean move(Action dir) {
		return false;
	}
	
	
}
