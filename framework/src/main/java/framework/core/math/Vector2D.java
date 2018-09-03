package framework.core.math;

import org.json.JSONObject;
import org.json.JSONString;

/**
 * Created by Pouya Payandeh on 10/16/2015.
 */
public class Vector2D implements JSONString{
    public int x;
    public int y;
    public Vector2D(int x, int y) {

        this.x = x;
        this.y = y;
    }
    public Vector2D(JSONObject data)
    {
        this.x=data.getInt("x");
        this.y=data.getInt("y");
    }
    public void add(Vector2D v)
    {
        this.x+=v.x;
        this.y+=v.y;
    }
    public static Vector2D add(Vector2D v1 , Vector2D v2)
    {
        return new Vector2D(v1.x+v2.x , v2.y+v2.y);
    }
    public int getDistance(Vector2D v)
    {
        int xx = x-v.x;
        int yy = y-v.y;
        return (int) Math.sqrt(xx*xx+yy*yy);
    }

    @Override
    public Vector2D clone()  {
        return new Vector2D(x,y);
    }

    @Override
    public boolean equals(Object obj)
    {
        if(obj instanceof Vector2D)
        {
            Vector2D v = (Vector2D) obj;
            return x==v.x && y == v.y;
        }
        return super.equals(obj);
    }

    @Override
    public String toString()
    {
        return "Vector2D{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    @Override
    public String toJSONString() {
        JSONObject obj = new JSONObject();
        obj.accumulate("x",x);
        obj.accumulate("y",y);
        return obj.toString();
    }
}
