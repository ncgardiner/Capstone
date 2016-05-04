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
        
        this.addKeyListener(new KeyStrokeListener());
        
        setSize(FRAME_WIDTH,FRAME_HEIGHT);
    }
    //Thanks to Nic Guerrero for the KeyListener code
    class KeyStrokeListener implements KeyListener
    {
        public void keyPressed(KeyEvent event) 
        {
            String key = KeyStroke.getKeyStrokeForEvent(event).toString().replace("pressed ", "");
            if (key.equals("LEFT"))
            {
                game.moveAim(0);
            }
            else if (key.equals("RIGHT"))
            {
                game.moveAim(1);
            }
            else if (key.equals("UP")||key.equals("SPACE"))
            {
                game.fire();
            }
        }
        public void keyTyped(KeyEvent event) {}
        public void keyReleased(KeyEvent event) {}
    }
    
}