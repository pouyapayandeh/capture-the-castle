package client;

import framework.core.math.Vector2D;
import org.json.JSONObject;

/**
 * Created by Pouya Payandeh on 10/24/2015.
 */
public class GoldMine
{
    Vector2D pos;
    int goldAmount;

    public GoldMine(Vector2D pos, int goldAmount) {
        this.pos = pos;
        this.goldAmount = goldAmount;
    }
    public GoldMine(JSONObject data)
    {
        goldAmount = (int) data.get("goldamount");
        pos = new Vector2D((Integer) data.getJSONObject("pos").get("x"),(Integer) data.getJSONObject("pos").get("y"));
    }
}
