package tp1.logic.gameobjects;

import tp1.logic.Game;
import tp1.logic.Position;

public abstract class MovingObject extends GameObject {
	
	protected int actiondir;
	
	
	protected boolean falling = false;
	protected boolean stopped = false;
	protected boolean semistopped = false; //hit a wall and changed directions
	
	public MovingObject(Game game, Position pos) {
		super(game, pos);
		if(this.isMario()) {
			this.actiondir = 1;
		}
		else
			this.actiondir = -1;
	}
	
    protected void onDirectionChanged(int newDir) {}
	
	public int getDir() {
		return actiondir;
	}
	
	
	//IMPLEMENTAR MOV BASICO DE GOOMBA Y MARIO
	/*public void update() {
	    
		if (!stopped) {
	    	//MOVIMIENTO AUTOMATICO
	    	this.falling = false;
			Position below = this.add(1, 0);
			if(this.pos.getRow()==game.DIM_Y-1) {
            	this.die();
            	return; 
        	}
			
			
			if (!Land.getIcon2().equals(game.positionToString(below.getCol(), below.getRow()))){
				//COLISIONES
				this.falling = true;
				this.pos = below;
				return;
			}
			
			//Hay suelo y no se sale del tablero -> movimientos
			Position movido = this.add(0, this.actiondir); //primero probamos a la derecha
			if(!game.dentroDeTablero(movido)|| Land.getIcon2().equals(game.positionToString(movido.getCol(), movido.getRow())) || movido.getCol()==game.DIM_X) {
				//Si se sale del tablero por la derecha o se choca con algo s lido -> cambio de sentido a la izda
				this.actiondir *= -1;
				onDirectionChanged(this.actiondir); 
				
			}
			else {
				//No se sale ni se choca
				this.pos = movido;
			}
			
			game.doInteraction(this);
	    } 
	    else
	    	game.doInteraction(this);
		
	}*/
	
	
	public void update() {
	    if (!stopped) {
	        this.falling = false;
	        Position below = this.add(1, 0);
	        
	        //Se sale del tablero --> muere
	        if(this.pos.getRow() == game.DIM_Y-1) {
	            this.die();
	            return; 
	        }
	        
	        //No hay tierra debajo --> cae una casilla, se mira interacción y SE SALE
	        if (!Land.getIcon2().equals(game.positionToString(below.getCol(), below.getRow()))){
	            this.falling = true;
	            this.pos = below;
	            game.doInteraction(this);
	            return;
	        }
	        
	        // SOLO UN MOVIMIENTO POR CICLO - EVITAR MOVERSE MÚLTIPLES VECES
	        Position movido = this.add(0, this.actiondir);
	        //Se puede mover si seguiría dentro del tablero y no se chocaría con tierra
	        boolean canMove = game.dentroDeTablero(movido) && 
	                         movido.getCol() >= 0 && movido.getCol() < game.DIM_X &&
	                         !Land.getIcon2().equals(game.positionToString(movido.getCol(), movido.getRow()));
	        
	        //Si no se puede mover --> cambio de dirección                 
	        if (!canMove) {
	            this.actiondir *= -1;
	            onDirectionChanged(this.actiondir);
	            this.semistopped = true;
	        } 
	        //Si se puede mover --> se mueve
	        else {
	            // MOVERSE SOLO UNA VEZ
	            this.pos = movido;
	            this.semistopped = false;
	        }
	        
	        //Se mira interacción tras (falta de) movimiento
	        game.doInteraction(this);
	    } 
	}

}
