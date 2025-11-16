package tp1.logic;
import java.util.ArrayList;
import java.util.Iterator;

public class ActionList implements Iterable<Action> {
    private final ArrayList<Action> actions = new ArrayList<>();

   
    @Override
    public Iterator<Action> iterator() {
        return actions.iterator();  // delegate to the internal list
    }
    
 // Contadores por eje
    private int hCount = 0;       // horizontales aceptadas (LEFT/RIGHT)
    private int vCount = 0;       // verticales aceptadas (UP/DOWN)

    // “Primera dirección” fijada por eje en este turno
    private Action firstH = null; // LEFT o RIGHT
    private Action firstV = null; // UP o DOWN
    
    
    public void add(Action a) { 
    	switch (a) {
        case LEFT:
        case RIGHT:
            if (firstH == null) firstH = a;      // fija la 1ª dirección horizontal
            if (firstH != a) return;             // opuesta → se ignora
            if (hCount >= 4) return;             // tope 4 por turno
            actions.add(a);
            hCount++;
            return;

        case UP:
        case DOWN:
            if (firstV == null) firstV = a;      // fija la 1ª dirección vertical
            if (firstV != a) return;             // opuesta → se ignora
            if (vCount >= 4) return;             // tope 4 por turno
            actions.add(a);
            vCount++;
            return;

        case STOP:
        default:
            // STOP (u otras que añadas) no afectan a las reglas de eje
            actions.add(a);
    }
    }
    public boolean isEmpty() { return actions.isEmpty(); }
    
    public void clear() {
        actions.clear();
        hCount = vCount = 0;
        firstH = firstV = null;
    }
    
    
}


