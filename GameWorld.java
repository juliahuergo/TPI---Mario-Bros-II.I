package tp1.logic;

import tp1.logic.gameobjects.GameItem;

//Para GameObject

public interface GameWorld {

	int DIM_Y = 15;
	int DIM_X = 30;

	public void marioExited();

	public void addPoints(int i);

	public void marioDies();
	
	public void minusLives();
	
	public void reset(Integer level);

	public void doInteraction(GameItem movingObject);

	public boolean dentroDeTablero(Position movido);

	public Object positionToString(int col, int row);

	public void setReset(boolean b);

}
