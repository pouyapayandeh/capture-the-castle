package framework.core;

import java.util.Random;

/**
 * Created by Pouya Payandeh on 10/11/2015.
 */
public class RandomBoard extends GameBoard {
    int r;
    public RandomBoard(int width, int heigh , int r) {
        super(width, heigh);
        this.r = r ;
    }

    @Override
    public void initBoard() {
        Random rand = new Random();
        for(int x = 0 ;  x < width ; x++)
            for(int y = 0 ; y < heigh ; y++)
                terrain[x][y] = rand.nextInt(r);
    }
}
