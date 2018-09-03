package server.agents;

import framework.core.GameAgent;
import framework.core.math.Vector2D;
import org.json.JSONObject;
import org.json.JSONString;

/**
 * Created by Pouya Payandeh on 10/24/2015.
 */
public class Unit extends GameAgent implements JSONString {
    public int HP;
    public int ATK;
    public final int MaxHP;
    public int movePerTurn;

    public String getType() {
        return type;
    }

    protected final String type;

    public Unit(int maxHP, String type) {
        MaxHP = maxHP;
        this.type=type;
    }

    public Unit(Vector2D pos, String type, int HP, int ATK, int maxHP) {
        MaxHP = maxHP;
        setPosition(pos.clone());
        this.type=type;
        this.HP = HP;
        this.ATK = ATK;
    }

    public String toJSONString()
    {
        JSONObject obj = new JSONObject();
        obj.accumulate("type", this.type);
        obj.accumulate("id",agentId);
        obj.accumulate("owner",getOwner().getPlayerId());
        obj.accumulate("pos", this.getPos());
        obj.accumulate("HP",HP);
        obj.accumulate("ATK", ATK);
        return  obj.toString();
    }
}
