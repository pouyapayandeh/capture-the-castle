package framework.network;

import framework.network.events.ClientConnectEvent;
import framework.network.events.ClientDisconnectEvent;
import framework.network.events.DataRecievedEvent;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by Pouya Payandeh on 10/22/2015.
 */
public class GameServer implements  Runnable , Observer{
    ServerSocket socket;
    Thread thread;
    int port;
    ArrayList<ClientSocket> clients;
    boolean stillRunning;

    public ClientConnectEvent getClientConnectEvent() {
        return clientConnectEvent;
    }

    public DataRecievedEvent getDataRecievedEvent() {
        return dataRecievedEvent;
    }


    DataRecievedEvent dataRecievedEvent;
    ClientConnectEvent clientConnectEvent;

    public ClientDisconnectEvent getClientDisconnectEvent()
    {
        return clientDisconnectEvent;
    }

    ClientDisconnectEvent clientDisconnectEvent;
    public GameServer(int port) {
        this.port = port;
        thread = new Thread(this);
        stillRunning=true;
        clients = new ArrayList<ClientSocket>();
        clientConnectEvent = new ClientConnectEvent();
        dataRecievedEvent = new DataRecievedEvent();

        clientDisconnectEvent = new ClientDisconnectEvent();
    }
    public void startServer()
    {
        try {
            socket = new ServerSocket(port);
            System.out.println("Game Server Started");
            thread.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void closeJoin()
    {
      thread.interrupt();
    }
    @Override
    public void run() {
        while (stillRunning)
        {
            ClientSocket clientSocket = new ClientSocket();

            try {
                clientSocket.dataRecievedEvent.addObserver(this);
                clientSocket.clientDisconnect.addObserver(this);
                clientSocket.handelSocket(socket.accept());
                clientConnectEvent.notifyObservers(clientSocket);
               // System.out.println("Client Connected");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        if(o instanceof DataRecievedEvent)
        {
            dataRecievedEvent.notifyObservers(arg);
        }else if(o instanceof  ClientDisconnectEvent)
        {
            clientDisconnectEvent.notifyObservers(arg);
        }
    }
}
