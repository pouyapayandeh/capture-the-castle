package framework.core;

import java.util.ArrayList;

/**
 * GamePlayer are the literally Players , Including AI Players (Like the Ghost in Pacman)
 * They own server.agents
 * Created by Pouya Payandeh on 10/11/2015.
 */

public class GamePlayer {
   protected ArrayList<GameAgent> agents;

    public ArrayList<GameAgent> getAgents() {
        return agents;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    String name;
    int playerId;

    public GamePlayer() {
        agents=new ArrayList<GameAgent>();
    }

    public int getPlayerId() {
        return playerId;
    }
    int score;

}
