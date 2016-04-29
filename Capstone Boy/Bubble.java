import java.awt.*;
import java.awt.geom.*;
import java.util.Random;
public class Bubble
{
    private double x;
    private double y;
    private Color color;
    private double radius;
    public Bubble(double inX,double inY,double radiusIn)
    {
        color = this.randomColor();
        x = inX;
        y = inY;
        radius = radiusIn;
    }
    public Color randomColor()
    {
        Random rand = new Random();
        int x = rand.nextInt(5);
        if (x == 0)
            return Color.RED;
        else if (x == 1)
            return Color.BLUE;
        else if (x == 2)
            return Color.GREEN;
        else if (x == 3)
            return Color.YELLOW;
        else 
            return Color.MAGENTA;
    }
    public void draw(Graphics2D g2)
    {
        g2.setColor(color);
        g2.fill(new Ellipse2D.Double(x,y,radius,radius));
    }
    public double getX(){return x;}
    public double getY(){return y;}
}