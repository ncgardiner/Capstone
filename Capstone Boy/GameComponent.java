import javax.swing.*;
import java.awt.geom.*;
import java.awt.*;
import java.io.IOException;
import java.lang.InterruptedException;
public class GameComponent extends JComponent
{
    private static final double FRAME_WIDTH = 790;
    private static final double FRAME_HEIGHT = 700;
    private static final double gunX = FRAME_WIDTH/2;
    private static final double gunY = FRAME_HEIGHT+60;
    private static final double aimLength=100;
    private static final double bubbleRadius = 30;
    private double aimX;
    private double aimY;
    private Bubble[] bubbles;
    private int bubbleCount;
    private boolean isFiring;
    private double tempLength;
    private boolean collided;
    private double directionX;
    private double directionY;
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
        for (int i=0;i<(FRAME_WIDTH-10);i+=(bubbleRadius-2))
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

    public void fire()
    {
        isFiring=true;
    }

    public double findAimRatio(double inX,double inY,double length)
    {
        return length/(Math.sqrt((Math.pow(gunX-inX,2))+(Math.pow(gunY-inY,2))));
    }

    public double findAimX(double inX,double ratio)
    {
        return gunX-((gunX-inX)*ratio);
    }

    public double findAimY(double inY,double ratio)
    {
        return gunY-((gunY-inY)*ratio);
    }

    public double getFrameWidth(){return FRAME_WIDTH;}

    public double getFrameHeight(){return FRAME_HEIGHT;}

    
    
    
    
    
    
    
}