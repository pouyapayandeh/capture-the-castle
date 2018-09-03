package client.commands;

import client.UnitType;
import framework.core.math.Vector2D;
import org.json.JSONObject;

/**
 * Created by Pouya Payandeh on 11/7/2015.
 */
public class MakeCommand implements Command
{
    int id;
    UnitType type;
    Vector2D pos;

    public MakeCommand(int id, Vector2D pos, UnitType type) {
        this.id = id;
        this.pos = pos;
        this.type = type;
    }

    @Override
    public String toJSONString() {
        JSONObject obj = new JSONObject();
        obj.put("cmd","make");
        obj.put("id",id);
        obj.put("pos",pos);
        obj.put("type",type.name());
        return obj.toString();
    }
}
