import javax.swing.JFrame;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**                         
This frame contains a moving rectangle.
 */
public class RectangleFrame3 extends JFrame
{
    private static final int FRAME_WIDTH = 300;
    private static final int FRAME_HEIGHT = 400;

    private RectangleComponent3 scene;

    class FrameWindowListener extends WindowAdapter
    {
        public void windowOpened(WindowEvent event)
        {
            scene.requestFocusInWindow();
        }
    }

    public RectangleFrame3()
    {
        scene = new RectangleComponent3();
        add(scene);

        this.addWindowListener(new FrameWindowListener());

        setSize(FRAME_WIDTH, FRAME_HEIGHT);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
} 

//Nic is the best programmer evar!