package framework.graphics;

import framework.core.GameBoard;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * Created by Pouya Payandeh on 10/24/2015.
 */
public class BattleGroundGR extends BoardGraphicalRepresentation {
    BufferedImage terrain_mountain,terrain_grass1,terrain_grass2,terrain_grass3,terrain_grass4;
    BufferedImage terrain_lake;
    BufferedImage boardImg = null;
    public BattleGroundGR(GameBoard board, int tileWidth, int tileHeight) {
        super(board, tileWidth, tileHeight);
        terrain_mountain = new BufferedImage(tileWidth,tileHeight,BufferedImage.TYPE_INT_BGR);
        loadTexture(terrain_mountain, "mountain.png");

        terrain_lake = new BufferedImage(tileWidth,tileHeight,BufferedImage.TYPE_INT_BGR);
        loadTexture(terrain_lake, "lake.png");

        terrain_grass1 = new BufferedImage(tileWidth,tileHeight,BufferedImage.TYPE_INT_BGR);
        loadTexture(terrain_grass1,"grass1.png");

        terrain_grass2 = new BufferedImage(tileWidth,tileHeight,BufferedImage.TYPE_INT_BGR);
        loadTexture(terrain_grass2,"grass2.png");

        terrain_grass3 = new BufferedImage(tileWidth,tileHeight,BufferedImage.TYPE_INT_BGR);
        loadTexture(terrain_grass3,"grass3.png");

        terrain_grass4 = new BufferedImage(tileWidth,tileHeight,BufferedImage.TYPE_INT_BGR);
        loadTexture(terrain_grass4,"grass4.png");
    }

    @Override
    public BufferedImage getTerrainImage() {
        BufferedImage img = new BufferedImage(board.getWidth() * getTileWidth(), board.getHeight()* getTileHeight(),BufferedImage.TYPE_3BYTE_BGR);
        Graphics2D gg = img.createGraphics();
        if(boardImg == null)
        {
            boardImg = new BufferedImage(board.getWidth() * getTileWidth(), board.getHeight()* getTileHeight(),BufferedImage.TYPE_3BYTE_BGR);
            Graphics2D g= boardImg.createGraphics();
            int[][] terrain = board.getTerrain();
            Random r = new Random();
            for (int x = 0; x < board.getWidth(); x++)
                for (int y = 0; y < board.getHeight(); y++)
                {
                    int totalX = x * getTileWidth();
                    int totalY = y * getTileHeight();
                    if (terrain[x][y] == 0)
                    {
                        double c =r.nextDouble();
                        if(c < 0.40)
                            g.drawImage(terrain_grass4, totalX, totalY, null);
                        else if(c < 0.80)
                            g.drawImage(terrain_grass1, totalX, totalY, null);
                        else if(c < 0.90)
                            g.drawImage(terrain_grass3, totalX, totalY, null);
                        else
                            g.drawImage(terrain_grass2, totalX, totalY, null);



                    } else if(terrain[x][y] == 1)
                    {
                        g.drawImage(terrain_mountain, totalX, totalY, null);
                    }else
                        g.drawImage(terrain_lake, totalX, totalY, null);
                }
        }
        gg.drawImage(boardImg,0,0,null);
        return img;
    }
}
