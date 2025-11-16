package tp1.logic;

/**
 * Represents the allowed actions in the game
 *
 */
public enum Action {
	LEFT(-1,0), RIGHT(1,0), DOWN(0,1), UP(0,-1), STOP(0,0);	
	private int x;
	private int y;
	
	private Action(int x, int y) {
		this.x=x;
		this.y=y;
	}
	
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	
	public static Action parseAction(String word) {
		
		word = word.toLowerCase();
		switch(word) {
			
			case "r":
			case "right":{
				return Action.RIGHT;
			}
			
			case "l":
			case "left":{
				return Action.LEFT;
			}
			
			case "u":
			case "up":{
				return Action.UP;
			}
			
			case "d":
			case "down":{
				return Action.DOWN;
			}
			
			case "s":
			case "stop":{
				return Action.STOP;
			}
			
			default:
				return null;
		}
	}
}
