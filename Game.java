package tp1.logic;

import tp1.view.ConsoleView;
import tp1.logic.gameobjects.*;

public class Game implements GameStatus, GameWorld, GameModel {

	public static final int DIM_X = 30;
	public static final int DIM_Y = 15;
	
	private String tablero[][];
	
	private int remainingTime;
	private int points;
	private int lives;
	private int nLevel = 0;
	private Mario mario;
	private boolean victory;
	private boolean finished;
	private boolean justReset;
	
	private GameObjectContainer gameObjects;
	private ConsoleView view;
	

	public Game(int nLevel) {
		this.nLevel = nLevel;
		this.remainingTime = 100;
		this.points = 0; 
		this.lives = 3;
		this.gameObjects = new GameObjectContainer(); 
		if(nLevel == 0)
			initLevel0();
		else
			initLevel1();
		this.tablero = new String[DIM_Y][DIM_X];
		this.victory = false;
		this.view = new ConsoleView(this);
		this.finished = false;
		this.justReset = false;
	}
	
	//SETTERS Y GETTERS
	public void setTime(int time) {
		this.remainingTime = time;
	}
	
	
	public void minusLives() {
		this.lives--;
	}
	
	public String positionToString(int col, int row) {
		/*ahora:
		 * Position pos = new Position(row, col);
			return this.gameObjects.postitionToString(pos);*/
		
		Position aux = new Position(row, col);
		return gameObjects.postitionToString(aux);
	}

	public boolean playerWins() {
		return this.victory;
	}
	
	public boolean justReset() {
		return this.justReset;
	}
	
	public void setReset(boolean reset) {
		this.justReset = reset;
	}
	
	public boolean playerLoses() {
		return (numLives() <= 0);
	}

	public int remainingTime() {
		return this.remainingTime;
	}
	
	public void reduceTime() {
		this.remainingTime--;
	}

	public int points() {
		return this.points;
	}

	public int numLives() {
		return this.lives;
	}
	
	public void marioExited() {
		this.points += ((this.remainingTime - 1) * 10);
		this.remainingTime = 1;
		this.victory = true;
	}

	@Override
	public String toString() {
		return "TODO: Hola soy el game";
	}
	
	//Resetea en su propio nivel
	public void reset(Integer level) {
		int keepPoints = this.points;
		int keepLives = this.lives;
		
		if(level != null && level >= 0) {
			this.nLevel = level;
		}
		
		if(this.nLevel == 0)
			initLevel0();
		else
			initLevel1();
		
		this.points = keepPoints;
		this.lives = keepLives;
	}
	
	
	
	public void update(boolean movido) {
		if(this.remainingTime > 0) {
			this.gameObjects.update(movido);
		}
	}
	
	
	public void addPoints(int n) {
		this.points += n;
	}
	
	public void marioDies() {
		this.lives--;
		if(this.lives > 0) {
			this.justReset = true;
			this.reset(this.nLevel);
		}
		else {
			gameObjects.setGameOver(true);
			gameObjects.clean();
			exit();
		}
	}
	
	//Nuevo m todo
	public boolean isFinished() {
		return this.finished || this.victory || this.playerLoses() || this.remainingTime <= 0;
	}

	//Nuevo m todo
	// Not mandatory but recommended
	public void exit() {
		// TODO Auto-generated method stub
		this.finished = true;
	}
	
	public boolean dentroDeTablero(Position pos) {
		return pos.getCol() >= 0 && pos.getCol() < DIM_X && pos.getRow() >= 0 && pos.getRow() < DIM_Y;
	}
	
	public void addAction(Action act) {
		if(act != null && this.mario != null)
			this.mario.add(act);
	}
	
	/*public void doInteractionsFrom(Mario mario) {
	    this.gameObjects.doInteractionsFrom(mario);
	}*/
	
	public void doInteraction(GameItem other) {
		gameObjects.doInteraction(other);
		gameObjects.clean();
	}
	

	
	public boolean move(Action a) {
		return this.mario.move(a);
	}
	
