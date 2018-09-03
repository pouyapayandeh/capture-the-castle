package framework.graphics;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

/**
 * Created by Pouya Payandeh on 10/12/2015.
 */
public class JFrameWrap extends JFrame {
    BufferedImage img;
    JPanel panel;
    GameGraphicalRepresentation ggr;
    Timer timer = new Timer(500, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            Graphics2D g = (Graphics2D)panel.getGraphics();
            if(g != null)
            {
                img = ggr.getFrame();
                g.drawImage(img,0,0,null);
            }
        }
    });
    public JFrameWrap() throws HeadlessException {
    }


    public void initUI(GameGraphicalRepresentation ggr,int width ,int height)
    {
        this.ggr= ggr;
        panel=new JPanel();
        this.add(panel);
        panel.setSize(width,height);
        panel.setPreferredSize(new Dimension(width,height));

        setResizable(false);
        setVisible(true);
        pack();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        timer.start();

    }

    public void initUI(GameGraphicalRepresentation ggr)
    {
        this.ggr= ggr;
        panel=new JPanel();
        this.add(panel);
        panel.setSize(ggr.bgr.getTotalWidth(),ggr.bgr.getTotalHeight());
        panel.setPreferredSize(new Dimension(ggr.bgr.getTotalWidth(),ggr.bgr.getTotalHeight()));

        setResizable(false);
        setVisible(true);
        pack();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        timer.start();

    }

    @Override
    public void paint(Graphics g) {
     //   super.paint(g);
        Graphics2D g2 = (Graphics2D)panel.getGraphics();
        if(g2 != null && img != null)
        {
                g2.drawImage(img,0,0,null);
        }
    }
}
