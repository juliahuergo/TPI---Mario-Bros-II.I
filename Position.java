package tp1.logic;


public class Position {

	private int col;
	private int row;

	public Position(int row, int col) {
		this.col = col;
		this.row = row;
	}
	
	
	public int getRow() {return this.row;}
	public int getCol() {return this.col;}

	public Position add(int toRow, int toCol) {
		return new Position(this.row + toRow, this.col + toCol);
	}
}
