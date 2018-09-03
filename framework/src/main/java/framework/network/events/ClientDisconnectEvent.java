package framework.network.events;

import java.util.Observable;

/**
 * Created by Pouya Payandeh on 11/5/2015.
 */
public class ClientDisconnectEvent extends Observable
{
    @Override
    public void notifyObservers(Object arg) {
        setChanged();
        super.notifyObservers(arg);
    }
}
