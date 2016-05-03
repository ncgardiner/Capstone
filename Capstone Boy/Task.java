import java.util.*;
public class Task extends TimerTask
{
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