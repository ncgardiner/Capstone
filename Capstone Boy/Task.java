import java.util.*;
/**
 * creates a task to complete
 * 
 * @author ngardiner 
 * @version 5.10.16
 */
public class Task extends TimerTask
{
    /** frame: the GameFrame to be passed in and repainted with the run() method */
    GameFrame frame;
    public Task(GameFrame frameIn)
    {
        frame = frameIn;
    }
    public void run()
    {
        frame.repaint();
    }
}