package client;

import client.commands.AttackCommand;
import client.commands.Command;
import client.commands.MakeCommand;
import client.commands.MoveCommand;
import framework.core.math.Vector2D;
import org.json.JSONObject;

/**
 * Created by Pouya Payandeh on 10/25/2015.
 */
public class Unit
{
    /**
     *
     * @return unit's type
     */
    public UnitType getType()
    {
        return type;
    }

    /**
     *
     * @return return unit's id <br>
     * all units of a player have unique id <br>
     * but all units of all players don't
     */
    public int getId()
    {
        return id;
    }

    /**
     *
     * @return id of the player who own this unit
     */
    public int getOwner()
    {
        return owner;
    }

    /**
     *
     * @return The Health Point
     */
    public int getHP()
    {
        return HP;
    }

    public int getATK()
    {
        return ATK;
    }

    /**
     *
     * @return a clone of unit position
     */
    public Vector2D getPos()
    {
        return pos.clone();
    }
    private UnitType type;
    private int id;
    private int owner;
    private int HP;
    private int ATK;
    private Vector2D pos;

    public Command getCmd()
    {
        return cmd;
    }

    private Command cmd;
    public Unit(JSONObject data) {
        type=UnitType.valueOf(data.getString("type"));
        id=data.getInt("id");
        owner = data.getInt("owner");
        pos= new Vector2D(data.getJSONObject("pos"));
        HP=data.getInt("HP");
        ATK = data.getInt("ATK");
    }

    /**
     * move the unit in the given direction
     * @param dir the direction for moving the unit
     */
    void move(Direction dir)
    {
        Vector2D pos = getPos();
        dir.apply(pos);
        cmd = new MoveCommand(id,pos);
    }

    /**
     * unit would attack in the given direction
     * @param dir
     */
    void attack(Direction dir)
    {
        Vector2D pos = getPos();
        dir.apply(pos);
        cmd = new AttackCommand(id,pos);
    }

    /**
     * unit would make the given unit type in the given direction
     * @param dir
     * @param type
     */
    void make(Direction dir, UnitType type)
    {
        Vector2D pos = getPos();
        dir.apply(pos);
        cmd = new MakeCommand(id,pos,type);
    }

}
