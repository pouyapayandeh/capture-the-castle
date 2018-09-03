package framework.core.events;

import java.util.Observable;

/**
 * Created by Pouya Payandeh on 10/24/2015.
 */
public class GameStartEvent extends Observable {
    @Override
    public void notifyObservers(Object arg) {
        setChanged();
        super.notifyObservers(arg);
    }
}
