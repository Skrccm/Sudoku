// Calvin Chan
// Ms. Dyke
// January 17th, 2012
// This thread is the animation part for the Sudoku class
// The "Animation" class.
import javax.swing.*;
import java.awt.*;
import java.awt.image.*;
public class Animation extends Thread
{
    public class AnimationDrawing extends JFrame
    {
	JFrame frame;
	final Color BG = new Color (135, 206, 255);
	final Color TC = new Color (74, 112, 139);
	GradientPaint gradient = new GradientPaint (0, 0, Color.WHITE, 640, 500, BG, false);
	BufferStrategy bf;
	public AnimationDrawing (JFrame frameToDrawIn)
	{
	    frame = frameToDrawIn;
	    BufferCapabilities cap = new BufferCapabilities (new ImageCapabilities (true), new ImageCapabilities (true), BufferCapabilities.FlipContents.COPIED);
	    while (frame.getBufferStrategy () == null)
	    {
		try
		{
		    frame.createBufferStrategy (2, cap);
		}
		catch (AWTException e)
		{
		}
	    }
	    bf = frame.getBufferStrategy ();
	    Graphics2D g = (Graphics2D) bf.getDrawGraphics ();
	    g.setPaint (gradient);
	    g.fillRect (0, 0, frame.getWidth (), frame.getHeight ());
	}
	public void fade (Color change)
	{
	    for (float x = 0.0f ; x < 1.0 ; x += 0.05)
	    {
		Graphics2D g = (Graphics2D) bf.getDrawGraphics ();
		g.setComposite (AlphaComposite.getInstance (AlphaComposite.SRC_OVER, x));
		g.setRenderingHint (RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g.setColor (change);
		g.fillRect (0, 0, frame.getWidth (), frame.getHeight ());
		g.dispose ();
		Toolkit.getDefaultToolkit ().sync ();
		bf.show ();
		try
		{
		    Thread.sleep (100);
		}
		catch (InterruptedException ie)
		{
		}
	    }
	}
	public void draw (int switchDistance, int height, String text, int leftOrRight)
	{
	    int speed = 50 * leftOrRight;
	    if (leftOrRight == 1)
	    {
		for (int y = 0 ; y < 690 ; y += speed)
		{
		    Graphics2D g = (Graphics2D) bf.getDrawGraphics ();
		    g.setFont (new Font ("courier", Font.BOLD + Font.ITALIC, 125));
		    if (y == switchDistance)
		    {
			speed = 1;
		    }
		    else if (y == switchDistance + 50)
		    {
			speed = 50;
		    }
		    g.setPaint (gradient);
		    g.fillRect (0, 0, frame.getWidth (), frame.getHeight ());
		    g.setColor (TC);
		    g.drawString (text, 0 + y, height);
		    g.dispose ();
		    Toolkit.getDefaultToolkit ().sync ();
		    bf.show ();
		    try
		    {
			Thread.sleep (10);
		    }
		    catch (InterruptedException ie)
		    {
		    }
		}
	    }
	    else
	    {
		for (int y = 640 ; y > 0 ; y += speed)
		{
		    Graphics2D g = (Graphics2D) bf.getDrawGraphics ();
		    g.setFont (new Font ("courier", Font.BOLD + Font.ITALIC, 125));
		    if (640 - y == switchDistance)
		    {
			speed = -1;
		    }
		    else if (640 - y == switchDistance + 50)
		    {
			speed = -50;
		    }
		    g.setPaint (gradient);
		    g.fillRect (0, 0, frame.getWidth (), frame.getHeight ());
		    g.setColor (TC);
		    g.drawString (text, y, height);
		    g.dispose ();
		    Toolkit.getDefaultToolkit ().sync ();
		    bf.show ();
		    try
		    {
			Thread.sleep (10);
		    }
		    catch (InterruptedException ie)
		    {
		    }
		}
	    }

	}
	public void drawWords ()
	{
	    fade (Color.BLACK);
	    draw (200, 200, "THIS", 1);
	    draw (400, 250, "IS", -1);
	    draw (300, 300, "A", 1);
	    try
	    {
		Thread.sleep (1000);
	    }
	    catch (InterruptedException ie)
	    {
	    }
	    Graphics2D g = (Graphics2D) bf.getDrawGraphics ();
	    g.setPaint (gradient);
	    g.fillRect (0, 0, frame.getWidth (), frame.getHeight ());
	    for (int x = 255 ; x > 0 ; x--)
	    {
		g = (Graphics2D) bf.getDrawGraphics ();
		g.setFont (new Font ("courier", Font.BOLD + Font.ITALIC, 75));
		g.setColor (new Color (x, x, x));
		g.drawString ("Totally", 162, 150);
		g.drawString ("Not An", 185, 200);
		g.drawString ("ISP", 252, 250);
		g.drawString ("Production", 95, 300);
		g.dispose ();
		Toolkit.getDefaultToolkit ().sync ();
		bf.show ();
		try
		{
		    Thread.sleep (10);
		}
		catch (InterruptedException ie)
		{
		}

	    }

	    fade (BG);
	    fade (Color.BLACK);
	    frame.repaint ();
	}
    }


    public Animation (JFrame frameToDrawIn)
    {
	AnimationDrawing t = new AnimationDrawing (frameToDrawIn);
	t.drawWords ();
    }
} // Animation class


