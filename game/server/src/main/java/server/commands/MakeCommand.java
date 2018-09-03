package server.commands;

import framework.core.*;
import framework.core.math.Vector2D;
import server.JBPlayer;
import server.Settings;
import server.agents.Castle;
import server.agents.Unit;
import server.agents.Warrior;
import server.agents.Worker;

import java.util.ArrayList;

/**
 * Created by Pouya Payandeh on 11/7/2015.
 */
public class MakeCommand extends BasicCommand
{
    Vector2D pos;
    String type;
    public MakeCommand(GamePlayer player, GameAgent agent , Vector2D pos , String type) {
        super(player, agent);
        this.pos=pos;
        this.type=type;
    }
    @Override
    public void doCommand(Game game) {
        TurnBaseGame tg = (TurnBaseGame)game;
        GameBoard board =tg.getBoard();
        if(checkPlayer(tg.currentPlayer()))
        {
            if(agent.getPos().getDistance(pos) <= 1 && board.getTerrainAt(pos)==0 && agent instanceof Castle)
            {
                ArrayList<BoardObject>[][] objects =tg.getBoard().getObjects();
                boolean res = false;
                for(BoardObject obj : objects[pos.x][pos.y])
                {
                    if(obj instanceof Unit)
                        res = true;
                }
                if(!res)
                {
                    if(type.equals ("WARRIOR") )
                    {
                        game.addAgents(tg.currentPlayer(), new Warrior(pos));
                        ((JBPlayer)tg.currentPlayer()).gold-= Settings.WarriorCost;
                    }
                    else if (type.equals("WORKER"))
                    {

                            game.addAgents(tg.currentPlayer(), new Worker(pos));
                        ((JBPlayer)tg.currentPlayer()).gold-= Settings.WorkerCost;
                    }
                }
            }
        }
    }
}
