import javax.swing.*;
import java.awt.event.*;
// The "TimerControl" class.
public class TimerControl
{
    int x = 0;
    Timer blah;
    public TimerControl ()
    {
	blah = new Timer (1000, new AbstractAction ()
	{
	    public void actionPerformed (ActionEvent evt)
	    {
		x++;
		timerControl ();
	    }
	}
	);
	blah.setRepeats (true);
	blah.start ();
	System.out.println (blah.isRunning ());
	while (true)
	{
	}
    }


    public void timerControl ()
    {
	System.out.println (x);
    }


    public static void main (String[] args)
    {
	TimerControl t = new TimerControl ();
	// Place your code here
    } // main method
} // TimerControl class
