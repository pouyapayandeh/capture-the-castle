package server;

import framework.core.GamePlayer;
import framework.network.ClientSocket;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONString;

/**
 * Created by Pouya Payandeh on 10/24/2015.
 */
public class JBPlayer extends GamePlayer implements JSONString {
    public int gold;
    long lastMoveTime;
    public void setSocket(ClientSocket socket) {
        this.socket = socket;
    }

    ClientSocket socket;

    @Override
    public String toJSONString() {
        JSONObject object = new JSONObject();
        object.put("PlayerId", getPlayerId());
        object.put("name", getName());
        object.put("gold",gold);
        JSONArray array = new JSONArray();
        array.put(agents);
        object.append("Agents",array);
        return  object.toString();
    }
}
