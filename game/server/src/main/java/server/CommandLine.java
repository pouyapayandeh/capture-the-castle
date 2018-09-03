package server;

import com.beust.jcommander.Parameter;

/**
 * Created by Pouya Payandeh on 11/18/2015.
 */
public class CommandLine
{
    @Parameter(names = {"-p"}, description = "port to listen")
    public Integer port = 1111;
    @Parameter(names = {"-m"},description = "Map File Address")
    public String mapPath="";
    @Parameter (names ={"-cli"},description = "cli mode")
    public Boolean cli = false;
}
