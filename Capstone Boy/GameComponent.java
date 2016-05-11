import javax.swing.*;
import java.awt.geom.*;
import java.awt.*;
import java.io.*;
/**
 * this is the component that controls the game.  Takes in keyboard commands in order to
 * change different elements of the game and control it
 * 
 * @author ngardiner
 * @version 5.10.16
 */
public class GameComponent extends JComponent
{
    /** the width of the frame */
    private static final double FRAME_WIDTH = 790;
    /** the height of the frame */
    private static final double FRAME_HEIGHT = 700;
    /** the central X coordinate at which the gun is placed */
    private static final double gunX = FRAME_WIDTH/2;
    /** the central Y coordinate at which the gun is placed */
    private static final double gunY = FRAME_HEIGHT+60;
    /** the length of the aiming device */
    private static final double aimLength=100;
    /** the radius of all the bubbles to be created */
    private static final double bubbleRadius = 50;
    /** the X coordinate of the end of the aiming device */
    private double aimX;
    /** the Y coordinate of the end of the aiming device */
    private double aimY;
    /** the array which holds all Bubble objects in the game */
    private Bubble[] bubbles;
    /** the current count of Bubble objects in bubbles */
    private int bubbleCount;
    /** boolean determining whether or not the current bubble is firing or not */
    private boolean isFiring;
    /** the current length between the firing bubble and the gun as it moves away */
    private double tempLength;
    /** a boolean determining whether the current firing bubble has collided with anything */
    private boolean collided;
    /** the X coordinate that is aimed towards by the gun */
    private double directionX;
    /** the Y coordinate that is aimed towards by the gun */
    private double directionY;
    /**
     * Default constructor for objects of class GameComponent
     * Creates all bubbles and sets instance variables
     */
    public GameComponent()
    {
        bubbleCount=0;
        setBackground(Color.WHITE);
        aimX = gunX;
        aimY = gunY-100;
        tempLength=1;
        bubbles = new Bubble[500];
        isFiring=false;
        collided=false;
        directionX=aimX;
        directionY=aimY;
        for (int i=0;i<(FRAME_WIDTH-30);i+=(bubbleRadius-2))
        {
            for (int j=0; j<FRAME_HEIGHT/2; j+=(bubbleRadius-2))
            {
                bubbles[bubbleCount] = new Bubble(i,j,bubbleRadius,FRAME_WIDTH,bubbleCount);
                bubbleCount++;
            }
        }
        //first bubble on the gun
        bubbles[bubbleCount]= new Bubble(gunX-bubbleRadius/2,gunY-bubbleRadius/2,bubbleRadius,FRAME_WIDTH,bubbleCount);
        bubbleCount++;
    }
    /**
     * repaints the frame, including all bubbles and the aiming device
     * also moves the current bubble depending on whether or not isFiring is true
     *
     * @post    the frame will be repainted appropriately
     * @param    a Graphics object that will be used to repaint the frame
     */
    public void paintComponent(Graphics g) 
    {
        super.paintComponent(g);
        Graphics2D g2= (Graphics2D) g;
        //Draw the aiming device 
        g2.setColor(Color.RED);
        g2.fill(new Ellipse2D.Double(gunX-50,gunY-50,100,100));
        g2.setColor(Color.BLACK);
        g2.draw(new Ellipse2D.Double(gunX-50,gunY-50,100,100));
        g2.draw(new Ellipse2D.Double(gunX-bubbleRadius/2,gunY-bubbleRadius/2,bubbleRadius,bubbleRadius));
        g2.setStroke(new BasicStroke(5));
        g2.draw(new Line2D.Double(gunX,gunY,aimX,aimY));
        g2.setStroke(new BasicStroke(1));
        g2.setColor(Color.WHITE);
        g2.fill(new Ellipse2D.Double(gunX-bubbleRadius/2,gunY-bubbleRadius/2,bubbleRadius,bubbleRadius));
        //Draw the bubbles
        for (Bubble b : bubbles)
            if (b != null)
            {
                b.draw(g2,bubbles);
                if (b.isDead())
                    bubbles[b.getID()]=null;
            }
        //fire
        boolean done = false;
        if (isFiring)
        {
            if (!collided && bubbles[bubbleCount-1]!=null)
            {
                double ratio = findAimRatio(aimX-bubbleRadius/2,aimY-bubbleRadius/2,tempLength);
                tempLength+=5;
                bubbles[bubbleCount-1].moveTo(findAimX(aimX,ratio),findAimY(aimY-bubbleRadius/2,ratio));
                if (bubbles[bubbleCount-1].collided(bubbles,bubbleCount))
                {
                    done = true;
                    collided=true;
                }
            }
            else
            {
                done=true;
                collided=true;
            }
            if (done)
            {
                bubbles[bubbleCount]= new Bubble(gunX-bubbleRadius/2,gunY-bubbleRadius/2,bubbleRadius,FRAME_WIDTH,bubbleCount);
                bubbleCount++;
                tempLength=1;
                done=false;
                collided=false;
                isFiring=false;
            }
        }
    }
    /**
     * moves the aiming device so that it stays the same length while pointing further left or right
     * (only moves when isFiring is false)
     *
     * @pre    direction will be a value of either 1 or 0
     * @post    aimX and aimY will be adjusted to either the left or right
     * @param    direction  an integer of value 0 or 1 which determines a movement to the left or right
     */
    public void moveAim(int direction)
    {
        if (direction==0)//0 is left
            directionX-=5;
        else if (direction==1)//1 is right
            directionX+=5;
        double ratio = findAimRatio(directionX,directionY,aimLength);
        if (!isFiring)
        {
            aimX = findAimX(directionX,ratio);
            aimY = findAimY(directionY,ratio);
        }
    }

    /**
     * starts the firing of the bubble
     *
     * @post    isFiring will be true
     */
    public void fire()
    {
        isFiring=true;
    }
    /**
     * finds the ratio which can be applied to move the aiming device without changing the length
     *
     * @param    inX  an X coordinate value to measure
     *           inY  a Y coordinate value to measure
     *           length  the length of the aiming device
     * @return    a double value which can be applied to find the new aimX and aimY of the device
     */
    public double findAimRatio(double inX,double inY,double length)
    {
        return length/(Math.sqrt((Math.pow(gunX-inX,2))+(Math.pow(gunY-inY,2))));
    }
    /**
     * finds the new aimX based on the direction to point and the ratio to determine length
     *
     * @param    inX  the X coordinate to aim towards
     *           ratio  the ratio to apply to find the new aimX
     * @return    the new X coordinate value based on the inX and ratio
     */
    public double findAimX(double inX,double ratio)
    {
        return gunX-((gunX-inX)*ratio);
    }
    /**
     * finds the new aimY based on the direction to point and the ratio to determine length
     *
     * @param    inY  the Y coordinate to aim towards
     *           ratio  the ratio to apply to find the new aimY  
     * @return    the new Y coordinate based on the inY and ratio
     */
    public double findAimY(double inY,double ratio)
    {
        return gunY-((gunY-inY)*ratio);
    }
}