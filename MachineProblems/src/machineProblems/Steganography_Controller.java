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
import java.io.File;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.JFileChooser;

/*
 *Steganography_Controller Class
 */
public class Steganography_Controller
{
	// Program variables
	private Steganography_View view;
	private Steganography model;

	// Action event classes
	private Open open;
	private Save save;

	// Decode variables
	private String stat_path = "";
	private String stat_name = "";

	/*
	 * Constructor to initialize view, model and environment variables
	 *@param aView  A GUI class, to be saved as view
	 *@param aModel A model class, to be saved as model
	 */
	public Steganography_Controller(Steganography_View aView, Steganography aModel)
	{
		//program variables
		view  = aView;
		model = aModel;

		//assign action events
		open = new Open();
		view.getOpen().addActionListener(open);
		save = new Save();
		view.getSave().addActionListener(save);
		view.getExit().addActionListener(new Exit());

		// Set the view as visible
        view.setVisible(true);
	}

	/*
	 * Open Class - handles the Open menu item
	 */
	private class Open implements ActionListener
	{
		/*
		 * Handles the click event
		 *@param e The ActionEvent Object
		 */
		public void actionPerformed(ActionEvent e)
		{
			// Start path of displayed File Chooser
			JFileChooser chooser = new JFileChooser("./");
			chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
			chooser.setFileFilter(new Image_Filter());
			int returnVal = chooser.showOpenDialog(view);
			if (returnVal == JFileChooser.APPROVE_OPTION){
				File directory = chooser.getSelectedFile();
				try{
					String image = directory.getPath();
					stat_name = directory.getName();
					stat_path = directory.getPath();
					stat_path = stat_path.substring(0,stat_path.length()-stat_name.length()-1);
					stat_name = stat_name.substring(0, stat_name.length()-4);
					view.getPictureBox().setIcon(new ImageIcon(ImageIO.read(new File(image))));
				}
				catch(Exception except) {
					// Message if opening the file fails
					JOptionPane.showMessageDialog(view, "The file cannot be opened!", 
							"Error!", JOptionPane.INFORMATION_MESSAGE);
				}
			}
			
			String message = model.decode(stat_path, stat_name);
			System.out.println(stat_path + ", " + stat_name);
			if(message != "")
			{
				view.getTextBox().setText(message);
			}
			else {
				view.getTextBox().setText("");
			}
			
			// Packs the window for display
			view.pack();
		}
	}

	/*
	 * Save Class - handles the Save menu item
	 */
	private class Save implements ActionListener
	{
		/*
		 * Handles the click event
		 *@param e The ActionEvent Object
		 */
		public void actionPerformed(ActionEvent e)
		{
			// Start path of displayed File Chooser
			JFileChooser chooser = new JFileChooser("./");
			chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
			chooser.setFileFilter(new Image_Filter());
			int returnVal = chooser.showSaveDialog(view);
			if (returnVal == JFileChooser.APPROVE_OPTION){
				File directory = chooser.getSelectedFile();
				try{
					String text = view.getTextBox().getText();
					String ext  = Image_Filter.getExtension(directory);
					String name = directory.getName();
					String path = directory.getPath();
					path = path.substring(0,path.length()-name.length()-1);
					name = name.substring(0, name.length()-4);

					String stegan = JOptionPane.showInputDialog(view,
							"Enter output file name:", "File name",
							JOptionPane.PLAIN_MESSAGE);

					if(model.encode(path,name,ext,stegan,text))
					{
						JOptionPane.showMessageDialog(view, "The image was encoded successfully!", 
								"Success!", JOptionPane.INFORMATION_MESSAGE);
						view.getTextBox().setText("");
					}
					else
					{
						JOptionPane.showMessageDialog(view, "The image could not be encoded!", 
								"Error!", JOptionPane.INFORMATION_MESSAGE);
					}
					// Display the new image
					view.getPictureBox().setIcon(new ImageIcon(ImageIO.read(new File(path + "/" + stegan + ".png"))));
				}
				catch(Exception except) {
					// Message if opening the file fails
					JOptionPane.showMessageDialog(view, "The file cannot be opened!", 
							"Error!", JOptionPane.INFORMATION_MESSAGE);
				}
			}
			
			// Packs the window for display
			view.pack();
		}
	}

	/*
	 *Exit Class - handles the Exit menu item
	 */
	private class Exit implements ActionListener
	{
		/*
		 *handles the click event
		 *@param e The ActionEvent Object
		 */
		public void actionPerformed(ActionEvent e)
		{
			System.exit(0); // Exit the program
		}
	}

	/*
	 *Main Method for testing
	 */
	public static void main(String args[])
	{
		new Steganography_Controller(
				new Steganography_View("Steganography"),
				new Steganography()
		);
	}
}