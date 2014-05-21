package machineProblems;

/*
 *@author William_Wilson
 *@version 1.0
 *Created May 10, 2007
 *
 * Refactored by Eric Thompson
 * Refactored February 23, 2011
 */

/*
 *Import List
 */
import java.awt.Color;

import javax.swing.BoxLayout;
import javax.swing.JMenu;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.BorderFactory;

/*
 *Class Steganography_View
 */
public class Steganography_View extends JFrame
{
	// Size variables for window
	private static int WIDTH  = 500;
	private static int HEIGHT = 400;

	// Elements for JPanel
	private JLabel		pictureBox;
	private JTextArea	textBox;

	// Elements for Menu
	private JMenuItem 	open;
	private JMenuItem 	save;
	private JMenuItem 	exit;

	/*
	 *Constructor for Steganography_View class
	 *@param name Used to set the title on the JFrame
	 *
	 * As you can see, I used a BoxLayout instead of the GridBagLayout. I used
	 * this for the primary reason that I didn't understand the GridBagLayout
	 * and making a BoxLayout just was easier than trying to figure it out.
	 */
	public Steganography_View(String name)
	{
		// Set the title of the JFrame
		super(name);
		
		// Menubar
		JMenuBar menu = new JMenuBar();
		JMenu file = new JMenu("File");	file.setMnemonic('F');
		open = new JMenuItem("Open"); open.setMnemonic('O'); file.add(open);
		save = new JMenuItem("Save"); save.setMnemonic('S'); file.add(save);
		file.addSeparator();
		exit = new JMenuItem("Exit"); exit.setMnemonic('x'); file.add(exit);
		menu.add(file);
		setJMenuBar(menu);

		// Display rules
		setResizable(true);						// Allow window to be resized: true?false
		setBackground(Color.lightGray);			// Background color of window: Color(int,int,int) or Color.name
		setLocation(100,100);					// Location on the screen to display window
		setDefaultCloseOperation(EXIT_ON_CLOSE);// What to do on close operation: exit, do_nothing, etc
		setSize(WIDTH,HEIGHT);					// Set the size of the window
		
		// Create the box for the image
		pictureBox = new JLabel();
		pictureBox.setHorizontalAlignment(JLabel.CENTER);
		
		// Create the box for the text
		textBox = new JTextArea(7, 20);
		textBox.setLineWrap(true);
		textBox.setSize(100, 100);
		
		// Use a BoxLayout for the GUI
		BoxLayout layoutMgr = new BoxLayout(getContentPane(), BoxLayout.Y_AXIS);
		getContentPane().setLayout(layoutMgr);
		
		// Create vertical and horizontal scrollbars for the text if needed
		JScrollPane scroll1 = new JScrollPane(textBox,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED); 
		scroll1.setBorder(BorderFactory.createLineBorder(Color.CYAN,2));
		add(scroll1);
		
		// Create vertical and horizontal scrollbars for the image if needed
		JScrollPane scroll2 = new JScrollPane(pictureBox,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED); 
		scroll2.setBorder(BorderFactory.createLineBorder(Color.RED,2));
		add(scroll2);
	}

	/*
	 *@return The menu item 'Open'
	 */
	public JMenuItem getOpen()	{ return open;	}
	/*
	 *@return The menu item 'Save'
	 */
	public JMenuItem getSave()	{ return save;	}
	/*
	 *@return The menu item 'Exit'
	 */
	public JMenuItem getExit()	{ return exit;	}
	/*
	 *@return The TextArea containing the text to encode
	 */
	public JTextArea getTextBox()	{ return textBox;	}
	/*
	 *@return The JPanel displaying the Encode View
	 */
	public JLabel getPictureBox()	{ return pictureBox;	}
}