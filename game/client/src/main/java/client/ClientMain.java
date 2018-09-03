package client;

import com.beust.jcommander.JCommander;
import framework.network.ClientSocket;
import org.json.JSONObject;


/**
 * Created by Pouya Payandeh on 10/24/2015.
 */
public class ClientMain {
    public static void main(String[] args) {
        CommandLine jct = new CommandLine();
        new JCommander(jct,args);

        ClientSocket socket = new ClientSocket();
        WorldModel wm = new WorldModel();
        PlayerAI ai = new PlayerAI();
        NetworkHandler clientNetworkHandler = new NetworkHandler(socket,wm,ai);
        socket.connect(jct.host,jct.port.intValue());
        JSONObject object =new JSONObject();
        object.accumulate("name","Pouya");
        socket.response(object.toString());
    }
}
