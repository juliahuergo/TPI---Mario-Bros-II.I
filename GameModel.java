package tp1.logic;

//para Controller

public interface GameModel {
	public boolean isFinished();
	public int numLives();
	public void reduceTime();
	public void reset(Integer level);
	public void exit();
	public boolean move(Action a);
	public void update(boolean move);
	public boolean justReset();
	public void setReset(boolean reset);
}
