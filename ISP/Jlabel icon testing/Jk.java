// The "Jk" class.
import javax.swing.*;
import java.awt.*;
public class Jk extends JFrame
{
    JPanel lol = new JPanel ();
    JLabel blah = new JLabel ("lol");
    public Jk ()
    {
	setVisible (true);
	setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
	setTitle ("LOL");
	setResizable (false);
	lol.setPreferredSize (new Dimension (300, 300));
	
	java.net.URL imgURL = getClass ().getResource ("");
	ImageIcon icon = new ImageIcon (imgURL);
	blah.setIcon (icon);
	getContentPane ().add (lol);
	lol.add (blah);
	pack ();
    }


    public static void main (String[] args)
    {
	Jk t = new Jk ();
	// Place your code here
    } // main method
} // Jk class
