package client.commands;

import framework.core.math.Vector2D;
import org.json.JSONObject;

/**
 * Created by Pouya Payandeh on 11/7/2015.
 */
public class AttackCommand implements Command
{
    int id;
    Vector2D pos;

    public AttackCommand(int id, Vector2D pos) {
        this.id = id;
        this.pos = pos;
    }

    @Override
    public String toJSONString() {
        JSONObject obj = new JSONObject();
        obj.put("cmd","attack");
        obj.put("id",id);
        obj.put("pos",pos);
        return obj.toString();
    }
}
