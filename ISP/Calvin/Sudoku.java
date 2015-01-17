// Calvin Chan
// Ms. Dyke
// January 17th, 2012
// This is a sudoku game that can generate puzzles, save, load and show highscores.
// The "Sudoku" class.
import java.awt.*;
import java.io.*;
import javax.swing.*;
import javax.swing.text.*;
import javax.swing.border.*;
import java.awt.event.*;
import java.util.Random;
import java.util.StringTokenizer;
import java.util.ArrayList;
public class Sudoku implements ActionListener, MouseWheelListener
{
    JFrame frame;

    JPanel[] [] place;
    JPanel[] [] footnotes;
    JPanel[] [] savePlace;
    JPanel[] [] saveFootnotes;
    JPanel[] gridTwo;
    JPanel[] saveGridTwo;

    JPanel animationPanel;
    JPanel introPanel;
    JPanel mainMenuScreen;
    JPanel screen;
    JPanel newGameScreen;
    JPanel loadGameScreen;
    JPanel gameInformation;
    JPanel gameInfo;
    JPanel centredTitle;
    JPanel fileNamePanel;
    JPanel playGameScreen;
    JPanel grid;
    JPanel saveGrid;
    JPanel sidePanel;
    JPanel sidePanelButtons;
    JPanel bottomPanel;
    JPanel loadOrDelete;
    JPanel highscoreScreen;
    JPanel scores;
    JPanel highscoresTitlePanel;
    JPanel highscoresMenuPanel;
    JPanel easyMediumHard;
    JPanel highscoresTable;
    JPanel namesTable;
    JPanel timesTable;
    JPanel namesAndTimesTable;
    JPanel instructionsPanel;
    JPanel creditsPanel;

    JButton[] [] gridButtons;
    JButton[] panelButtons;
    JButton[] saveGridButtons;
    JButton introToMainMenu;
    JButton newGameButton;
    JButton loadGameButton;
    JButton viewHighscores;
    JButton exitButton;
    JButton easyButton;
    JButton mediumButton;
    JButton hardButton;
    JButton deleteButton;
    JButton restartButton;
    JButton exitAndSave;
    JButton exitWithoutSaving;
    JButton load;
    JButton delete;
    JButton back;
    JButton highscoresBack;
    JButton instructions;
    JButton credits;
    JButton instructionsBack;
    JButton creditsBack;

    JLabel[] [] [] footNotes;
    JLabel[] [] [] saveFootNotes;
    JLabel[] [] saveButtons;
    JLabel[] [] namesTimes;

    JLabel introTitle;
    JLabel chooseDifficulty;
    JLabel mainMenuTitle;
    JLabel newGameTitle;
    JLabel loadGameTitle;
    JLabel fileName;
    JLabel chosen;
    JLabel numberChosen;
    JLabel timerDisplay;
    JLabel gameInfoTimer;
    JLabel gameInfoDifficulty;
    JLabel highscoresTitle;
    JLabel scoresTitles;
    JLabel nameTitle;
    JLabel timeTitle;
    JLabel instructionsTitle;
    JLabel creditsTitle;

    JScrollBar loading;
    JScrollBar highscoresBar;

    JTextPane instructionsDisplay;
    JTextPane creditsDisplay;
    JScrollPane instructionScroll;
    JScrollPane creditsScroll;

    JProgressBar progressBar;

    Timer timer;
    int timerCount;
    int difficulty;
    String numberSet = "1";
    int[] [] solved;
    int[] [] missing;
    int[] [] status;
    //0 is blank;
    //1 is filled, cannot be changed
    //2 is filled
    File[] saveFiles;
    final int EASY = 80;
    final int MEDIUM = 33;
    final int HARD = 27;
    final Color BG = new Color (135, 206, 255); //Background Colour
    final Color TC = new Color (74, 112, 139); //Title Colour

    Border etchedBorder = BorderFactory.createEtchedBorder (EtchedBorder.RAISED);
    //Constructor
    public Sudoku ()
    {
	frame = new JFrame ("SUDOKU");
	frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
	frame.setResizable (false);
	Object[] yesOrNo = {"Yes, Please!", "No Way!"};
	if (JOptionPane.showOptionDialog (null, "Would you like to not have the border showing?", "Hello There!", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, yesOrNo, yesOrNo [0]) == 0)
	{
	    frame.setUndecorated (true);
	}
	Dimension dim = Toolkit.getDefaultToolkit ().getScreenSize ();
	int x = (dim.width - 640) / 2;
	int y = (dim.height - 500) / 2;
	frame.setLocation (x, y);
	mainMenuScreen = new JPanel ();
	mainMenuScreen.setLayout (new CardLayout ());
	frame.getContentPane ().add (mainMenuScreen);
	mainMenuScreen.setPreferredSize (new Dimension (640, 500));
	mainMenuScreen.setBorder (etchedBorder);
	frame.setVisible (true);
    }


    //Increase the value of the progress bar
    protected void increaseProgressBar (int increase)
    {
	progressBar.setValue (progressBar.getValue () + increase);
    }


