package framework.graphics;

import framework.core.GameBoard;

import java.awt.image.BufferedImage;

/**
 * Created by Pouya Payandeh on 10/11/2015.
 */
public abstract  class BoardGraphicalRepresentation extends GraphicalRepresentation {
    protected GameBoard board;

    public BoardGraphicalRepresentation(GameBoard board, int tileWidth, int tileHeight) {
        super(tileWidth, tileHeight);
        this.board = board;
    }
    public int getTotalWidth()
    {
        return  board.getWidth()*tileWidth;
    }
    public int getTotalHeight()
    {
        return  board.getHeight()*tileHeight;
    }
    public int getBoardWidth()
    {
        return board.getWidth();
    }
    public  int getBoardHeight()
    {
        return board.getHeight();
    }
    public abstract BufferedImage getTerrainImage();

}
