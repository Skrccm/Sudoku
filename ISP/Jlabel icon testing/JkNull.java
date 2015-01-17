// The "Jk" class.
import javax.swing.*;
import java.awt.*;
public class JkNull extends JFrame
{
    JPanel lol = new JPanel ();
    JLabel blah = new JLabel ();
    public JkNull ()
    {
	setVisible (true);
	setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
	lol.setLayout (null);
	lol.setPreferredSize (new Dimension (300, 300));
	java.net.URL imgURL = getClass ().getResource ("damage0.png");
	ImageIcon icon = new ImageIcon (imgURL);
	blah.setIcon (icon);
	blah.setBounds (0, 0, 37, 38);
	getContentPane ().add (lol);
	lol.add (blah);
	pack ();
	for (int x = 0 ; x < 200 ; x++)
	{
	    blah.setBounds (x, 0, 37, 38);
	    try
	    {
		Thread.sleep (10);
	    }
	    catch (InterruptedException ioe)
	    {
	    }
	}
	for (int x = 0 ; x < 200 ; x++)
	{
	    blah.setBounds (200, x, 37, 38);
	    try
	    {
		Thread.sleep (10);
	    }
	    catch (InterruptedException ioe)
	    {
	    }
	}
	for (int x = 0 ; x < 200 ; x++)
	{
	    blah.setBounds (200 - x, 200, 37, 38);
	    try
	    {
		Thread.sleep (10);
	    }
	    catch (InterruptedException ioe)
	    {
	    }
	}
	for (int x = 0 ; x < 200 ; x++)
	{
	    blah.setBounds (0, 200 - x, 37, 38);
	    try
	    {
		Thread.sleep (10);
	    }
	    catch (InterruptedException ioe)
	    {
	    }
	}
    }


    public static void main (String[] args)
    {
	JkNull t = new JkNull ();
	// Place your code here
    } // main method
} // Jk class
