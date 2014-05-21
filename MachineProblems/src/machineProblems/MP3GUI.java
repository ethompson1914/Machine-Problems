package machineProblems;
import java.awt.*;
import javax.swing.*;

public class MP3GUI extends JFrame {

	JPanel encryptDecrypt;
	JTextArea encryptedMessage;
	
	JComboBox types;
	
	JComboBox rotationFactor;
	JPanel rot;
	JPanel keyBox;
	JTextArea message;
	JPanel bottom;
	
	public MP3GUI() {
		
		// Creates the window
		this.setTitle("MP3");
		this.setLocation(400, 200);
        Container myContainer = this.getContentPane();
        myContainer.setLayout(new BoxLayout(myContainer, BoxLayout.Y_AXIS));
        
        // Declare text area for the encrypted message
        encryptedMessage = new JTextArea(7,40);
        
        //Declare the ComboBox for choosing the encrypting type
        types = new JComboBox(new String[] {"None", "Rotational", "Keyed Rotational"});
        
        // Declare the ComboBox for choosing the rotation factor
        rotationFactor = new JComboBox(new String[] {"1","2","3","4","5","6","7","8","9","10","11","12",
        		"13","14","15","16","17","18","19","20","21","22","23","24","25","26"});
        rot = new JPanel();
        rot.add(new JLabel("Rotation:"));
        rot.add(rotationFactor);
        
        // Adds a text box to enter the key
        keyBox = new JPanel();
        keyBox.add(new JLabel("Key:"));
        keyBox.add(new JTextField(20));
        
        // Adds a text box to enter the message
        message = new JTextArea(7,40);
        
        // Adds a text box to enter the key
        encryptDecrypt = new JPanel();
        encryptDecrypt.add(new JButton("Encrypt"));
        encryptDecrypt.add(new JButton("Decrypt"));
        
        // Creates a panel for the bottom half of the GUI
        bottom = new JPanel();
        bottom.setLayout(new BoxLayout(bottom, BoxLayout.Y_AXIS));
        
        // Adds the components 
        bottom.add(rot);
        bottom.add(keyBox);
        bottom.add(message);
        bottom.add(encryptDecrypt);
        
        // Adds the components to the overall container
        myContainer.add(encryptedMessage);
        myContainer.add(types);
        myContainer.add(bottom);
        
        // Packs the container for display
        this.pack();
	}
}