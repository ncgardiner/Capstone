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
    private double aimX;
    private double aimY;
    private Bubble[][] bubbles;
    private Bubble[] newBubbles;
    private int bubs;
    private int bubbleRadius;
    private int bubbleCount;
    private boolean isFiring;
    private double tempLength;
    private boolean collided;
    private double directionX;
    private double directionY;
    private int rows;
    private int cols;
    public GameComponent()
    {
        bubbleCount=0;
        setBackground(Color.WHITE);
        aimX = gunX;
        aimY = gunY-100;
        tempLength=1;
        bubbleRadius = 30;
        isFiring=false;
        collided=false;
        directionX=aimX;
        directionY=aimY;
        bubs=0;
        rows=(int)(FRAME_WIDTH)/((bubbleRadius-2));
        cols=(int)(FRAME_HEIGHT/2)/(bubbleRadius-2);
        bubbles = new Bubble[rows][cols];
        newBubbles = new Bubble[100];
        for (int i=0;i<rows;i++)
        {
            for (int j=0; j<cols; j++)
            {
                bubbles[i][j] = new Bubble(i*(bubbleRadius-2),j*(bubbleRadius-2),bubbleRadius,FRAME_WIDTH,bubbleCount);
                bubbleCount++;
            }
        }
        //first bubble on the gun
        newBubbles[bubs]= new Bubble(gunX-bubbleRadius/2,gunY-bubbleRadius/2,bubbleRadius,FRAME_WIDTH,bubs);
        bubs++;
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
        g2.setStroke(new BasicStroke(5));
        g2.draw(new Line2D.Double(gunX,gunY,aimX,aimY));
        g2.setStroke(new BasicStroke(1));
        //Draw the bubbles
        for (int i = 0; i< rows;i++)
            for (int j = 0; j < cols;j++)
            {
                if (!bubbles[i][j].isDead())
                {
                    bubbles[i][j].draw(g2);
                }
            }
        for (Bubble b : newBubbles)
        {
            if (b != null)
            {
                b.draw(g2);
                if (b.isDead())
                {
                    bubbles[b.getID()]=null;
                }
            }
        }
        //fire
        if (isFiring)
            shoot();
    }

    public void moveAim(int direction)
    {
        if (direction==0)//0 is left
        {
            directionX-=5;
        }
        else if (direction==1)//1 is right
        {
            directionX+=5;
        }
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

    public void shoot()
    {
        boolean done = false;
        if (!collided && newBubbles[bubs-1].isDead()==false)
        {
            double ratio = findAimRatio(aimX-bubbleRadius/2,aimY-bubbleRadius/2,tempLength);
            tempLength+=5;
            newBubbles[bubs-1].moveTo(findAimX(aimX,ratio),findAimY(aimY-bubbleRadius/2,ratio));
            if (newBubbles[bubs-1].collided(bubbles,newBubbles,bubs))
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
            newBubbles[bubs]= new Bubble(gunX-bubbleRadius/2,gunY-bubbleRadius/2,bubbleRadius,FRAME_WIDTH,bubs);
            bubs++;
            tempLength=1;
            done=false;
            collided=false;
            isFiring=false;
        }
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