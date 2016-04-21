import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JComponent;
import java.awt.geom.Line2D;
import java.awt.Point;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Ellipse2D;
import java.awt.Color;
public class GameComponent extends JComponent
{
    private static final double FRAME_WIDTH = 800;
    private static final double FRAME_HEIGHT = 700;
    public GameComponent()
    {
        setBackground(Color.WHITE);
    }
    
    public void paintComponent(Graphics g)
    {
        Graphics2D g2= (Graphics2D) g; 
        g2.setColor(Color.BLACK);
        g2.draw(new Ellipse2D.Double(FRAME_WIDTH/2,FRAME_HEIGHT,50,50));
    }
    public void painter()
    {
        repaint();
    }
}