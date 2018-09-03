package framework.graphics;

import framework.core.BoardObject;
import framework.core.GameAgent;

import java.awt.*;

/**
 * Created by Pouya Payandeh on 10/15/2015.
 */
public abstract class BoardObjectGraphicalRepresentation extends GraphicalRepresentation {
    public BoardObjectGraphicalRepresentation(int tileWidth, int tileHeight) {
        super(tileWidth, tileHeight);
    }
    public abstract void drawAgent(GameAgent ag,Graphics2D g);
    public abstract void drawObject(BoardObject object,Graphics2D g);

}
