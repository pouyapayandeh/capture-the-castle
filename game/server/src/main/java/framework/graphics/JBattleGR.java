package framework.graphics;

import framework.core.BoardObject;
import framework.core.Game;
import framework.core.GameAgent;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * Created by Pouya Payandeh on 10/15/2015.
 */
public class JBattleGR extends GameGraphicalRepresentation {
    int tileWidth = 50;
    int tileHeight = 50;
    public JBattleGR(Game game) {
        super(game);
        this.bgr=new BattleGroundGR(game.getBoard(),tileWidth,tileHeight);
        this.agr=new UnitGR(tileWidth,tileWidth);
    }

    @Override
    public BufferedImage getFrame() {
        BufferedImage img = bgr.getTerrainImage();
        Graphics2D g =img.createGraphics();
        ArrayList<BoardObject> boardObjects = game.getBoardObjects();
        for(BoardObject obj : boardObjects)
        {
            agr.drawObject(obj,g);
        }
        ArrayList<GameAgent> allAgents = game.getAllAgents();

        for(GameAgent agent : allAgents)
        {
            agr.drawAgent(agent,g);
        }


        return img;
    }
}
