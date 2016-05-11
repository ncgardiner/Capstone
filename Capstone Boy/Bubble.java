import java.awt.*;
import java.awt.geom.*;
import java.util.Random;
/**
 * The bubbles that are present in a game.  This class represents one individual bubble,
 * and controls its properties
 * 
 * @author ngardiner
 * @version 5.10.16
 */
public class Bubble
{
    /** the X coordinate of the bubble */
    private double x;
    /** the Y coordinate of the bubble */
    private double y;
    /** the Color property of the bubble */
    private Color color;
    /** the radius of the bubble */
    private double radius;
    /** the width of the frame that the bubble is within */
    private double frameWidth;
    /** a boolean value determining whether or not the bubble is alive */
    private boolean alive;
    /** the unique ID number of the bubble */
    private int idNum;
    /** a boolean value representing whether the bubble is connected to any other bubbles of the same color */
    private boolean connected;
    /**
     * Default constructor for objects of class Bubble
     */
    public Bubble(double inX,double inY,double radiusIn,double frameW,int id)
    {
        color = this.randomColor();
        x = inX;
        y = inY;
        radius = radiusIn;
        frameWidth=frameW;
        alive = true;
        idNum = id;
        connected = false;
    }

    /**
     * returns a random color between 5 potential options
     *
     * @return    a random Color object
     */
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

    /**
     * draws the bubble
     *
     * @post    the bubble will be drawn
     * @param    g2  a Graphics2D object which is used to draw the bubble
     */
    public void draw(Graphics2D g2,Bubble[] bubs)
    {
        g2.setColor(color);
        g2.fill(new Ellipse2D.Double(x,y,radius,radius));
        if (connected)
            findConnected(bubs,true);
    }
    /**
     * moves the bubble to the inputed X and Y coordinates (offsets slightly so that it is drawn correctly)
     *
     * @post    the bubble will be moved to the appropriate location
     * @param    inX  the X coordinate to move to
     *           inY  the Y coordinate to move to
     */
    public void moveTo(double inX,double inY)
    {
        x = inX-radius/2;
        y = inY-radius/2;
    }
    /**
     * determines whether this bubble has collided with any other bubbles or the border of the frame
     *
     * @param    bubbles   an array of bubbles to sort through to detect collision with any of them
     *           skip  a value at which to stop sorting, because this Bubble has reached itself
     *                  (this Bubble's index in bubbles)
     * @return    a boolean value that says whether the bubble has collided with any bubbles or border
     */
    public boolean collided(Bubble[] bubbles,int skip)
    {
        for (Bubble b: bubbles)
        {
            skip--;
            if (skip>0 && b!=null)
            {
                if (Math.sqrt(Math.pow(b.getX()-x,2)+Math.pow(b.getY()-y,2))<=radius)
                {
                    findConnected(bubbles,false);
                    return true;
                }
                else if (x<0||x>frameWidth-35||y<0)//if the bubble hits the border
                    return true;
            }
        }
        return false;
    }
    /**
     * determines whether the bubble is connected with any other bubbles in order to form a chain 
     *
     * @post    if a connected bubble is found, calls kill()
     * @param    bubbles  an array of bubbles to sort through and find connected ones
     *           found  a boolean value of whether or not a chain of bubbles has been formed
     */
    public void findConnected(Bubble[] bubbles,boolean found)
    {
        for (Bubble b: bubbles)
        {
            if (b!=null)
                if (b.getID()!=idNum && color==b.getColor())
                    if (Math.sqrt(Math.pow(b.getX()-x,2)+Math.pow(b.getY()-y,2))<=radius+5)
                    {
                        b.connected();
                        found = true;
                    }
        }
        if (found)
            kill();
    }
    /**
     * returns whether or not the bubble is dead
     *
     * @return    true if the bubble is dead, false if it's still alive
     */
    public boolean isDead()
    {
        return !alive;
    }
    /**
     * sets connected to true in order to find a chain of connected bubbles
     *  @post  connected will be set to true
     */
    public void connected(){connected = true;}
    /**
     * returns the X coordinate of the bubble
     *
     * @return    the X coordinate of this Bubble object
     */
    public double getX(){return x;}
    /**
     * returns the Y coordinate of the bubble
     *
     * @return    the Y coordinate of this Bubble object
     */
    public double getY(){return y;}
    /**
     * returns this Bubble object's color
     * 
     * @return    this bubble's color
     */
    public Color getColor(){return color;}
    /**
     * returns this bubble's ID number 
     *
     * @return    an integer object determining this Bubble's unique ID
     */
    public int getID(){return idNum;}
    /**
     * sets alive to false
     *
     * @post    alive will be false
     */
    public void kill(){alive=false;}
}






