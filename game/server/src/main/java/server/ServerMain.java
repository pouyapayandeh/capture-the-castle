package server;

import com.beust.jcommander.JCommander;
import framework.core.MapBoard;
import framework.graphics.JFrameWrap;
import framework.network.GameServer;
import framework.graphics.JBattleGR;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * Created by Pouya Payandeh on 10/24/2015.
 */
public class ServerMain {
    public static void main(String[] args) {


        CommandLine jct = new CommandLine();
        boolean error = false;
        new JCommander(jct,args);
        if(jct.cli)
        {
            if(jct.mapPath.equals(""))
            {
                error = true;
                System.err.println("No Map File Address Entered");
            }

        }
        else
        {
            JFrameWrap frameWrap =new JFrameWrap();
            if(jct.mapPath.equals(""))
            {
                JFileChooser jFileChooser = new JFileChooser();
                jFileChooser.setFileFilter(new FileNameExtensionFilter("JBATTLE MAP FILE(*.map)","map"));
                if(jFileChooser.showOpenDialog(null) != JFileChooser.APPROVE_OPTION )
                {
                    System.err.println("No Map File Selected");
                    error = true;
                }else
                {
                    jct.mapPath=jFileChooser.getSelectedFile().getPath();
                }
            }
        }
        if(!error)
        {
            System.out.println(jct.mapPath);
            MapBoard b = new MapBoard(jct.mapPath);
            b.initBoard();
            JBattleGame g = new JBattleGame();
            g.loadPositionFile(jct.mapPath);
            g.setBoard(b);

            if (!jct.cli)
            {
                JBattleGR jBattleGR = new JBattleGR(g);
                JFrameWrap frameWrap = new JFrameWrap();
                frameWrap.initUI(jBattleGR);
            }

            GameServer server = new GameServer(jct.port.intValue());
            NetworkHandler networkHandler = new NetworkHandler(g, server);
            server.startServer();
        }
    }
}