	private void initLevel0() {
		this.nLevel = 0;
		this.remainingTime = 100;
		
		this.gameObjects = new GameObjectContainer();
		
		// 1. Mapa
		for(int col = 0; col < 15; col++) {
			gameObjects.add(new Land(this, new Position(13,col)));
			gameObjects.add(new Land(this, new Position(14,col)));		
		}

		gameObjects.add(new Land(this, new Position(Game.DIM_Y-3,9)));
		gameObjects.add(new Land(this, new Position(Game.DIM_Y-3,12)));
		for(int col = 17; col < Game.DIM_X; col++) {
			gameObjects.add(new Land(this, new Position(Game.DIM_Y-2, col)));
			gameObjects.add(new Land(this, new Position(Game.DIM_Y-1, col)));		
		}

		gameObjects.add(new Land(this, new Position(9,2)));
		gameObjects.add(new Land(this, new Position(9,5)));
		gameObjects.add(new Land(this, new Position(9,6)));
		gameObjects.add(new Land(this, new Position(9,7)));
		gameObjects.add(new Land(this, new Position(5,6)));
		
		// Salto final
		int tamX = 8, tamY= 8;
		int posIniX = Game.DIM_X-3-tamX, posIniY = Game.DIM_Y-3;
		
		for(int col = 0; col < tamX; col++) {
			for (int fila = 0; fila < col+1; fila++) {
				gameObjects.add(new Land(this, new Position(posIniY- fila, posIniX+ col)));
			}
		}
		
		gameObjects.add(new ExitDoor(this, new Position(Game.DIM_Y-3, Game.DIM_X-1)));

		// 3. Personajes
		this.mario = new Mario(this, new Position(Game.DIM_Y-3, 0));
		gameObjects.add(this.mario);
		
		gameObjects.add(new Goomba(this, new Position(0, 19)));
	}
	
	
	private void initLevel1() {
		this.nLevel = 1;
		this.remainingTime = 100;
		
		this.gameObjects = new GameObjectContainer();
		
		// 1. Mapa
		for(int col = 0; col < 15; col++) {
			gameObjects.add(new Land(this, new Position(13,col)));
			gameObjects.add(new Land(this, new Position(14,col)));		
		}

		gameObjects.add(new Land(this, new Position(Game.DIM_Y-3,9)));
		gameObjects.add(new Land(this, new Position(Game.DIM_Y-3,12)));
		for(int col = 17; col < Game.DIM_X; col++) {
			gameObjects.add(new Land(this, new Position(Game.DIM_Y-2, col)));
			gameObjects.add(new Land(this, new Position(Game.DIM_Y-1, col)));		
		}

		gameObjects.add(new Land(this, new Position(9,2)));
		gameObjects.add(new Land(this, new Position(9,5)));
		gameObjects.add(new Land(this, new Position(9,6)));
		gameObjects.add(new Land(this, new Position(9,7)));
		gameObjects.add(new Land(this, new Position(5,6)));
		
		// Salto final
		int tamX = 8, tamY= 8;
		int posIniX = Game.DIM_X-3-tamX, posIniY = Game.DIM_Y-3;
		
		for(int col = 0; col < tamX; col++) {
			for (int fila = 0; fila < col+1; fila++) {
				gameObjects.add(new Land(this, new Position(posIniY- fila, posIniX+ col)));
			}
		}
		
		gameObjects.add(new ExitDoor(this, new Position(Game.DIM_Y-3, Game.DIM_X-1)));

		// 3. Personajes
		this.mario = new Mario(this, new Position(Game.DIM_Y-3, 0));
		gameObjects.add(this.mario);
		
		gameObjects.add(new Goomba(this, new Position(0, 19)));
		gameObjects.add(new Goomba(this, new Position(4, 6)));
		gameObjects.add(new Goomba(this, new Position(12, 6)));
		gameObjects.add(new Goomba(this, new Position(12, 8)));
		gameObjects.add(new Goomba(this, new Position(10, 10)));
		gameObjects.add(new Goomba(this, new Position(12, 11)));
		gameObjects.add(new Goomba(this, new Position(12, 14)));
		
	}



	
}
