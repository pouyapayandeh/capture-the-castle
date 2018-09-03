package client;

import client.commands.Command;
import framework.network.ClientSocket;
import framework.network.events.DataRecievedEvent;
import org.json.JSONObject;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by Pouya Payandeh on 10/24/2015.
 */
public class NetworkHandler implements Observer {
    ClientSocket socket;
    WorldModel wm;
    int phase = 0;
    boolean gameStarted=false;
    PlayerAI ai;
    public NetworkHandler(ClientSocket socket, WorldModel wm, PlayerAI ai) {
        this.socket = socket;
        this.wm=wm;
        this.ai=ai;
        socket.getDataRecievedEvent().addObserver(this);
    }
    public void doTurn(JSONObject data)
    {
        wm.update(data);
        ai.doTurn(wm);
        JSONObject responseData = new JSONObject();
        for(Unit u : wm.self.agents)
        {
            Command c = u.getCmd();
            if(c != null)
            {
                responseData.append("cmds",c);
            }
        }
        socket.response(responseData.toString());
    }
    @Override
    public void update(Observable o, Object arg) {
        if(o instanceof DataRecievedEvent)
        {
            Object[] args = (Object[])arg;
            String data = (String)args[1];
            System.out.println(args[1]);
            if(gameStarted)
            {
                doTurn(new JSONObject(data));
            }
            if(phase == 0)//Reading Terrain Data
            {
                wm.setTerrain(new JSONObject((data)));
                phase++;
            }else if(phase == 1)//Reading client.GoldMine Location
            {
                wm.setGoldMines(new JSONObject(data));
                phase++;
            }else if(phase == 2)//Reading Self info
            {
                wm.setSelf(new JSONObject(data));
                phase++;
                gameStarted=true;
            }
        }

    }
}
