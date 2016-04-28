import javax.swing.*;
import java.awt.geom.Line2D;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Ellipse2D;
public class GameComponent extends JComponent
{
    private static final double FRAME_WIDTH = 800;
    private static final double FRAME_HEIGHT = 700;
    private static final double gunX = FRAME_WIDTH/2;
    private static final double gunY = FRAME_HEIGHT+60;
    private static final double aimLength=100;
    private boolean firstDragCancel;
    private double aimX;
    private double aimY;
    public GameComponent()
    {
        setBackground(Color.WHITE);
        aimX = gunX;
        aimY = gunY-100;
        firstDragCancel=true;
    }
    
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        //Draw the aiming device
        Graphics2D g2= (Graphics2D) g; 
        g2.setColor(Color.RED);
        g2.fill(new Ellipse2D.Double(gunX-50,gunY-50,100,100));
        g2.setColor(Color.BLACK);
        g2.draw(new Ellipse2D.Double(gunX-50,gunY-50,100,100));
        g2.setStroke(new BasicStroke(5));
        g2.draw(new Line2D.Double(gunX,gunY,aimX,aimY));
        //Draw the balls
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
}