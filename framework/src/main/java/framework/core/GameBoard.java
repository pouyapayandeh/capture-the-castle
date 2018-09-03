package framework.core;

import framework.core.math.Vector2D;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONString;

import java.util.ArrayList;

/**
 * Created by Pouya Payandeh on 10/11/2015.
 */
public class GameBoard implements JSONString{
    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return heigh;
    }

    public int[][] getTerrain() {
        return terrain.clone();
    }

    protected int width, heigh;
    protected int[][] terrain;

    public ArrayList<BoardObject>[][] getObjects() {
        return objects;
    }

    protected ArrayList<BoardObject>[][] objects;
    public GameBoard(int width, int heigh) {
        this.width = width;
        this.heigh = heigh;
        terrain = new int[width][heigh];
        objects = new ArrayList[width][heigh];
        for(int i = 0 ;  i < width ; i++)
            for(int j = 0 ;  j < heigh ; j++)
                objects[i][j]=new ArrayList<>();
    }
    public void initBoard(){}
    public int getTerrainAt(Vector2D pos)
    {
        if(pos.x >= 0 && pos.x < width && pos.y >= 0 && pos.y< heigh)
        {
            return terrain[pos.x][pos.y];
        }
        return Integer.MIN_VALUE;
    }

    @Override
    public String toJSONString() {
        JSONObject obj = new JSONObject();
        JSONArray array = new JSONArray(terrain);
        obj.put("terrain",array);
        return obj.toString();
    }
}
