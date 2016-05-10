import java.awt.*;
import java.awt.geom.*;
import java.util.Random;
public class Bubble
{
    private double x;
    private double y;
    private Color color;
    private int radius;
    private double frameWidth;
    private boolean alive;
    private static final int chainMinimum = 0;
    private int idNum;
    public Bubble(double inX,double inY,int radiusIn,double frameW,int id)
    {
        color = this.randomColor();
        x = inX;
        y = inY;
        radius = radiusIn;
        frameWidth=frameW;
        alive = true;
        idNum = id;
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

    public void moveTo(double inX,double inY)
    {
        x = inX-radius/2;
        y = inY-radius/2;
    }

    public boolean collided(Bubble[][] oldBubs, Bubble[] bubbles,int skip)
    {
        for (int i = 0; i < oldBubs.length; i++)
            for (int j = 0; j < oldBubs[0].length;j++)
            {
                if (!oldBubs[i][j].isDead())
                {
                    Bubble b = oldBubs[i][j];
                    if (Math.sqrt(Math.pow(b.getX()-x,2)+Math.pow(b.getY()-y,2))<=radius)
                    {
                        findConnected(oldBubs,bubbles,0);
                        return true;
                    }
                    else if (x<0||x>frameWidth-35||y<0)//if the bubble hits the border
                        return true;
                }
            }
        for (Bubble b: bubbles)
        {
            skip--;
            if (skip>0 && b!=null)
            {
                if (Math.sqrt(Math.pow(b.getX()-x,2)+Math.pow(b.getY()-y,2))<=radius)
                {
                    findConnected(oldBubs,bubbles,0);
                    return true;
                }
                else if (x<0||x>frameWidth-35||y<0)//if the bubble hits the border
                    return true;
            }
        }
        return false;
    }

    public int findConnected(Bubble[][] oldBubs, Bubble[] bubbles,int chainCount)
    {
        for (Bubble b: bubbles)
        {
            if (b!=null)
                if (b.getID()!=idNum && color==b.getColor())
                    if (Math.sqrt(Math.pow(b.getX()-x,2)+Math.pow(b.getY()-y,2))<=radius)
                    {
                        chainCount++;
                        //chainCount=b.findConnected(oldBubs,bubbles,chainCount);
                    }
        }
        if (chainCount>=chainMinimum)
            kill();
        return chainCount;
    }
    
    public boolean isDead()
    {
        return !alive;
    }
    
    public double getX(){return x;}

    public double getY(){return y;}
    
    public Color getColor(){return color;}
    
    public int getID(){return idNum;}
    
    public void kill(){alive=false;}
}











