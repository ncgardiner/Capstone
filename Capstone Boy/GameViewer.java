import javax.swing.JFrame;
import java.util.*;
/**
 * the basic viewer class, opens a new gameframe and uses a timer to periodically update the frame
 * 
 * @author ngardiner 
 * @version 5.10.16
 */
public class GameViewer
{
    /**
     * Creates the frame, and periodically calls the task until the program is exited
     */
    public static void main(String[] args) throws InterruptedException
    {
        GameFrame frame = new GameFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        Task task = new Task(frame);
        Timer timer = new Timer();
        timer.schedule(task,100,25);
    }
}