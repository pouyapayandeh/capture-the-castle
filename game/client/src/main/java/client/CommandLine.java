package client;

import com.beust.jcommander.Parameter;

/**
 * Created by Pouya Payandeh on 11/18/2015.
 */
public class CommandLine
{
    @Parameter(names = {"-p"}, description = "port to listen")
    public Integer port = 1111;
    @Parameter(names = {"-h"},description = "Server Host Address")
    public String host="127.0.0.1";
}
