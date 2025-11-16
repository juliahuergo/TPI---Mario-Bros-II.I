package tp1.logic.gameobjects;

import tp1.logic.Game;
import tp1.logic.Position;
import tp1.view.Messages;
import tp1.logic.Action;
import tp1.logic.ActionList;

public class Mario extends MovingObject  {
	
	private boolean big = true;
	private int dirCol = 1;
	private ActionList actions;
	private boolean movedByActions = false;
	
	
	public Mario(Game game, Position pos) {
		// TODO Auto-generated constructor stub
		super(game, pos);
		this.actions  = new ActionList();
	}
	
	
	@Override
	public boolean isMario() {
		return true;
	}
	
	public boolean getBig() {
		return this.big;
	}
	
	public void changeBig(boolean value) {
		this.big = value;
	}
	
	public boolean getStopped() {
		return this.stopped;
	}
	
	public boolean getSemiStopped() {
		return this.semistopped;
	}
	
	
	public boolean getFalling() {
		return this.falling;
	}
	
	
	@Override
	public String getIcon() {
		if(this.dirCol == 1) return Messages.MARIO_RIGHT;
		else if (this.dirCol == -1) return Messages.MARIO_LEFT;
		else return Messages.MARIO_STOP;
	}
	
	// Comprueba si Mario puede poner los PIES en newFeet.
	// Si big==true, tambi n verifica la celda de arriba (cabeza).
	private boolean canOccupy(Position newFeet) {
	    // pies dentro y no hay Land
	    if (!game.dentroDeTablero(newFeet)) return false;
	    if (Land.getIcon2().equals(game.positionToString(newFeet.getCol(), newFeet.getRow())))
	        return false;

	    if (this.big) {
	        Position head = new Position(newFeet.getRow() - 1, newFeet.getCol());
	        if (!game.dentroDeTablero(head)) return false;
	        if (Land.getIcon2().equals(game.positionToString(head.getCol(), head.getRow())))
	            return false;
	    }
	    return true;
	}
	
	public void add(Action act) {
		actions.add(act);
	}
	
    protected void onDirectionChanged(int newDir) {
        this.dirCol = newDir; 
    }
	
	
	//Versi n anterior
	public boolean move(Action a) {
		
		final int startRow = this.pos.getRow();
		final int startCol = this.pos.getCol();
		
		int oldDirCol = this.dirCol;
		
	    switch (a) {
	        case LEFT: {
	        	this.stopped = false;
	        	this.falling = false;
        		this.dirCol = -1; // icono mirando a la izda (si aplicas)
	            Position next = this.pos.add(0, a.getX()); // X = -1
	            if (canOccupy(next)) {
	                this.pos = next;
	            }
	            else
	            	dirCol = 1;
		            
	        	
	        	game.doInteraction(this);
	            break;
	        }

	        case RIGHT: {
	        	this.stopped = false;
	        	this.falling = false;
        		this.dirCol = 1;
	            Position next = this.pos.add(0, a.getX()); // X = +1
	            if (canOccupy(next)) {
	                this.pos = next;
	            }
	            else {
	            	dirCol = -1;
	            	//this.stopped = false; 
	            }
	            game.doInteraction(this);
	            break;
	        }

	        case UP: {
	        	this.stopped = false;
	        	this.falling = false;
        		Position up = this.pos.add(a.getY(), 0); // Y = -1
	            if (canOccupy(up)) {
	                this.pos = up;
	            }
	            game.doInteraction(this);
	            break;
	        }

	        case DOWN: {
	        	this.stopped = false;
                this.falling = true;
        		Position down = this.pos.add(a.getY(), 0); // Y = +1
	            if (canOccupy(down)) {
	                while (true) {
	                    Position fall = this.pos.add(1, 0);
	                    if(!canOccupy(fall)) {
	                    	break;
	                    }
	                    this.pos = fall;
	                    if(this.pos.getRow()==game.DIM_Y-1) {
	                    	this.die();
	                    	break;
                    	}
	                    
	                }
		        } 
	            else {
	                this.dirCol = 0;
	                this.stopped = true; 
	                this.falling = false; 	                
	                
	                break;
	            }
	            game.doInteraction(this);
	            break;
	        }

	        case STOP: {
	            this.dirCol = 0;
	            this.stopped = true;
	            this.falling = false;   
	            game.doInteraction(this);
	            break;
	        }

	        default:
	            break;
	            
		   }

		if(oldDirCol == -1 && this.dirCol != -1)
			this.actiondir = 1;
		
		this.movedByActions = (this.pos.getRow() != startRow) || (this.pos.getCol() != startCol) || this.stopped;

	    return this.movedByActions;
	    
	}

	@Override
	public boolean interactWith(GameItem other) {
		if(!other.isAlive() || !this.isAlive()) return false;
		boolean canInteract = other.isInPosition(this.pos) || (this.big && other.isInPosition(this.pos.add(-1, 0)));
	     if (canInteract) {
	    	 other.receiveInteraction(this);
	     }
	     return canInteract;
	}

	

	@Override
	public boolean receiveInteraction(ExitDoor obj) {
		if(!obj.isAlive()) return false;
		this.game.marioExited();
		return true;
	}

	@Override
	public boolean receiveInteraction(Goomba obj) {		
		if(obj.isDead() || this.isDead()) return false;
		
		if(this.falling)
			obj.receiveInteraction(this);
		else {
			if(this.big || this.stopped) {
				changeBig(false);
				obj.receiveInteraction(this);
			}
			else {
				game.minusLives();
				obj.receiveInteraction(this);
				game.reset(null);
				game.setReset(true); 
			}
		}
		return true;
	}
	
	
}