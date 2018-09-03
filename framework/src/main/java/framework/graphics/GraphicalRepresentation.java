package framework.graphics;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Created by Pouya Payandeh on 10/15/2015.
 */
public class GraphicalRepresentation {
    protected int tileWidth;
    protected int tileHeight;

    public GraphicalRepresentation(int tileWidth, int tileHeight) {
        this.tileWidth = tileWidth;
        this.tileHeight = tileHeight;
    }

    public int getTileWidth() {
        return tileWidth;
    }

    public void setTileWidth(int tileWidth) {
        this.tileWidth = tileWidth;
    }

    public int getTileHeight() {
        return tileHeight;
    }

    public void setTileHeight(int tileHeight) {
        this.tileHeight = tileHeight;
    }

    public void loadTexture(BufferedImage img,String path)
    {
        try {
            Image imgt = ImageIO.read(getClass().getClassLoader().getResourceAsStream(path)).
                    getScaledInstance(tileWidth, tileHeight, Image.SCALE_SMOOTH);
            Graphics2D gr=img.createGraphics();
            gr.drawImage(imgt,0,0,null);
            gr.dispose();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
