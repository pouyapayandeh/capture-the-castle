package server;

import framework.core.GameAgent;
import framework.core.events.GameStartEvent;
import framework.core.math.Vector2D;
import framework.network.ClientSocket;
import framework.network.GameServer;
import framework.network.events.ClientConnectEvent;
import framework.network.events.ClientDisconnectEvent;
import framework.network.events.DataRecievedEvent;

import server.commands.AttackCommand;
import server.commands.BasicCommand;
import server.commands.MakeCommand;
import server.commands.MoveCommand;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by Pouya Payandeh on 10/24/2015.
 */
public class NetworkHandler implements Observer{
    JBattleGame game;
    GameServer server;
    Timer changeTurnTimer;
    public NetworkHandler(JBattleGame game, GameServer server) {
        this.game = game;
        this.server = server;
        changeTurnTimer=new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changeTurn();
            }
        });
        changeTurnTimer.setRepeats(false);
        server.getClientConnectEvent().addObserver(this);
        server.getDataRecievedEvent().addObserver(this);
        server.getClientDisconnectEvent().addObserver(this);
        game.getGameStartEvent().addObserver(this);
    }
    public void addNewPlayer(ClientSocket clientSocket , String name)
    {
        clientSocket.getOwner().setName(name);
        game.addPlayer(clientSocket.getOwner());
        clientSocket.response(game.getBoard().toJSONString());
      //  JSONObject objs = new JSONObject(game.getBoardObjects());
        JSONArray objs = new JSONArray();
        objs.put(game.getBoardObjects()) ;
        JSONObject wrap = new JSONObject();
        wrap.put("objects",objs);
        clientSocket.response(wrap.toString());
        clientSocket.response(((JBPlayer) clientSocket.getOwner()).toJSONString());

    }
    public void makeNewPlayer(ClientSocket handler)
    {
        JBPlayer player = new JBPlayer();
        player.setSocket(handler);
        handler.setOwner(player);
    }
    public JSONObject turnData()
    {
        JSONObject data = new JSONObject();
        JSONArray objsArray = new JSONArray();
        objsArray.put(game.getBoardObjects()) ;
        data.put("objects", objsArray);
        JSONArray playerArray = new JSONArray();
        playerArray.put(game.getPlayers());
        data.put("players",playerArray);
        return data;
    }
    public void gameStarted()
    {
  //      JBPlayer player = (JBPlayer)game.currentPlayer();
    //    player.socket.response(turnData().toString());
        changeTurnTimer.start();

    }
    public void changeTurn()
    {
        game.doTurn();
        JBPlayer player = (JBPlayer)game.currentPlayer();
        player.lastMoveTime=System.currentTimeMillis();
        player.socket.response(turnData().toString());
        changeTurnTimer.start();
    }
    public void dataRecieved(ClientSocket clientSocket,String s)
    {
        JSONObject object =new JSONObject(s);

        System.out.println(s);
        if(game.isStarted())
        {
            doTurn((JBPlayer) clientSocket.getOwner(),object);
        }else {
            if (object.has("name")) {
                addNewPlayer(clientSocket, (String) object.get("name"));
                if (game.isReadyToPlay())
                    game.start();
            }
        }
    }
    public void doTurn(JBPlayer player,JSONObject data)
    {
        //TODO : May Collide with Timer ,
        if(data.has("cmds"))
        {
            for(Object singleCmdObj : data.getJSONArray("cmds"))
            {
                JSONObject singleCmd = (JSONObject) singleCmdObj;
                String cmdType = singleCmd.getString("cmd");
                ArrayList<GameAgent> agents = player.getAgents();
                Vector2D pos =new Vector2D(singleCmd.getJSONObject("pos"));
                GameAgent agent = null;
                for (GameAgent c : agents)
                {
                    if (c.getAgentId() == singleCmd.getInt("id"))
                        agent = c;
                }
                if (agent != null)
                {
                    BasicCommand mv = null;
                    if (cmdType.equals("move"))
                    {
                         mv = new MoveCommand(player, agent, pos);
                    }
                    if (cmdType.equals("attack"))
                    {
                        mv = new AttackCommand(player, agent, pos);
                    }
                    if (cmdType.equals("make"))
                    {
                        mv = new MakeCommand(player, agent, pos,singleCmd.getString("type"));
                    }
                    mv.doCommand(game);
                }
            }
        }
        System.out.println(System.currentTimeMillis() - player.lastMoveTime);
        player.lastMoveTime = System.currentTimeMillis();

    }
    @Override
    public void update(Observable o, Object arg) {
        if(o instanceof ClientConnectEvent)
        {
           makeNewPlayer((ClientSocket) arg);
        }else  if(o instanceof GameStartEvent)
        {
            server.closeJoin();
            gameStarted();
        }else  if(o instanceof DataRecievedEvent)
        {
            Object[] args =(Object[]) arg;
            dataRecieved((ClientSocket)args[0],(String)args[1]);
        }else if(o instanceof ClientDisconnectEvent)
        {
            changeTurnTimer.stop();
            System.out.println("Game Has Stop");
            System.out.println("Player Disconnected");
        }

    }
}
