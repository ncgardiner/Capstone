import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
/**
 * the frame for the game. extends JFrame to create a frame that 
 * the game can execute on, as well as uses a keylistener to execute
 * commands to the game.
 * 
 * @author ngardiner 
 * @version 5.10.16
 */
public class GameFrame extends JFrame
{
    private static final int FRAME_WIDTH = 800;
    private static final int FRAME_HEIGHT = 800;

    private GameComponent game;
    public GameFrame()
    {
        setTitle("Bubble Popper 3000 Extreme");

        Container c = getContentPane();
        c.setBackground(Color.CYAN);

        game = new GameComponent();
        add(game);

        this.addKeyListener(new KeyStrokeListener());

        setSize(FRAME_WIDTH,FRAME_HEIGHT);
    }
    /**
     * A keylistener that takes in commands from the keyboard and passes appropriate actions
     * to the game's component.  Special thanks to Nic Guerrero for the code in this class
     * 
     * @author ngardiner (help from Nic Guerrero) 
     * @version 5.10.16
     */
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