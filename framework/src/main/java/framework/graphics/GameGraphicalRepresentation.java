package framework.graphics;

import framework.core.Game;

import java.awt.image.BufferedImage;

/**
 * Created by Pouya Payandeh on 10/15/2015.
 */
public abstract class GameGraphicalRepresentation {
    public BoardGraphicalRepresentation bgr;
    public BoardObjectGraphicalRepresentation agr;
    public Game game;

    public GameGraphicalRepresentation(BoardGraphicalRepresentation bgr, BoardObjectGraphicalRepresentation agr) {
        this.bgr = bgr;
        this.agr = agr;
    }

    public GameGraphicalRepresentation(Game game) {
        this.game = game;
    }
    public abstract BufferedImage getFrame();
}
