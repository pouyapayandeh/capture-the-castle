package client;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Pouya Payandeh on 10/24/2015.
 */
public class WorldModel {

    private int[][] terrain;
    ArrayList<GoldMine> goldMines;
    Player self;
    ArrayList<Player> others;
    public WorldModel() {
        goldMines=new ArrayList<>();
        others = new ArrayList<>();
    }
    int getWidth()
    {
        return terrain.length;
    }
    int getHeight()
    {
        return terrain[0].length;
    }
    public int[][] cloneTerrain()
    {
        /*
        TODO:use System.copyarray
         */
        int [][] t = new int[getWidth()][getHeight()];
        for(int i = 0 ; i < getWidth() ; i++)
            for (int j = 0 ; j < getHeight() ; j++)
                t[i][j]=terrain[i][j];
        return t;
    }
    void setTerrain(JSONObject data)
    {
        JSONArray array = data.getJSONArray("terrain");
        int w = array.length();
        int h = array.getJSONArray(0).length();
        terrain = new int[w][h];
        for(int i = 0 ; i < w ; i++)
        {
            for(int j = 0 ; j < h ; j++)
            {
                terrain[i][j] = (int)array.getJSONArray(i).get(j);
            }
        }
    }
    void setGoldMines(JSONObject data)
    {
        JSONArray array = data.getJSONArray("objects").getJSONArray(0);
        goldMines.clear();
        for(int i=0 ; i < array.length() ; i++)
        {
            goldMines.add(new GoldMine(array.getJSONObject(i)));
        }
    }
    void setSelf(JSONObject data)
    {
        self = new Player(data);
    }
    void update(JSONObject data)
    {
        setGoldMines(data);
        others.clear();
        for(Object obj : data.getJSONArray("players").getJSONArray(0))
        {
            JSONObject player = (JSONObject) obj;
            if(player.getInt("PlayerId")==self.id)
            {
                self.updateAgents(player.getJSONArray("Agents").getJSONArray(0).getJSONArray(0));
            }else
            {
                Player oplayer = new Player(player);
                oplayer.updateAgents(player.getJSONArray("Agents").getJSONArray(0).getJSONArray(0));
                others.add(oplayer);
            }
        }
    }
}
