package tp1.logic;

import java.util.ArrayList;
import java.util.List;

import tp1.logic.gameobjects.GameItem;
import tp1.logic.gameobjects.GameObject;
import tp1.view.Messages;

public class GameObjectContainer {
	private List<GameObject> gameObjects;
	private boolean game_over = false; //sobra yo creo

	public GameObjectContainer() {
		gameObjects = new ArrayList<GameObject>();
	}
	
	
	
	public void setGameOver(boolean value) { //sobra yo creo
		this.game_over = value;
	}
	
	// Only one add method (polymorphism)
	public void add(GameObject object) {
		this.gameObjects.add(object);
	}
	
	public String postitionToString(Position pos) {
		StringBuilder resul = new StringBuilder();
		for(GameObject g: gameObjects) {
			if(g.isInPosition(pos) && !g.isDead())
				resul.append(g.getIcon());
		}
		
		String resul2 = resul.toString();
		if(resul2.length() == 0)
			return Messages.EMPTY;
		return resul2;
	}
	
	public void update(boolean movido) {

	    for (int i = gameObjects.size() - 1; i >= 0; i--) {
	        GameObject object = gameObjects.get(i);

	        if (object.isMario() && object.isAlive()) {

	            if (!movido) {
	                object.update();
	            }

	            if (object.isAlive()) {
	                doInteraction(object);
	            }
	        }
	    }

	    for (int i = gameObjects.size() - 1; i >= 0; i--) {
	        GameObject object = gameObjects.get(i);

	        if (object.isGoomba() && object.isAlive()) {

	            object.update();

	            if (object.isAlive()) {
	                doInteraction(object);
	            }
	        }
	    }
	    
	    
	    clean();
	}


	
	public void doInteraction(GameItem other) {
		if(!other.isAlive()) return;
		for(GameObject obj: gameObjects) {
			if(obj.isAlive() && obj != other) {
				if(obj.interactWith(other)) {
					other.interactWith(obj);
				}
			}
		}
	}
	
	public void clean() {
		gameObjects.removeIf(o -> !o.isAlive());
	}
}