    //Add all components to the jframe
    public void screenDesign ()
    {
	// 527 lines
	// Intro 18 8
	// Animation 3 1
	// Instructions 25 4
	// Credits 32 4
	// Main Menu 48 14
	// New Game 19 10
	// Load Game 55 53
	// Play Game 64 96
	// Highscores 52 21

	//*****INTRO*****
	introPanel = new JPanel ();
	introPanel.setLayout (new BoxLayout (introPanel, BoxLayout.PAGE_AXIS));
	introPanel.setBorder (javax.swing.BorderFactory.createEmptyBorder (0, 5, 25, 5));
	introPanel.setBackground (BG);
	introTitle = new JLabel ("Sudoku");
	introTitle.setFont (new Font ("Calibri", Font.PLAIN, 100));
	introTitle.setAlignmentX (Component.CENTER_ALIGNMENT);
	introTitle.setForeground (TC);
	progressBar = new JProgressBar (0, 527);
	progressBar.setStringPainted (true);
	progressBar.setAlignmentX (Component.CENTER_ALIGNMENT);
	introToMainMenu = new JButton ("GO");
	introToMainMenu.addActionListener (this);
	introToMainMenu.setAlignmentX (Component.CENTER_ALIGNMENT);
	introToMainMenu.setEnabled (false);
	introToMainMenu.setFont (new Font ("couriernew", Font.PLAIN, 100));
	introToMainMenu.setVisible (false);
	Component tempHold = Box.createRigidArea (new Dimension (190, 137));
	increaseProgressBar (18);
	//Adding
	mainMenuScreen.add (introPanel, "Intro");
	introPanel.add (introTitle);
	introPanel.add (Box.createRigidArea (new Dimension (0, 50)));
	introPanel.add (tempHold);
	introPanel.add (introToMainMenu);
	introPanel.add (Box.createRigidArea (new Dimension (0, 100)));
	introPanel.add (progressBar);
	frame.pack ();
	increaseProgressBar (8);
	//*****AnimationPanel*****
	animationPanel = new JPanel ();
	animationPanel.setBackground (BG);
	animationPanel.setDoubleBuffered (true);
	//Adding
	mainMenuScreen.add (animationPanel, "Animation");
	increaseProgressBar (4);
	//*****Instructions*****
	instructionsPanel = new JPanel ();
	instructionsPanel.setLayout (new BorderLayout ());
	instructionsPanel.setBorder (javax.swing.BorderFactory.createEmptyBorder (0, 5, 25, 8));
	instructionsPanel.setBackground (BG);
	instructionsTitle = new JLabel ("Sudoku");
	instructionsTitle.setHorizontalAlignment (SwingConstants.CENTER);
	instructionsTitle.setFont (new Font ("Calibri", Font.PLAIN, 100));
	instructionsTitle.setForeground (TC);
	instructionsDisplay = new JTextPane ();
	instructionsDisplay.setBorder (etchedBorder);
	try
	{
	    java.net.URL imgURL = getClass ().getResource ("instructions.html");
	    instructionsDisplay.setPage (imgURL);
	}
	catch (IOException ioe)
	{
	    JOptionPane.showMessageDialog (null, "You somehow managed to mess up the instructions.\nInstructions will not be displayed in-game.");
	}
	instructionsDisplay.setEditable (false);
	instructionScroll = new JScrollPane (instructionsDisplay);
	instructionScroll.setVerticalScrollBarPolicy (JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
	instructionsBack = new JButton ("Back");
	instructionsBack.setAlignmentX (Component.RIGHT_ALIGNMENT);
	instructionsBack.addActionListener (this);
	increaseProgressBar (25);
	//Adding
	mainMenuScreen.add (instructionsPanel, "Instructions");
	instructionsPanel.add (instructionsTitle, BorderLayout.NORTH);
	instructionsPanel.add (instructionScroll, BorderLayout.CENTER);
	instructionsPanel.add (instructionsBack, BorderLayout.SOUTH);
	increaseProgressBar (4);
	//*****Credits*****
	creditsPanel = new JPanel ();
	creditsPanel.setLayout (new BorderLayout ());
	creditsPanel.setBorder (javax.swing.BorderFactory.createEmptyBorder (0, 5, 25, 8));
	creditsPanel.setBackground (BG);
	creditsTitle = new JLabel ("Sudoku");
	creditsTitle.setFont (new Font ("Calibri", Font.PLAIN, 100));
	creditsTitle.setHorizontalAlignment (SwingConstants.CENTER);
	creditsTitle.setForeground (TC);
	creditsDisplay = new JTextPane ();
	creditsDisplay.setEditable (false);
	creditsDisplay.setBorder (etchedBorder);
	creditsDisplay.setFont (new Font ("Calibri", Font.PLAIN, 15));
	creditsDisplay.setText ("Everything was done by: ");
	try
	{
	    StyledDocument doc = creditsDisplay.getStyledDocument ();
	    Random ran = new Random ();
	    String myName = "Calvin Chan";
	    for (int x = 0 ; x < myName.length () ; x++)
	    {
		SimpleAttributeSet colorFul = new SimpleAttributeSet ();
		StyleConstants.setForeground (colorFul, new Color (ran.nextInt (256), ran.nextInt (256), ran.nextInt (256)));
		StyleConstants.setFontSize (colorFul, 75);
		doc.insertString (doc.getLength (), Character.toString (myName.charAt (x)), colorFul);
	    }
	}
	catch (Throwable badLocation)
	{
	}
	creditsBack = new JButton ("Back");
	creditsBack.setAlignmentX (Component.RIGHT_ALIGNMENT);
	creditsBack.addActionListener (this);
	increaseProgressBar (32);
	//Adding
	mainMenuScreen.add (creditsPanel, "Credits");
	creditsPanel.add (creditsTitle, BorderLayout.NORTH);
	creditsPanel.add (creditsDisplay, BorderLayout.CENTER);
	creditsPanel.add (creditsBack, BorderLayout.SOUTH);
	increaseProgressBar (4);
	//*****Main Menu*****
	screen = new JPanel ();
	screen.setLayout (new BoxLayout (screen, BoxLayout.PAGE_AXIS));
	screen.setBackground (BG);
	mainMenuTitle = new JLabel ("Sudoku");
	mainMenuTitle.setFont (new Font ("Calibri", Font.PLAIN, 100));
	mainMenuTitle.setForeground (TC);
	newGameButton = new JButton ("New Game");
	//**************************ACCELERATOR
	newGameButton.getInputMap (JComponent.WHEN_IN_FOCUSED_WINDOW).put (KeyStroke.getKeyStroke (KeyEvent.VK_N, 0), "OMG");
	newGameButton.getActionMap ().put ("OMG", new AbstractAction ("OMG")
	{
	    public void actionPerformed (ActionEvent evt)
	    {
		actionPerformedTransfer (evt);
	    }
	}
	);
	//END OF ACCELERATOR
	//**************************RIGHT CLICK
	newGameButton.addMouseListener (new MouseAdapter ()
	{
	    public void mouseClicked (MouseEvent e)
	    {
		ActionEvent k = new ActionEvent (e.getSource (), e.getID (), e.paramString ());
		actionPerformedTransfer (k);
	    }
	}
	);
	//END OF RIGHT CLICK
	newGameButton.addActionListener (this);
	loadGameButton = new JButton ("Load Game");
	loadGameButton.addActionListener (this);
	instructions = new JButton ("Instructions");
	instructions.addActionListener (this);
	credits = new JButton ("Credits");
	credits.addActionListener (this);
	viewHighscores = new JButton ("View Highscores");
	viewHighscores.addActionListener (this);
	exitButton = new JButton ("Exit Game");
	exitButton.addActionListener (this);
	mainMenuTitle.setAlignmentX (Component.CENTER_ALIGNMENT);
	newGameButton.setAlignmentX (Component.CENTER_ALIGNMENT);
	loadGameButton.setAlignmentX (Component.CENTER_ALIGNMENT);
	instructions.setAlignmentX (Component.CENTER_ALIGNMENT);
	credits.setAlignmentX (Component.CENTER_ALIGNMENT);
	viewHighscores.setAlignmentX (Component.CENTER_ALIGNMENT);
	exitButton.setAlignmentX (Component.CENTER_ALIGNMENT);
	increaseProgressBar (48);
	//Adding
	mainMenuScreen.add (screen, "MainMenu");
	screen.add (mainMenuTitle);
	screen.add (Box.createRigidArea (new Dimension (25, 25)));
	screen.add (newGameButton);
	screen.add (Box.createRigidArea (new Dimension (25, 25)));
	screen.add (loadGameButton);
	screen.add (Box.createRigidArea (new Dimension (25, 25)));
	screen.add (instructions);
	screen.add (Box.createRigidArea (new Dimension (25, 25)));
	screen.add (credits);
	screen.add (Box.createRigidArea (new Dimension (25, 25)));
	screen.add (viewHighscores);
	screen.add (Box.createRigidArea (new Dimension (25, 25)));
	screen.add (exitButton);
	increaseProgressBar (14);
	//*****New Game Menu*****
	newGameScreen = new JPanel ();
	newGameScreen.setLayout (new BoxLayout (newGameScreen, BoxLayout.PAGE_AXIS));
	newGameScreen.setBackground (BG);
	newGameTitle = new JLabel ("Sudoku");
	newGameTitle.setFont (new Font ("Calibri", Font.PLAIN, 100));
	newGameTitle.setForeground (TC);
	chooseDifficulty = new JLabel ("Choose A Difficulty");
	chooseDifficulty.setFont (new Font ("Calibri", Font.PLAIN, 30));
	easyButton = new JButton ("Easy");
	easyButton.addActionListener (this);
	mediumButton = new JButton ("Medium");
	mediumButton.addActionListener (this);
	hardButton = new JButton ("Hard");
	hardButton.addActionListener (this);
	newGameTitle.setAlignmentX (Component.CENTER_ALIGNMENT);
	chooseDifficulty.setAlignmentX (Component.CENTER_ALIGNMENT);
	easyButton.setAlignmentX (Component.CENTER_ALIGNMENT);
	mediumButton.setAlignmentX (Component.CENTER_ALIGNMENT);
	hardButton.setAlignmentX (Component.CENTER_ALIGNMENT);
	increaseProgressBar (19);
	//Adding
	mainMenuScreen.add (newGameScreen, "NewGame");
	newGameScreen.add (newGameTitle);
	newGameScreen.add (Box.createRigidArea (new Dimension (25, 25)));
	newGameScreen.add (chooseDifficulty);
	newGameScreen.add (Box.createRigidArea (new Dimension (25, 25)));
	newGameScreen.add (easyButton);
	newGameScreen.add (Box.createRigidArea (new Dimension (25, 25)));
	newGameScreen.add (mediumButton);
	newGameScreen.add (Box.createRigidArea (new Dimension (25, 25)));
	newGameScreen.add (hardButton);
	increaseProgressBar (10);
	//*****Load Game*****
	mainMenuScreen.setFocusable (true);
	loadGameScreen = new JPanel ();
	loadGameScreen.setLayout (new BorderLayout (5, 5));
	loadGameScreen.setBorder (javax.swing.BorderFactory.createEmptyBorder (0, 5, 25, 8));
	loadGameScreen.setBackground (BG);
	loadGameScreen.addMouseWheelListener (this);
	loadGameTitle = new JLabel ("Sudoku");
	loadGameTitle.setFont (new Font ("Calibri", Font.PLAIN, 100));
	loadGameTitle.setForeground (TC);
	loadGameTitle.setHorizontalAlignment (SwingConstants.CENTER);
	loading = new JScrollBar (JScrollBar.VERTICAL, 0, 1, 0, 10);
	loading.addAdjustmentListener (new AdjustmentListener ()
	{
	    public void adjustmentValueChanged (AdjustmentEvent evt)
	    {
		scrollBarChanged (evt);
	    }
	}
	);
	gameInformation = new JPanel ();
	gameInformation.setBackground (BG);
	gameInformation.setLayout (new BorderLayout (5, 5));
	gameInfo = new JPanel ();
	gameInfo.setBackground (BG);
	gameInfo.setLayout (new BoxLayout (gameInfo, BoxLayout.PAGE_AXIS));
	gameInformation.setBorder (etchedBorder);
	saveGrid = new JPanel ();
	saveGrid.setBackground (BG);
	saveGrid.setLayout (new GridLayout (3, 3));
	fileNamePanel = new JPanel ();
	fileNamePanel.setBackground (BG);
	fileNamePanel.setLayout (new BoxLayout (fileNamePanel, BoxLayout.PAGE_AXIS));
	fileName = new JLabel ("");
	fileName.setFont (new Font ("Calibri", Font.PLAIN, 20));
	gameInfoTimer = new JLabel ("");
	gameInfoTimer.setFont (new Font ("Calibri", Font.PLAIN, 20));
	gameInfoDifficulty = new JLabel ("");
	gameInfoDifficulty.setFont (new Font ("Calibri", Font.PLAIN, 20));
	loadOrDelete = new JPanel ();
	loadOrDelete.setBackground (BG);
	loadOrDelete.setLayout (new FlowLayout (FlowLayout.CENTER, 50, 5));
	load = new JButton ("Load this Game");
	load.addActionListener (this);
	delete = new JButton ("Delete this Game");
	delete.addActionListener (this);
	back = new JButton ("Back to Main Menu");
	back.addActionListener (this);
	saveGridTwo = new JPanel [9];
	saveButtons = new JLabel [9] [9];
	savePlace = new JPanel [9] [9];
	saveFootnotes = new JPanel [9] [9];
	saveFootNotes = new JLabel [9] [9] [9];
	fileName.setAlignmentX (Component.CENTER_ALIGNMENT);
	gameInfo.setBorder (etchedBorder);
	gameInfoTimer.setAlignmentX (Component.CENTER_ALIGNMENT);
	gameInfoDifficulty.setAlignmentX (Component.CENTER_ALIGNMENT);
	increaseProgressBar (55);
	//Adding
	mainMenuScreen.add (loadGameScreen, "LoadGame");
	fileNamePanel.add (fileName);
	loadGameScreen.add (loadGameTitle, BorderLayout.NORTH);
	loadGameScreen.add (loading, BorderLayout.WEST);
	loadGameScreen.add (gameInformation, BorderLayout.CENTER);
	gameInformation.add (fileNamePanel, BorderLayout.NORTH);
	gameInformation.add (saveGrid, BorderLayout.CENTER);
	gameInformation.add (gameInfo, BorderLayout.EAST);
	gameInfo.add (Box.createRigidArea (new Dimension (75, 75)));
	gameInfo.add (gameInfoTimer);
	gameInfo.add (Box.createRigidArea (new Dimension (75, 75)));
	gameInfo.add (gameInfoDifficulty);
	gameInformation.add (loadOrDelete, BorderLayout.SOUTH);
	loadOrDelete.add (load);
	loadOrDelete.add (delete);
	loadOrDelete.add (back);
	for (int x = 0 ; x < 9 ; x++)
	{
	    saveGridTwo [x] = new JPanel (new GridLayout (3, 3));
	    saveGridTwo [x].setBorder (BorderFactory.createLineBorder (Color.black, 1));
	    saveGrid.add (saveGridTwo [x]);
	    for (int y = 0 ; y < 3 ; y++)
	    {
		for (int z = 0 ; z < 3 ; z++)
		{
		    int one = (int) (x / 3) * 3 + y;
		    int two = x % 3 * 3 + z;
		    saveButtons [one] [two] = new JLabel ();
		    saveButtons [one] [two].setBorder (BorderFactory.createLineBorder (new Color (211, 211, 211), 1));
		    saveButtons [one] [two].setFont (new Font ("couriernew", Font.PLAIN, 30));
		    saveButtons [one] [two].setOpaque (true);
		    saveButtons [one] [two].setHorizontalAlignment (SwingConstants.CENTER);
		    saveButtons [one] [two].setBackground (Color.WHITE);
		    saveFootnotes [one] [two] = new JPanel ();
		    savePlace [one] [two] = new JPanel ();
		    saveFootnotes [one] [two].setLayout (new GridLayout (3, 3));
		    saveFootnotes [one] [two].setBorder (BorderFactory.createLineBorder (new Color (211, 211, 211), 1));
		    saveFootnotes [one] [two].setBackground (Color.WHITE);
		    for (int a = 0 ; a < 9 ; a++)
		    {
			saveFootNotes [one] [two] [a] = new JLabel ("");
			saveFootNotes [one] [two] [a].setFont (new Font ("couriernew", Font.PLAIN, 10));
			saveFootNotes [one] [two] [a].setForeground (Color.BLUE);
			saveFootNotes [one] [two] [a].setHorizontalAlignment (SwingConstants.CENTER);
			saveFootnotes [one] [two].add (saveFootNotes [one] [two] [a]);
		    }
		    savePlace [one] [two].setLayout (new CardLayout ());
		    savePlace [one] [two].add (saveFootnotes [one] [two], "fn");
		    savePlace [one] [two].add (saveButtons [one] [two], "n");
		    saveGridTwo [x].add (savePlace [one] [two]);
		}
	    }
	}
	increaseProgressBar (53);
	//*****Play Game*****
	playGameScreen = new JPanel ();
	playGameScreen.setLayout (new BorderLayout (5, 5));
	playGameScreen.setBackground (BG);
	grid = new JPanel ();
	grid.setLayout (new GridLayout (3, 3));
	sidePanel = new JPanel ();
	sidePanel.setLayout (new BoxLayout (sidePanel, BoxLayout.PAGE_AXIS));
	sidePanel.setBackground (BG);
	timer = new Timer (1000, new AbstractAction ()
	{
	    public void actionPerformed (ActionEvent evt)
	    {
		timerControl ();
	    }
	}
	);
	timer.setRepeats (true);
	timerDisplay = new JLabel ("");
	timerDisplay.setFont (new Font ("Calibri", Font.PLAIN, 20));
	sidePanelButtons = new JPanel ();
	sidePanelButtons.setLayout (new GridLayout (3, 3));
	chosen = new JLabel ("Chosen:");
	chosen.setFont (new Font ("Calibri", Font.PLAIN, 20));
	numberChosen = new JLabel (numberSet);
	numberChosen.setFont (new Font ("Calibri", Font.PLAIN, 30));
	numberChosen.setBorder (BorderFactory.createLineBorder (Color.black));
	deleteButton = new JButton ("DELETE");
	deleteButton.addActionListener (new AbstractAction ("num")
	{
	    public void actionPerformed (ActionEvent evt)
	    {
		panelController (evt);
	    }
	}
	);
	deleteButton.getInputMap (JComponent.WHEN_IN_FOCUSED_WINDOW).put (KeyStroke.getKeyStroke (KeyEvent.VK_0, 0), "num");
	deleteButton.getInputMap (JComponent.WHEN_IN_FOCUSED_WINDOW).put (KeyStroke.getKeyStroke (KeyEvent.VK_NUMPAD0, 0), "num");
	deleteButton.getActionMap ().put ("num", new AbstractAction ("num")
	{
	    public void actionPerformed (ActionEvent evt)
	    {
		panelController (evt);
	    }
	}
	);
	bottomPanel = new JPanel ();
	bottomPanel.setLayout (new FlowLayout (FlowLayout.CENTER, 50, 5));
	bottomPanel.setBackground (BG);
	restartButton = new JButton ("Clear Puzzle");
	restartButton.addActionListener (this);
	exitAndSave = new JButton ("Save and Exit");
	exitAndSave.addActionListener (this);
	exitWithoutSaving = new JButton ("Exit without Saving");
	exitWithoutSaving.addActionListener (this);
	gridTwo = new JPanel [9];
	place = new JPanel [9] [9];
	footnotes = new JPanel [9] [9];
	gridButtons = new JButton [9] [9];
	footNotes = new JLabel [9] [9] [9];
	panelButtons = new JButton [9];
	timerDisplay.setAlignmentX (Component.CENTER_ALIGNMENT);
	deleteButton.setAlignmentX (Component.CENTER_ALIGNMENT);
	numberChosen.setAlignmentX (Component.CENTER_ALIGNMENT);
	chosen.setAlignmentX (Component.CENTER_ALIGNMENT);
	increaseProgressBar (64);
	//Adding
	playGameScreen.add (grid, BorderLayout.CENTER);
	playGameScreen.add (sidePanel, BorderLayout.EAST);
	playGameScreen.add (bottomPanel, BorderLayout.SOUTH);
	sidePanel.add (Box.createRigidArea (new Dimension (25, 25)));
	sidePanel.add (timerDisplay);
	sidePanel.add (Box.createRigidArea (new Dimension (25, 25)));
	sidePanel.add (sidePanelButtons);
	mainMenuScreen.add (playGameScreen, "PlayGame");
	for (int x = 0 ; x < 9 ; x++)
	{
	    gridTwo [x] = new JPanel (new GridLayout (3, 3));
	    gridTwo [x].setBorder (BorderFactory.createLineBorder (Color.black, 1));
	    grid.add (gridTwo [x]);
	    for (int y = 0 ; y < 3 ; y++)
	    {
		for (int z = 0 ; z < 3 ; z++)
		{
		    int one = (int) (x / 3) * 3 + y;
		    int two = x % 3 * 3 + z;
		    //System.out.println (one + " " + two);
		    gridButtons [one] [two] = new JButton ();
		    gridButtons [one] [two].setBorder (BorderFactory.createLineBorder (new Color (211, 211, 211), 1));
		    gridButtons [one] [two].setFont (new Font ("couriernew", Font.PLAIN, 30));
		    gridButtons [one] [two].setBackground (Color.WHITE);
		    gridButtons [one] [two].addActionListener (new ActionListener ()
		    {
			public void actionPerformed (ActionEvent e)
			{
			    gridController (e);
			}
		    }
		    );

		    footnotes [one] [two] = new JPanel ();
		    place [one] [two] = new JPanel ();
		    footnotes [one] [two].setLayout (new GridLayout (3, 3));
		    footnotes [one] [two].setBorder (BorderFactory.createLineBorder (new Color (211, 211, 211), 1));
		    footnotes [one] [two].setBackground (Color.WHITE);
		    footnotes [one] [two].addMouseListener (new MouseAdapter ()
		    {
			public void mousePressed (MouseEvent e)
			{
			    ActionEvent k = new ActionEvent (e.getSource (), e.getID (), e.paramString (), e.getModifiers ());
			    footNotesController (k);

			}
		    }
		    );
		    for (int a = 0 ; a < 9 ; a++)
		    {
			footNotes [one] [two] [a] = new JLabel ("");
			footNotes [one] [two] [a].setFont (new Font ("couriernew", Font.PLAIN, 15));
			footNotes [one] [two] [a].setForeground (Color.BLUE);
			footNotes [one] [two] [a].setHorizontalAlignment (SwingConstants.CENTER);
			footnotes [one] [two].add (footNotes [one] [two] [a]);
		    }
		    place [one] [two].setLayout (new CardLayout ());
		    place [one] [two].add (footnotes [one] [two], "fn");
		    place [one] [two].add (gridButtons [one] [two], "n");
		    gridTwo [x].add (place [one] [two]);
		}
	    }
	}
	for (int x = 0 ; x < 9 ; x++)
	{
	    panelButtons [x] = new JButton (Integer.toString (x + 1));
	    panelButtons [x].addActionListener (new AbstractAction ("num")
	    {
		public void actionPerformed (ActionEvent evt)
		{
		    panelController (evt);
		}
	    }
	    );
	    panelButtons [x].getInputMap (JComponent.WHEN_IN_FOCUSED_WINDOW).put (KeyStroke.getKeyStroke (KeyEvent.VK_1 + x, 0), "num");
	    panelButtons [x].getInputMap (JComponent.WHEN_IN_FOCUSED_WINDOW).put (KeyStroke.getKeyStroke (KeyEvent.VK_NUMPAD1 + x, 0), "num");
	    panelButtons [x].getActionMap ().put ("num", new AbstractAction ("num")
	    {
		public void actionPerformed (ActionEvent evt)
		{
		    panelController (evt);
		}
	    }
	    );
	    sidePanelButtons.add (panelButtons [x]);
	}
	sidePanel.add (Box.createRigidArea (new Dimension (10, 10)));
	sidePanel.add (deleteButton);
	sidePanel.add (Box.createVerticalGlue ());
	sidePanel.add (chosen);
	sidePanel.add (Box.createRigidArea (new Dimension (10, 10)));
	sidePanel.add (numberChosen);
	sidePanel.add (Box.createRigidArea (new Dimension (0, 100)));
	bottomPanel.add (restartButton);
	bottomPanel.add (exitAndSave);
	bottomPanel.add (exitWithoutSaving);
	increaseProgressBar (96);
	//*****HIGHSCORES*****
	highscoreScreen = new JPanel ();
	highscoreScreen.setLayout (new BorderLayout (5, 5));
	highscoreScreen.setBackground (BG);
	highscoresTitle = new JLabel ("HIGHSCORES");
	highscoresTitle.setHorizontalAlignment (SwingConstants.CENTER);
	highscoresTitle.setFont (new Font ("Calibri", Font.PLAIN, 100));
	highscoresTitle.setForeground (TC);
	highscoresBar = new JScrollBar (JScrollBar.VERTICAL, 1, 1, 0, 3);
	highscoreScreen.addMouseWheelListener (this);
	highscoresBar.addAdjustmentListener (new AdjustmentListener ()
	{
	    public void adjustmentValueChanged (AdjustmentEvent evt)
	    {
		highscoreBarMoved (evt);
	    }
	}
	);
	scoresTitles = new JLabel ("");
	scoresTitles.setHorizontalAlignment (SwingConstants.CENTER);
	scoresTitles.setFont (new Font ("Calibri", Font.PLAIN, 100));
	scores = new JPanel ();
	scores.setBackground (BG);
	scores.setLayout (new BorderLayout (5, 5));
	scores.setBorder (BorderFactory.createLineBorder (Color.BLACK));
	highscoresTable = new JPanel ();
	highscoresTable.setBackground (BG);
	highscoresTable.setLayout (new BoxLayout (highscoresTable, BoxLayout.PAGE_AXIS));
	namesAndTimesTable = new JPanel ();
	namesAndTimesTable.setBackground (BG);
	namesAndTimesTable.setLayout (new BorderLayout ());
	namesTable = new JPanel ();
	namesTable.setBackground (BG);
	namesTable.setLayout (new BoxLayout (namesTable, BoxLayout.PAGE_AXIS));
	namesTable.setBorder (BorderFactory.createLineBorder (Color.BLACK));
	namesTable.setPreferredSize (new Dimension (400, 209));
	timesTable = new JPanel ();
	timesTable.setBackground (BG);
	timesTable.setLayout ((new BoxLayout (timesTable, BoxLayout.PAGE_AXIS)));
	timesTable.setBorder (BorderFactory.createLineBorder (Color.BLACK));
	timesTable.setPreferredSize (new Dimension (212, 209));
	nameTitle = new JLabel (" Name");
	nameTitle.setFont (new Font ("Calibri", Font.PLAIN, 40));
	nameTitle.setPreferredSize (namesTable.getSize ());
	timeTitle = new JLabel (" Time");
	timeTitle.setFont (new Font ("Calibri", Font.PLAIN, 40));
	timeTitle.setPreferredSize (timesTable.getSize ());
	highscoresMenuPanel = new JPanel ();
	highscoresMenuPanel.setBackground (BG);
	highscoresMenuPanel.setLayout (new BoxLayout (highscoresMenuPanel, BoxLayout.PAGE_AXIS));
	highscoresBack = new JButton ("Back to Main Menu");
	highscoresBack.addActionListener (this);
	highscoresBack.setAlignmentX (Component.RIGHT_ALIGNMENT);
	increaseProgressBar (52);
	//Adding
	mainMenuScreen.add (highscoreScreen, "HighScores");
	highscoreScreen.add (highscoresTitle, BorderLayout.NORTH);
	highscoreScreen.add (highscoresBar, BorderLayout.WEST);
	highscoreScreen.add (scores, BorderLayout.CENTER);
	scores.add (scoresTitles, BorderLayout.NORTH);
	scores.add (namesAndTimesTable, BorderLayout.CENTER);
	namesAndTimesTable.add (namesTable, BorderLayout.WEST);
	namesAndTimesTable.add (timesTable, BorderLayout.EAST);
	scores.add (highscoresMenuPanel, BorderLayout.SOUTH);
	highscoresMenuPanel.add (highscoresBack);
	namesTable.add (nameTitle);
	timesTable.add (timeTitle);
	namesTimes = new JLabel [2] [10];
	for (int x = 0 ; x < 10 ; x++)
	{
	    namesTimes [0] [x] = new JLabel ("");
	    namesTimes [1] [x] = new JLabel ("");
	    namesTable.add (namesTimes [0] [x]);
	    timesTable.add (namesTimes [1] [x]);
	}
	highscoresBar.setValue (0);
	increaseProgressBar (21);
	//Enabling Go button
	introPanel.remove (tempHold);
	introToMainMenu.setEnabled (true);
	introToMainMenu.setVisible (true);
    }


    //If the mouse wheel is moved
    public void mouseWheelMoved (MouseWheelEvent e)
    {
	JPanel temp = (JPanel) (e.getSource ());
	try
	{
	    if (temp == loadGameScreen)
	    {
		if (e.getWheelRotation () < 0)
		{
		    loading.setValue (loading.getValue () - 1);
		}
		else
		{
		    loading.setValue (loading.getValue () + 1);
		}
	    }
	    else
	    {
		if (e.getWheelRotation () < 0)
		{
		    highscoresBar.setValue (highscoresBar.getValue () - 1);
		}
		else
		{
		    highscoresBar.setValue (highscoresBar.getValue () + 1);
		}
	    }
	}


	catch (NullPointerException npe)
	{
	}
    }


    //If the mouse wheel is moved inside the highscore window
    protected void highscoreBarMoved (AdjustmentEvent evt)
    {
	Adjustable source = evt.getAdjustable ();
	try
	{
	    BufferedReader in = new BufferedReader (new FileReader ("Files/highscores.sudoku"));
	    if (!in.readLine ().equals ("CALVIN IS THE BEST!"))
	    {
		throw new NullPointerException ();
	    }
	    String[] names = new String [30];
	    int[] times = new int [30];
	    for (int x = 0 ; x < 30 ; x++)
	    {
		names [x] = in.readLine ();
		times [x] = Integer.parseInt (in.readLine ());
	    }
	    for (int y = source.getValue () * 10 ; y < (source.getValue () + 1) * 10 ; y++)
	    {
		String seconds = Integer.toString (times [y] % 60);
		String minutes = Integer.toString ((int) (times [y] / 60) % 60);
		String hours = Integer.toString ((int) ((times [y] / 60) / 60));
		if (Integer.parseInt (seconds) < 10)
		    seconds = "0" + seconds;
		if (Integer.parseInt (minutes) < 10)
		    minutes = "0" + minutes;
		if (Integer.parseInt (hours) < 10)
		    hours = "0" + hours;
		if (y % 10 + 1 != 10)
		{
		    namesTimes [0] [y % 10].setText (" " + (y % 10 + 1) + ". " + names [y]);
		}
		else
		{
		    namesTimes [0] [y % 10].setText ("" + (y % 10 + 1) + ". " + names [y]);

		}
		namesTimes [1] [y % 10].setText (" " + hours + ":" + minutes + ":" + seconds);
	    }
	    in.close ();
	    if (source.getValue () == 0)
	    {
		scoresTitles.setText ("Easy");
	    }
	    else if (source.getValue () == 1)
	    {
		scoresTitles.setText ("Medium");
	    }
	    else
	    {
		scoresTitles.setText ("Hard");
	    }
	}

	catch (FileNotFoundException fnfe)
	{
	    JOptionPane.showMessageDialog (null, "A highscores file was not found.\nA new one will be created.");
	    createHighscores ();
	    highscoreBarMoved (evt);
	}
	catch (NumberFormatException nfe)
	{
	    JOptionPane.showMessageDialog (null, "The highscores file is corrupted.\nA new one will be created.");
	    createHighscores ();
	    highscoreBarMoved (evt);
	}
	catch (NullPointerException npe)
	{
	    JOptionPane.showMessageDialog (null, "The highscores file is corrupted.\nA new one will be created.");
	    createHighscores ();
	    highscoreBarMoved (evt);
	}
	catch (IOException ioe)
	{
	    if (JOptionPane.showConfirmDialog (null, "An error has occured while reading the highscores\nfile. Would you like to create a new one? \n(If no, then this error will probably continue to appear)", "Error!", JOptionPane.YES_NO_OPTION) == 0)
	    {
		createHighscores ();
		highscoreBarMoved (evt);
	    }
	    else
	    {
		highscoresBack.doClick ();
	    }
	}
    }


    //Controls the timer
    protected void timerControl ()
    {
	timerCount++;
	String seconds = Integer.toString (timerCount % 60);
	String minutes = Integer.toString ((int) (timerCount / 60) % 60);
	String hours = Integer.toString ((int) ((timerCount / 60) / 60));
	if (Integer.parseInt (seconds) < 10)
	    seconds = "0" + seconds;
	if (Integer.parseInt (minutes) < 10)
	    minutes = "0" + minutes;
	if (Integer.parseInt (hours) < 10)
	    hours = "0" + hours;
	timerDisplay.setText (hours + ":" + minutes + ":" + seconds);
    }

    //Finds files and loads games
    protected void loadGame (int runType)
    {
	if (runType == 0)
	{
	    File saveDirectory = new File ("Saves");
	    if (!saveDirectory.exists ())
	    {
		saveDirectory.mkdir ();
	    }
	    saveFiles = new File [0];
	    saveFiles = saveDirectory.listFiles ();
	    loading.setValues (loading.getValue (), 1, 0, saveFiles.length);
	}


	else if (runType == 1)
	{

	    try
	    {
		loadGame (0);
		BufferedReader in = new BufferedReader (new FileReader (saveFiles [loading.getValue ()]));
		missing = new int [9] [9];
		status = new int [9] [9];
		if (!in.readLine ().equals ("CALVIN IS THE BEST!"))
		{
		    JOptionPane.showMessageDialog (null, "You managed to corrupt the save file from the time you pressed load game.\nYou will now be returned to the main menu.");
		    in.close ();
		    back.doClick ();
		    return;
		}
		for (int x = 0 ; x < 9 ; x++)
		{
		    String hold = in.readLine ();
		    for (int y = 0 ; y < 9 ; y++)
		    {
			status [x] [y] = Integer.parseInt (Character.toString (hold.charAt (y)));
		    }
		}
		for (int x = 0 ; x < 9 ; x++)
		{
		    String hold = in.readLine ();
		    for (int y = 0 ; y < 9 ; y++)
		    {
			missing [x] [y] = Integer.parseInt (Character.toString (hold.charAt (y)));
		    }
		}
		for (int x = 0 ; x < 9 ; x++)
		{
		    for (int y = 0 ; y < 9 ; y++)
		    {
			String hold = in.readLine ();
			for (int z = 0 ; z < 9 ; z++)
			{
			    if (hold.charAt (z) != '0')
			    {
				footNotes [x] [y] [z].setText (Character.toString (hold.charAt (z)));
			    }
			    else
			    {
				footNotes [x] [y] [z].setText ("");

			    }
			}
		    }
		}
		timerCount = Integer.parseInt (in.readLine ());
		String diff = in.readLine ();
		if (diff.equals ("Easy"))
		{
		    difficulty = EASY;
		}
		else if (diff.equals ("Medium"))
		{
		    difficulty = MEDIUM;

		}
		else if (diff.equals ("Hard"))
		{
		    difficulty = HARD;
		}
		for (int a = 0 ; a < 9 ; a++)
		{
		    for (int b = 0 ; b < 9 ; b++)
		    {
			gridButtons [a] [b].setText ("");
			CardLayout temp2 = (CardLayout) (place [a] [b].getLayout ());
			temp2.show (place [a] [b], "fn");
			if (status [a] [b] != 1)
			{
			    gridButtons [a] [b].setForeground (Color.BLUE);
			    if (status [a] [b] == 2)
			    {
				gridButtons [a] [b].setText (Integer.toString (missing [a] [b]));
				CardLayout temp3 = (CardLayout) (place [a] [b].getLayout ());
				temp3.show (place [a] [b], "n");
			    }
			}
			else
			{
			    CardLayout temp3 = (CardLayout) (place [a] [b].getLayout ());
			    temp3.show (place [a] [b], "n");
			    gridButtons [a] [b].setText (Integer.toString (missing [a] [b]));
			    gridButtons [a] [b].setForeground (Color.BLACK);
			}
		    }
		}
		playGame ();
		CardLayout cl = (CardLayout) (mainMenuScreen.getLayout ());
		cl.show (mainMenuScreen, "PlayGame");
		in.close ();
	    }
	    catch (IOException ioe)
	    {
		JOptionPane.showMessageDialog (null, "An error occured while loading! Your\ngame has not been loaded sucessfully!");
		back.doClick ();
	    }
	}


	else
	{
	    loadGame (0);
	    if (JOptionPane.showConfirmDialog (null, "Are you sure you wish\nto delete this save game?", "Are you sure?", JOptionPane.YES_NO_OPTION) == 0)
	    {
		if (!saveFiles [loading.getValue ()].delete ())
		{
		    JOptionPane.showMessageDialog (null, "An Error has occured while deleting the file!");
		}
		loadGame (0);
	    }
	    loading.setValue (0);
	    if (saveFiles.length < 1)
	    {
		back.doClick ();
	    }
	}
    }

    //If the mouse wheel is moved inside the load game window
    protected void scrollBarChanged (AdjustmentEvent evt)
    {
	loadGame (0);
	Adjustable source = evt.getAdjustable ();
	try
	{
	    int value = source.getValue ();
	    BufferedReader in = new BufferedReader (new FileReader (saveFiles [value]));
	    if (!in.readLine ().equals ("CALVIN IS THE BEST!"))
	    {
		in.close ();
		JOptionPane.showMessageDialog (null, "This save file is corrupted.\n It will now be deleted.");
		if (!saveFiles [value].delete ())
		{
		    JOptionPane.showMessageDialog (null, "I don't know what you are doing, but you managed to mess up the delete process too.\n I don't know what's going to happen now.");
		}
		loadGame (0);
		back.doClick ();
		return;
	    }
	    //System.out.println (in.readLine ());
	    int index = saveFiles [value].getName ().lastIndexOf ('.');
	    if (index > 0 && index <= saveFiles [value].getName ().length () - 2)
	    {
		fileName.setText (saveFiles [value].getName ().substring (0, index));
	    }
	    int[] [] numbers = new int [9] [9];
	    for (int x = 0 ; x < 9 ; x++)
	    {
		String hold = in.readLine ();
		for (int y = 0 ; y < 9 ; y++)
		{
		    numbers [x] [y] = Integer.parseInt ((Character.toString (hold.charAt (y))));
		}
	    }

	    for (int x = 0 ; x < 9 ; x++)
	    {
		String hold = in.readLine ();
		for (int y = 0 ; y < 9 ; y++)
		{
		    CardLayout temp2 = (CardLayout) (savePlace [x] [y].getLayout ());
		    temp2.show (savePlace [x] [y], "fn");
		    saveButtons [x] [y].setText ("");
		    if (numbers [x] [y] == 1)
		    {
			saveButtons [x] [y].setForeground (Color.BLACK);
			saveButtons [x] [y].setText (Character.toString (hold.charAt (y)));
			temp2.show (savePlace [x] [y], "n");
		    }
		    else if (numbers [x] [y] == 2)
		    {
			saveButtons [x] [y].setForeground (Color.BLUE);
			saveButtons [x] [y].setText (Character.toString (hold.charAt (y)));
			temp2.show (savePlace [x] [y], "n");
		    }
		}
	    }
	    for (int x = 0 ; x < 9 ; x++)
	    {
		for (int y = 0 ; y < 9 ; y++)
		{
		    String hold = in.readLine ();
		    for (int z = 0 ; z < 9 ; z++)
		    {
			if (hold.charAt (z) != '0')
			{
			    saveFootNotes [x] [y] [z].setText (Character.toString (hold.charAt (z)));
			}
			else
			{
			    saveFootNotes [x] [y] [z].setText ("");
			}
		    }
		}
	    }
	    int saveTimer = Integer.parseInt (in.readLine ());
	    String seconds = Integer.toString (saveTimer % 60);
	    String minutes = Integer.toString ((int) (saveTimer / 60) % 60);
	    String hours = Integer.toString ((int) ((saveTimer / 60) / 60));
	    if (Integer.parseInt (seconds) < 10)
		seconds = "0" + seconds;
	    if (Integer.parseInt (minutes) < 10)
		minutes = "0" + minutes;
	    if (Integer.parseInt (hours) < 10)
		hours = "0" + hours;
	    gameInfoTimer.setText (hours + ":" + minutes + ":" + seconds);
	    gameInfoDifficulty.setText (in.readLine ());
	    in.close ();
	}


	catch (IOException ioe)
	{
	}


	catch (ArrayIndexOutOfBoundsException ioe)
	{
	}
    }

    //Creates a new game
    protected void newGame ()
    {
	solved = new int [9] [9];
	gridGenerator (0, solved);
	status = new int [9] [9];
	missing = new int [9] [9];
	for (int a = 0 ; a < 9 ; a++)
	{
	    missing [a] = (int[]) (solved [a].clone ());
	}


	for (int a = 0 ; a < 9 ; a++)
	{
	    for (int b = 0 ; b < 9 ; b++)
	    {
		status [a] [b] = 1;
		gridButtons [a] [b].setForeground (Color.BLACK);
	    }
	}


	Random ran = new Random ();
	for (int a = 0 ; a < 81 - difficulty ; a++)
	{
	    int one = (ran.nextInt (9));
	    int two = (ran.nextInt (9));
	    if (missing [one] [two] != 0)
	    {
		gridButtons [one] [two].setForeground (Color.BLUE);
		status [one] [two] = 0;
		missing [one] [two] = 0;
	    }
	    else
	    {
		a--;
	    }
	}


	for (int a = 0 ; a < 9 ; a++)
	{
	    for (int b = 0 ; b < 9 ; b++)
	    {
		for (int c = 0 ; c < 9 ; c++)
		{
		    footNotes [a] [b] [c].setText ("");
		}
		if (missing [a] [b] != 0)
		{
		    gridButtons [a] [b].setText (Integer.toString (missing [a] [b]));
		    CardLayout temp2 = (CardLayout) (place [a] [b].getLayout ());
		    temp2.show (place [a] [b], "n");
		}
		else
		{
		    CardLayout temp2 = (CardLayout) (place [a] [b].getLayout ());
		    temp2.show (place [a] [b], "fn");
		    gridButtons [a] [b].setText ("");
		}
	    }
	}


	timerCount = 0;
    }

    //Generates a puzzle
    protected boolean gridGenerator (int counter, int[] [] gridd)
    {
	int[] [] hold = new int [9] [9];
	for (int x = 0 ; x < 9 ; x++)
	{
	    hold [x] = (int[]) (gridd [x].clone ());
	}

	int x = (int) (counter / 9);
	int y = counter % 9;
	int[] nums = new int [9];
	Random ran = new Random ();
	ArrayList num = new ArrayList ();
	for (int z = 1 ; z < 10 ; z++)
	{
	    num.add (Integer.toString (z));
	}


	for (int z = 0 ; z < 9 ; z++)
	{
	    while (true)
	    {
		nums [z] = (ran.nextInt (9) + 1);
		if (num.contains (Integer.toString (nums [z])))
		{
		    num.remove (Integer.toString (nums [z]));
		    break;
		}
	    }
	}


	for (int z = 0 ; z < 9 ; z++)
	{
	    hold [x] [y] = (nums [z]);
	    boolean none = true;
	    for (int a = 0 ; a < 9 ; a++)
	    {
		if (y != a && hold [x] [a] == nums [z])
		{
		    none = false;
		    break;
		}
		if (x != a && hold [a] [y] == nums [z])
		{
		    none = false;
		    break;
		}
	    }
	    if (none)
	    {
		for (int c = (int) (x / 3) * 3 ; c < (int) (x / 3) * 3 + 3 ; c++)
		{
		    if (none)
		    {
			for (int d = (int) (y / 3) * 3 ; d < (int) (y / 3) * 3 + 3 ; d++)
			{
			    if (c != x && d != y && hold [c] [d] == nums [z])
			    {
				none = false;
				break;
			    }
			}
		    }
		}
	    }
	    if (none)
	    {
		if (counter != 80)
		{
		    if (gridGenerator (counter + 1, hold))
		    {
			solved [x] [y] = nums [z];
			return (true);
		    }
		}
		else
		{
		    solved [x] [y] = nums [z];
		    return (true);
		}
	    }
	}
	return (false);
    }

    //Starts the timer
    protected void playGame ()
    {
	timer.start ();
    }

    //Controls the footnotes
    protected void footNotesController (ActionEvent e)
    {
	JPanel temp = (JPanel) (e.getSource ());
	for (int x = 0 ; x < 9 ; x++)
	{
	    for (int y = 0 ; y < 9 ; y++)
	    {
		if (temp == footnotes [x] [y])
		{
		    if (numberSet.equals ("0"))
		    {
			for (int a = 0 ; a < 9 ; a++)
			{
			    footNotes [x] [y] [a].setText ("");
			}
			return;
		    }
		    if (e.getModifiers () == 4)
		    {

			if (footNotes [x] [y] [Integer.parseInt (numberSet) - 1].getText ().equals (""))
			{
			    footNotes [x] [y] [Integer.parseInt (numberSet) - 1].setText (numberSet);
			}
			else
			{
			    footNotes [x] [y] [Integer.parseInt (numberSet) - 1].setText ("");
			}
			return;
		    }
		    if (e.getModifiers () == 16)
		    {
			if (missing [x] [y] == 0)
			{
			    CardLayout temp2 = (CardLayout) (place [x] [y].getLayout ());
			    temp2.show (place [x] [y], "n");
			    gridButtons [x] [y].doClick ();
			}
		    }
		    return;
		}
	    }
	}
    }

    //Controls the grid
    protected void gridController (ActionEvent e)
    {
	JButton temp = (JButton) (e.getSource ());
	for (int x = 0 ; x < 9 ; x++)
	{
	    for (int y = 0 ; y < 9 ; y++)
	    {
		if (temp == gridButtons [x] [y])
		{
		    if (status [x] [y] != 1)
		    {
			if (gridButtons [x] [y].getText ().equals (numberSet) || numberSet.equals ("0"))
			{
			    status [x] [y] = 0;
			    missing [x] [y] = 0;
			    gridButtons [x] [y].setText ("");
			    CardLayout temp2 = (CardLayout) (place [x] [y].getLayout ());
			    temp2.show (place [x] [y], "fn");
			}
			else
			{
			    status [x] [y] = 2;
			    missing [x] [y] = Integer.parseInt (numberSet);
			    gridButtons [x] [y].setText (numberSet);
			    check ();
			}
			return;
		    }
		}
	    }
	}
    }

    //Saves the puzzle
    protected void savePuzzle ()
    {
	try
	{
	    while (true)
	    {
		boolean write = true;
		String name = JOptionPane.showInputDialog ("Please enter the filename\nyou would like to save with:");
		if (name == null)
		{
		    break;
		}
		if (name != null && !name.equals (""))
		{
		    loadGame (0);
		    for (int x = 0 ; x < saveFiles.length ; x++)
		    {
			if (saveFiles [x].getName ().equals (name + ".txt"))
			{
			    write = false;
			}
		    }
		    if (!write && JOptionPane.showConfirmDialog (null, "Would you like to overwrite\nthe save file?", "A save file with the same name already exists!", JOptionPane.YES_NO_OPTION) == 0)
		    {
			write = true;
		    }
		    if (write)
		    {
			File files = new File ("Saves");
			if (!files.exists ())
			{
			    files.mkdir ();
			}
			PrintWriter out = new PrintWriter (new FileWriter ("Saves/" + name + ".txt"));
			out.println ("CALVIN IS THE BEST!");
			for (int x = 0 ; x < 9 ; x++)
			{
			    for (int y = 0 ; y < 9 ; y++)
			    {
				out.print (status [x] [y]);
			    }
			    out.println ();
			}
			for (int x = 0 ; x < 9 ; x++)
			{
			    for (int y = 0 ; y < 9 ; y++)
			    {
				out.print (missing [x] [y]);
			    }
			    out.println ();
			}
			for (int x = 0 ; x < 9 ; x++)
			{
			    for (int y = 0 ; y < 9 ; y++)
			    {
				for (int z = 0 ; z < 9 ; z++)
				{
				    if (footNotes [x] [y] [z].getText ().equals (""))
				    {
					out.print (0);
				    }
				    else
				    {
					out.print (footNotes [x] [y] [z].getText ());
				    }
				}
				out.println ();
			    }
			}
			out.println (timerCount);
			if (difficulty == EASY)
			{
			    out.println ("Easy");
			}
			else if (difficulty == MEDIUM)
			{
			    out.println ("Medium");
			}
			else
			{
			    out.println ("Hard");
			}
			out.close ();
			exitWithoutSaving.doClick ();
			break;
		    }

		}
	    }
	}


	catch (IOException ioe)
	{
	    JOptionPane.showMessageDialog (null, "An error occured while saving! Your\ngame has not been saved sucessfully!");
	}
    }

    //Clears the puzzle
    protected void restartPuzzle ()
    {
	if (JOptionPane.showConfirmDialog (null, "Are you sure you wish\nto clear the puzzle?", "Are you sure?", JOptionPane.YES_NO_OPTION) == 0)
	{
	    for (int x = 0 ; x < 9 ; x++)
	    {
		for (int y = 0 ; y < 9 ; y++)
		{
		    for (int z = 0 ; z < 9 ; z++)
		    {
			footNotes [x] [y] [z].setText ("");
		    }
		    if (status [x] [y] == 2)
		    {
			missing [x] [y] = 0;
			gridButtons [x] [y].setText ("");
		    }
		}
	    }
	}
    }

    //Checks if completed
    protected void check ()
    {
	boolean done = true;
	for (int x = 0 ; x < 9 ; x++)
	{
	    int one = 0;
	    int two = 0;
	    for (int y = 0 ; y < 9 ; y++)
	    {
		one += missing [x] [y];
		two += missing [y] [x];
	    }
	    if (one != 45 || two != 45)
	    {
		done = false;
		x = 10;
		break;
	    }
	}


	if (done)
	{
	    timer.stop ();
	    JOptionPane.showMessageDialog (null, "Congratulations! You finished in " + timerCount + " seconds!");
	    if (!checkHighscores (difficulty, timerCount))
	    {
		CardLayout cl = (CardLayout) (mainMenuScreen.getLayout ());
		cl.show (mainMenuScreen, "MainMenu");
	    }

	}
    }

    //Creates a new highscore file
    protected void createHighscores ()
    {
	try
	{
	    File files = new File ("Files");
	    if (!files.exists ())
	    {
		files.mkdir ();
	    }
	    PrintWriter out = new PrintWriter (new FileWriter ("Files/highscores.sudoku"));
	    out.println ("CALVIN IS THE BEST!");
	    for (int x = 0 ; x < 30 ; x++)
	    {
		out.println ("No one");
		out.println ("0");
	    }
	    out.close ();
	}
	catch (IOException ioe)
	{
	    JOptionPane.showMessageDialog (null, "An error occured while creating a new highscore file!");
	}
    }

    //Returns the integer
    protected int returnVariable (int one, int two, int three)
    {
	return (one / two * three);
    }

    //Checks if the user made the highscores
    protected boolean checkHighscores (int level, int time)
    {
	try
	{
	    boolean ifNewScore = false;
	    File files = new File ("Files");
	    if (!files.exists ())
	    {
		files.mkdir ();
	    }
	    BufferedReader in = new BufferedReader (new FileReader ("Files/highscores.sudoku"));
	    int[] scores = new int [30];
	    String[] names = new String [30];
	    if (!in.readLine ().equals ("CALVIN IS THE BEST!"))
	    {
		throw new NumberFormatException ();
	    }
	    for (int x = 0 ; x < 30 ; x++)
	    {
		names [x] = in.readLine ();
		scores [x] = Integer.parseInt (in.readLine ());
	    }
	    in.close ();
	    int diffic;
	    if (level == EASY)
	    {
		diffic = 20;
	    }
	    else if (level == MEDIUM)
	    {
		diffic = 10;
	    }
	    else
	    {
		diffic = 0;
	    }
	    for (int x = 20 - diffic ; x < 30 - diffic ; x++)
	    {
		if (scores [x] >= time || scores [x] == 0)
		{
		    ifNewScore = true;
		    for (int y = 29 - diffic ; y > x ; y--)
		    {
			scores [y] = scores [y - 1];
			names [y] = names [y - 1];
		    }
		    String name;
		    while (true)
		    {
			name = JOptionPane.showInputDialog ("You got a new highscore! Please enter your username:");
			boolean notAllSpaces = true;
			for (int g = 0 ; g < name.length () ; g++)
			{
			    if (name.charAt (g) == ' ')
			    {
				notAllSpaces = false;
				break;
			    }
			}
			if (!name.equals ("") && name != null && name.length () < 61 && notAllSpaces)
			{
			    break;
			}
			JOptionPane.showMessageDialog (null, "Invalid username (Make sure it is under 60 characters), Please try again");
		    }
		    scores [x] = time;
		    names [x] = name;
		    break;
		}
	    }
	    if (ifNewScore)
	    {
		File file = new File ("Files");
		if (!file.exists ())
		{
		    file.mkdir ();
		}
		PrintWriter out = new PrintWriter (new FileWriter ("Files/highscores.sudoku"));
		out.println ("CALVIN IS THE BEST!");
		for (int x = 0 ; x < 30 ; x++)
		{
		    out.println (names [x]);
		    out.println (scores [x]);
		}
		out.close ();
		highscoresBar.setValue (1);
		highscoresBar.setValue (0);
		viewHighscores.doClick ();
		return (true);
	    }
	    else
	    {
		return (false);
	    }

	}
	catch (FileNotFoundException blah)
	{
	    JOptionPane.showMessageDialog (null, "The highscores file was not found.\nA new one will be created.");
	    createHighscores ();
	    checkHighscores (level, time);
	}

	catch (IOException ioe)
	{
	    JOptionPane.showMessageDialog (null, "An error occured.\nA new highscores file will be created.");
	    createHighscores ();
	    checkHighscores (level, time);
	}


	catch (NumberFormatException nfe)
	{
	    JOptionPane.showMessageDialog (null, "The highscores file is corrupted.\nA new one will be created.");
	    createHighscores ();
	    checkHighscores (level, time);
	}


	catch (NullPointerException npe)
	{
	    JOptionPane.showMessageDialog (null, "The highscores file is corrupted.\nA new one will be created.");
	    createHighscores ();
	    checkHighscores (level, time);
	}
	return (false);
    }

    //Controls the panel
    protected void panelController (ActionEvent e)
    {
	JButton temp = (JButton) (e.getSource ());
	for (int x = 0 ; x < 9 ; x++)
	{
	    if (temp == panelButtons [x])
	    {
		numberSet = Integer.toString (x + 1);
	    }
	}


	if (temp == deleteButton)
	{
	    numberSet = Integer.toString (0);
	    numberChosen.setText ("Delete");
	}


	else
	    numberChosen.setText (numberSet);
    }


    protected void actionPerformedTransfer (ActionEvent e)
    {
	actionPerformed (e);
    }

    //Controls the buttons
    public void actionPerformed (ActionEvent e)
    {
	JButton temp = (JButton) (e.getSource ());
	CardLayout cl = (CardLayout) (mainMenuScreen.getLayout ());
	if (temp == newGameButton)
	{
	    cl.show (mainMenuScreen, "NewGame");
	}


	else if (temp == loadGameButton)
	{
	    loadGame (0);
	    if (saveFiles.length > 0)
	    {
		cl.show (mainMenuScreen, "LoadGame");
	    }
	    else
	    {
		JOptionPane.showMessageDialog (null, "There are no save games!");
	    }
	}
	else if (temp == introToMainMenu)
	{
	    cl.show (mainMenuScreen, "Animation");
	    Animation t = new Animation (frame);
	    t.start ();
	    try
	    {
		t.join ();
		cl.show (mainMenuScreen, "MainMenu");
	    }
	    catch (InterruptedException ie)
	    {
	    }
	}

	else if (temp == viewHighscores)
	{
	    highscoresBar.setValue (0);
	    cl.show (mainMenuScreen, "HighScores");
	}


	else if (temp == exitButton)
	{
	    System.exit (0);
	}


	else if (temp == easyButton)
	{
	    difficulty = EASY;
	    cl.show (mainMenuScreen, "PlayGame");
	    newGame ();
	    playGame ();
	}


	else if (temp == mediumButton)
	{
	    difficulty = MEDIUM;
	    cl.show (mainMenuScreen, "PlayGame");
	    newGame ();
	    playGame ();
	}


	else if (temp == hardButton)
	{
	    difficulty = HARD;
	    cl.show (mainMenuScreen, "PlayGame");
	    newGame ();
	    playGame ();
	}


	else if (temp == restartButton)
	{
	    restartPuzzle ();
	}


	else if (temp == exitAndSave)
	{
	    savePuzzle ();
	}


	else if (temp == exitWithoutSaving || temp == back || temp == highscoresBack || temp == instructionsBack || temp == creditsBack)
	{
	    cl.show (mainMenuScreen, "MainMenu");
	}


	else if (temp == load)
	{
	    loadGame (1);
	}


	else if (temp == delete)
	{
	    loadGame (2);
	}


	else if (temp == instructions)
	{
	    cl.show (mainMenuScreen, "Instructions");

	}


	else
	{
	    if (temp == credits)
	    {
		cl.show (mainMenuScreen, "Credits");
	    }
	}
    }

    //Start of main method
    public static void main (String[] args)
    {
	Sudoku t = new Sudoku ();
	t.screenDesign ();
    }
    //End of main method
} // Sudoku class

