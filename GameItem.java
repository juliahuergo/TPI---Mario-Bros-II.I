package tp1.logic.gameobjects;

import tp1.logic.Position;

public interface GameItem {
	public  boolean isAlive();
	public  boolean isInPosition(Position pos);

	public  boolean interactWith(GameItem item);

	public default boolean receiveInteraction(Land obj) {
		return false;
	}
	public default boolean receiveInteraction(ExitDoor obj) {
		return false;
	}
	public default boolean receiveInteraction(Mario obj) {
		return false;
	}
	public default boolean receiveInteraction(Goomba obj) {
		return false;
	}
	
	public default boolean isSolid() {
		return false;
	}
	
	public default boolean isGoomba() {
		return false;
	}
	
	public default boolean isLand() {
		return false;
	}
	
	public default boolean isExitDoor() {
		return false;
	}
	
	public default boolean isMario() {
		return false;
	}	
}
