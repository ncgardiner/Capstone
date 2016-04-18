import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JComponent;
import java.awt.geom.Line2D;
import java.awt.Point;
public class GameComponent extends JComponent
{
    
    public GameComponent()
    {
        
    }
    
    public void paintComponent(Graphics g)
    {
        Graphics2D g2= (Graphics2D) g;
    }
    public void painter()
    {
        repaint();
    }
}