package tp1.logic.gameobjects;

import tp1.logic.Action;
import tp1.logic.Game;
import tp1.logic.Position;
import tp1.view.Messages;

public class Land extends GameObject {
	private Position pos;
	public Land(Game game, Position pos) {
		super(game, pos);
	}
	
	@Override
	public boolean isSolid() {
		return true;
	}
	@Override
	public String getIcon() {
		return Messages.LAND;
	}
	
	public static String getIcon2() {
		return Messages.LAND;
	}
	
	
	@Override
	public boolean isLand() {
		return true;
	}
	


	@Override
	public boolean interactWith(GameItem other) {
		/*boolean canInteract = other.isInPosition(this.pos);
	     if (canInteract) {other.receiveInteraction(this);
	     }*/
	     return false;
	}
	

	@Override
	protected boolean move(Action dir) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public void update() { //NO VA A HACER NADA
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getDir() { //NO VA A HACER NADA
		// TODO Auto-generated method stub
		return -2;
	}
	
	public boolean getBig() {
		return false;
	}
}
