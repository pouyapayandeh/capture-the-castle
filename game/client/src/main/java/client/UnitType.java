package client;

/**
 * Created by Pouya Payandeh on 11/7/2015.
 */
public enum UnitType
{
    WORKER,
    WARRIOR,
    UNKNOWN,
    CASTLE;

    UnitType fromString(String type)
    {
        if(type.equals("worker"))
            return WORKER;
        if(type.equals("warrior"))
            return WARRIOR;
        if(type.equals("castle"))
            return CASTLE;
        return UNKNOWN;
    }
}
