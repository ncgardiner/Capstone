import java.awt.event.*;
import javax.swing.*;
import java.awt.*;

public class GameFrame extends JFrame
{
    private static final int FRAME_WIDTH = 800;
    private static final int FRAME_HEIGHT = 800;
    
    private GameComponent game;
    public GameFrame()
    {
        setTitle("Capstone Game");
        
        game = new GameComponent();
        add(game);
        game.dragged(0,0);
        
        //setContentPane(new JLabel(new ImageIcon("space.jpg")));
        
        MouseListener listener1 = new MousePressListener();
        MouseMotionListener listener2 = new MousePressListener();
        game.addMouseListener(listener1);
        game.addMouseMotionListener(listener2);
        
        setSize(FRAME_WIDTH,FRAME_HEIGHT);
    }
    
    class MousePressListener implements MouseListener, MouseMotionListener
    {
        public void mousePressed(MouseEvent event)
        {
            int x = event.getX();
            int y = event.getY();
            game.dragged(x,y);
        }
        public void mouseDragged(MouseEvent event)
        {
            int x = event.getX();
            int y = event.getY();
            game.dragged(x,y);
        }
        public void mouseReleased(MouseEvent event){}
        public void mouseClicked(MouseEvent event){}
        public void mouseEntered(MouseEvent event){}
        public void mouseExited(MouseEvent event){}
        public void mouseMoved(MouseEvent event){}
    }
    
}