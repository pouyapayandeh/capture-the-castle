package server.agents;

import framework.core.math.Vector2D;
import server.Settings;

/**
 * Created by Pouya Payandeh on 10/24/2015.
 */
public class Castle extends  Unit {

    public Castle(Vector2D pos) {
        super(pos,"CASTLE",Settings.CastleHP,Settings.CastleATK, Settings.CastleHP);
    }
}
