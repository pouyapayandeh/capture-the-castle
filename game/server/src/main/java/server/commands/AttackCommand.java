package server.commands;

import framework.core.*;
import framework.core.math.Vector2D;
import server.agents.Unit;

import java.util.ArrayList;

/**
 * Created by Pouya Payandeh on 11/7/2015.
 */
public class AttackCommand extends BasicCommand
{
    Vector2D pos;
    public AttackCommand(GamePlayer player, GameAgent agent , Vector2D pos) {
        super(player, agent);
        this.pos=pos;
    }
    @Override
    public void doCommand(Game game) {
        TurnBaseGame tg = (TurnBaseGame)game;
        GameBoard board =tg.getBoard();
        if(checkPlayer(tg.currentPlayer()))
        {
            if(agent.getPos().getDistance(pos) <= 1 && board.getTerrainAt(pos)==0)
            {
                ArrayList<BoardObject>[][] objects =tg.getBoard().getObjects();
                Unit res = null;
                for(BoardObject obj : objects[pos.x][pos.y])
                {
                    if(obj instanceof Unit)
                        res = (Unit) obj;
                }
                if(res != null)
                {
                    res.HP -= ((Unit) agent).ATK;
                    if (res.HP <= 0)
                        game.removeAgent(res);
                }
            }
        }
    }
}
