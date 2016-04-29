import javax.swing.*;
import java.awt.geom.*;
import java.awt.*;
import java.io.IOException;
public class GameComponent extends JComponent
{
    private static final double FRAME_WIDTH = 790;
    private static final double FRAME_HEIGHT = 700;
    private static final double gunX = FRAME_WIDTH/2;
    private static final double gunY = FRAME_HEIGHT+60;
    private static final double aimLength=100;
    private boolean firstDragCancel;
    private double aimX;
    private double aimY;
    private double prevX;
    private double prevY;
    private Bubble[] bubbles;
    private double bubbleRadius;
    public GameComponent()
    {
        setBackground(Color.WHITE);
        aimX = gunX;
        aimY = gunY-100;
        prevX = 0;
        prevY = 0;
        //500 too big find exact number you asshole
        bubbles = new Bubble[500];
        bubbleRadius = 30;
        firstDragCancel=true;
        int count = 0;
        for (int i=0;i<FRAME_WIDTH;i+=(FRAME_WIDTH/20))
        {
            for (int j=0; j<FRAME_HEIGHT/2; j+=(FRAME_WIDTH/10))
            {
                bubbles[count] = new Bubble(i,j,bubbleRadius);
                count++;
            }
        }
    }
    
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2= (Graphics2D) g;
        g2.setStroke(new BasicStroke(1));
        //Draw the aiming device 
        g2.setColor(Color.RED);
        g2.fill(new Ellipse2D.Double(gunX-50,gunY-50,100,100));
        g2.setColor(Color.BLACK);
        g2.draw(new Ellipse2D.Double(gunX-50,gunY-50,100,100));
        g2.setStroke(new BasicStroke(5));
        g2.draw(new Line2D.Double(gunX,gunY,aimX,aimY));
        //Draw the bubbles
        for (Bubble b : bubbles)
        {
            b.draw(g2);
        }
    }
    public void dragged(double inX,double inY)
    {
        if (firstDragCancel==true)
        {
            repaint();
            firstDragCancel=false;
            return;
        }
        double ratio = aimLength/(Math.sqrt((Math.pow(gunX-inX,2))+(Math.pow(gunY-inY,2))));
        aimX = gunX-((gunX-inX)*ratio);
        aimY = gunY-((gunY-inY)*ratio);
        repaint();
    }
    public void fire(double inX,double inY)
    {
        if (prevX == aimX && prevY == aimY)
        {
            //fire here
        }
        prevX = aimX;
        prevY = aimY;
    }
    public double getFrameWidth(){return FRAME_WIDTH;}
    public double getFrameHeight(){return FRAME_HEIGHT;}
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}