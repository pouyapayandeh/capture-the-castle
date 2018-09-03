package framework.network;

import framework.core.GamePlayer;
import framework.network.events.ClientDisconnectEvent;
import framework.network.events.DataRecievedEvent;

import java.io.*;
import java.net.Socket;

/**
 * Created by Pouya Payandeh on 10/22/2015.
 */
public class ClientSocket  implements  Runnable{
    Socket socket;
    Thread thread;
    DataRecievedEvent dataRecievedEvent;
    GamePlayer owner = null;
    ClientDisconnectEvent clientDisconnect;
    public GamePlayer getOwner() {
        return owner;
    }

    public void setOwner(GamePlayer owner) {
        this.owner = owner;
    }

    public DataRecievedEvent getDataRecievedEvent() {
        return dataRecievedEvent;
    }
    public ClientDisconnectEvent getClientDisconnect()
    {
        return clientDisconnect;
    }
    private OutputStreamWriter osw;

    public ClientSocket() {
        thread = new Thread(this);
        dataRecievedEvent = new DataRecievedEvent();
        clientDisconnect=new ClientDisconnectEvent();
    }

    void handelSocket(Socket socket)
    {
        this.socket = socket;
        try {
            osw = new OutputStreamWriter(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        thread.start();
    }
    public void connect(String host,int port)
    {
        try {
            socket=new Socket(host, port);
            osw = new OutputStreamWriter(socket.getOutputStream());
            thread.start();
        } catch (IOException e) {
            System.err.println("Could not connect to given host");
        }
    }
    public void response(String s)
    {
        try {
            osw.write(s);
            osw.write("\n");
            osw.flush();
        } catch (IOException e) {
            //e.printStackTrace();
            clientDisconnect.notifyObservers(this);
        }
    }
    @Override
    public void run() {
        BufferedReader stream = null;
        try {
            stream = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            while (true)
            {
                String s = stream.readLine();
                Object[] args={this,s};
                dataRecievedEvent.notifyObservers(args);
            }

        } catch (IOException e) {
            clientDisconnect.notifyObservers(this);
        }
    }
}
