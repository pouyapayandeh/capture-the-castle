package framework.core;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Pouya Payandeh on 10/24/2015.
 */
public class MapBoard extends  GameBoard {
    String path;
    public MapBoard(String path) {
        super(0, 0);
        this.path=path;
    }

    @Override
    public void initBoard() {
        File mapfile = new File(path);
        if(mapfile.exists())
        {
            try {
                Scanner reader = new Scanner(mapfile);
                int w , h;
                width = reader.nextInt();
                heigh = reader.nextInt();
                objects = new ArrayList[width][heigh];
                for(int i = 0 ;  i < width ; i++)
                    for(int j = 0 ;  j < heigh ; j++)
                        objects[i][j]=new ArrayList<>();

                String s = reader.nextLine();
                terrain = new int[width][heigh];
                for(int i = 0 ; i  < width ; i++)
                {
                    s = reader.nextLine();
                    for(int j = 0 ; j <  heigh ; j++)
                    {
                        terrain[j][i] = Integer.parseInt(String.valueOf(s.charAt(j)));
                    }
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}
