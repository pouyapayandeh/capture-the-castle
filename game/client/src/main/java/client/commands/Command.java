package client.commands;

import org.json.JSONString;

/**
 * Created by Pouya Payandeh on 11/7/2015.
 */
public interface Command extends JSONString
{
    @Override
    String toJSONString();
}
