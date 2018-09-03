package client;

import framework.core.math.Vector2D;

/**
 * Created by Pouya Payandeh on 11/7/2015.
 */
public enum Direction
{
    /*
       TODO : rename this to FourWayDirection
       TODO : move this too framework
       TODO : implement EightWayDirection
    */
    N,E,S,W;

    /**
     * apply the direction to the vector
     * @param pos the given vector
     * @param dir the given direction
     */
    public void apply(Vector2D pos , Direction dir)
    {
        switch (dir)
        {
            case N:
                pos.y++;
                break;
            case E:
                pos.x++;
                break;
            case S:
                pos.y--;
                break;
            case W:
                pos.x--;
                break;
        }
    }
    /**
     * apply the direction to the vector
     * @param pos the given vector
     */
    public void apply(Vector2D pos)
    {
        apply(pos,this);
    }
}
