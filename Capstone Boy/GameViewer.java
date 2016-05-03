import javax.swing.JFrame;
import java.util.*;
public class GameViewer
{
    public static void main(String[] args) throws InterruptedException
    {
        GameFrame frame = new GameFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        Task task = new Task(frame);
        Timer timer = new Timer();
        timer.schedule(task,100,50);
    }
}