package framework.core;

/**
 * GameAgent represents Part of the game that is moved by owner GamePlayer
 * Created by Pouya Payandeh on 10/11/2015.
 */
public class GameAgent extends BoardObject {
    public int getAgentId() {
        return agentId;
    }

    protected int agentId;
    GamePlayer owner;

    public GamePlayer getOwner() {
        return owner;
    }
}
