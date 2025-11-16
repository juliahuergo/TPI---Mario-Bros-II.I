package tp1.logic.gameobjects;

import tp1.logic.Action;
import tp1.logic.GameWorld;
import tp1.logic.Position;

public abstract class GameObject implements GameItem { 

	protected Position pos;
	protected boolean dead;
	protected GameWorld game; 
	
	public GameObject(GameWorld game, Position pos) {
		this.dead = false;
		this.pos = pos;
		this.game = game;
	}
	

	public Position add(int toRow, int toCol) {
		return new Position(this.pos.getRow() + toRow, this.pos.getCol() + toCol);
	}
	
	public abstract boolean getBig();
	
	public boolean isInPosition(Position p) {
		if(this.isMario() && this.isAlive() && this.getBig()) {
			return p != null && 
					this.pos!= null && 
					((p.getRow() == this.pos.getRow() && p.getCol() == this.pos.getCol()) || (p.getRow() == this.pos.getRow() - 1 && p.getCol() == this.pos.getCol()));
		}
		return p != null && this.pos!= null && p.getRow() == this.pos.getRow() && p.getCol() == this.pos.getCol();
	}
 	
	public boolean isAlive() {
		return !dead;
	}
	
	public void die() {
		dead = true;
		if(this.isMario()) game.marioDies();
	}
	
	public boolean isDead() {
		return this.dead;
	}
	
	public abstract String getIcon();
	
	
	public abstract void update();
	
	public abstract int getDir();
	
	
	public int getRow() {
		return pos.getRow();
	}
	public int getCol() {
		return pos.getCol();
	}

	// Not mandatory but recommended
	protected abstract boolean move(Action dir); //boolean lo he puesto yo (estaba en void)
		
}
