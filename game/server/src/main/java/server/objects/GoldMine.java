package server.objects;

import framework.core.BoardObject;
import org.json.JSONObject;
import org.json.JSONString;

/**
 * Created by Pouya Payandeh on 10/24/2015.
 */
public class GoldMine extends BoardObject implements JSONString {
    int goldAmount;

    public GoldMine(int goldAmount,int x,int y) {
        setPosition(x,y);
        this.goldAmount = goldAmount;
    }

    @Override
    public String toJSONString() {
        JSONObject obj = new JSONObject();
        obj.put("goldamount",goldAmount);
        obj.put("pos",getPos());
        return obj.toString();
    }
}
