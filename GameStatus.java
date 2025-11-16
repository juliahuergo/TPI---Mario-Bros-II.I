package tp1.logic;

//para GameView

public interface GameStatus {

	public String positionToString(int col, int row);
	//TODO fill your code

	public boolean playerWins();

	public boolean playerLoses();

	public int remainingTime();

	public int points();

	public int numLives();

}
