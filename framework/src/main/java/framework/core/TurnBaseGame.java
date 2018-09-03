package framework.core;

/**
 * Created by Pouya Payandeh on 10/24/2015.
 */
public abstract class TurnBaseGame extends Game {
    public abstract  GamePlayer currentPlayer();
    public abstract void doTurn();
}
