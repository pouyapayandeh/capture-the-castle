package server.agents;

import framework.core.math.Vector2D;
import server.Settings;

/**
 * Created by Pouya Payandeh on 10/24/2015.
 */
public class Worker extends Unit {
    public Worker(Vector2D pos) {
        super(pos,"WORKER", Settings.WorkerHP,Settings.WarriorATK, Settings.WorkerHP);
    }

}
