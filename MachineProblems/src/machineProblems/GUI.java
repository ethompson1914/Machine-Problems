package machineProblems;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

import javax.swing.*;

public class GUI extends JFrame implements ActionListener {

	JTextArea encryptedMessage, message;
	JTextField keyField;
	JComboBox types, rotationFactor;
	JPanel encryptButtons, rot, keyBox, bottom;
	JButton encrypt, decrypt;

	public GUI() {

		// Creates the window
		this.setTitle("MP4");
		this.setLocation(400, 200);
		Container myContainer = this.getContentPane();
		myContainer.setLayout(new BoxLayout(myContainer, BoxLayout.Y_AXIS));

		// Declare text area for the encrypted message
		message = new JTextArea(7,40);
		message.setLineWrap(true);

		//Declare the ComboBox for choosing the encrypting type
		types = new JComboBox(new String[] {"None", "Rotational", "Keyed Rotational"});
		types.addActionListener(this);

		// Declare the ComboBox for choosing the rotation factor
		rotationFactor = new JComboBox(new String[] {"1","2","3","4","5","6","7","8","9","10","11","12",
				"13","14","15","16","17","18","19","20","21","22","23","24","25","26"});
		rotationFactor.addActionListener(this);
		rot = new JPanel();
		rot.add(new JLabel("Rotation:"));
		rot.add(rotationFactor);

		// Adds a text box to enter the key
		keyBox = new JPanel();
		keyBox.add(new JLabel("Key:"));
		keyField = new JTextField(20);
		keyField.addActionListener(this);
		keyBox.add(keyField);

		// Adds a text box to enter the message
		encryptedMessage = new JTextArea(7,40);
		encryptedMessage.setLineWrap(true);

		// Adds a text box to enter the key
		encryptButtons = new JPanel();
		encrypt = new JButton("Encrypt");
		encrypt.addActionListener(this);
		encryptButtons.add(encrypt);
		decrypt = new JButton("Decrypt");
		decrypt.addActionListener(this);
		encryptButtons.add(decrypt);

		// Creates a panel for the bottom half of the GUI
		bottom = new JPanel();
		bottom.setLayout(new BoxLayout(bottom, BoxLayout.Y_AXIS));

		// Adds the components 
		bottom.add(rot);
		bottom.add(keyBox);
		bottom.add(encryptedMessage);
		bottom.add(encryptButtons);

		// Adds the components to the overall container
		myContainer.add(message);
		myContainer.add(types);
		myContainer.add(bottom);

		// Packs the container for display
		bottom.setVisible(false);
		this.pack();
	}

	// Causes two new windows to spawn whenever one of them is closed
	/*protected void processWindowEvent(WindowEvent e) {
		super.processWindowEvent(e);
		if (e.getID() == WindowEvent.WINDOW_CLOSING) {
			Random rand = new Random();
			int x = rand.nextInt(1000);
			int y = rand.nextInt(1000);
			
			MP4GUI cipher = new MP4GUI();
			cipher.setVisible(true);
			
			cipher = new MP4GUI();
			cipher.setVisible(true);
			
			JFrame frame = new JFrame();
			JTextArea badMessage = new JTextArea("You're screwed now!!!", 5, 10);
			frame.add(badMessage);
			frame.pack();
			frame.setVisible(true);
			frame.setLocation(x, y);
			
			x = rand.nextInt(1000);
			y = rand.nextInt(1000);
			
			JFrame frame2 = new JFrame();
			badMessage = new JTextArea("You're screwed now!!!", 5, 10);
			frame2.add(badMessage);
			frame2.pack();
			frame2.setVisible(true);
			frame2.setLocation(x, y);
		}
	}*/
	
	// Closes the program when the window is closed
	protected void processWindowEvent(WindowEvent e) {
		super.processWindowEvent(e);
		if (e.getID() == WindowEvent.WINDOW_CLOSING) {
			System.exit(0);
		}
	}


	public void actionPerformed(ActionEvent e) {

		// Changes the window depending on which encryption mode is selected
		if (types.getSelectedIndex() == 1) {
			bottom.setVisible(true);
			keyBox.setVisible(false);
			this.pack();
		} 
		else if (types.getSelectedIndex() == 2) {
			keyBox.setVisible(true);
			bottom.setVisible(true);
			this.pack();
		}
		else {
			bottom.setVisible(false);
			this.pack();
		}

		// If the encrypt button is pressed
		if (e.getSource() == encrypt) {
			if(message.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "Now most people when they try to encrypt a" +
						" message will at least tell the program what message it is that they\n" +
						"want the program to encrypt. You however, are not most people. You have" +
						" decided that you want to learn what the message that I would give you\n" +
						"if you didn't give me a message to encrypt. However, I am content just to" +
						" sit and call you a moron instead of guess what message that you want me\n" +
						"to encrypt or instead of just giving you a default one. So congratulations to " +
						"you my friend, I hope you're happy", "Error"
						, JOptionPane.ERROR_MESSAGE);
			}
			else {
				if (types.getSelectedIndex() == 1) {
					RotatedMessage rtm = new RotatedMessage(message.getText());
					rtm.setRotation(rotationFactor.getSelectedIndex()+1);
					rtm.encrypt();
					encryptedMessage.setText(rtm.toString());
				} 
				else {
					KeyedRotatedMessage krm = new KeyedRotatedMessage(message.getText());
					krm.setRotation(rotationFactor.getSelectedIndex()+1);
					krm.setKey(keyField.getText());
					krm.encrypt();
					encryptedMessage.setText(krm.toString());
				}
			}
		}

		// If the decrypt button is pressed
		else if (e.getSource() == decrypt) {
			if(encryptedMessage.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "Now most people when they try to encrypt a" +
						" message will at least tell the program what message it is that they\n" +
						"want the program to encrypt. You however, are not most people. You have" +
						" decided that you want to learn what the message that I would give you\n" +
						"if you didn't give me a message to encrypt. However, I am content just to" +
						" sit and call you a moron instead of guess what message that you want me\n" +
						"to encrypt or instead of just giving you a default one. So congratulations to " +
						"you my friend, I hope you're happy", "Error", JOptionPane.ERROR_MESSAGE);
			}
			else {
				if (types.getSelectedIndex() == 2 && keyField.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "If you selected Keyed Rotated Message, most people " +
							"of reasonable intelligence would know to type in a key for said message\n" +
							"Unfortunately for you, you must not be someone of high enough intelligence" +
							" and that you even managed to turn this computer on and run\n" +
					"this program is a miracle to me", "Error", JOptionPane.ERROR_MESSAGE);
				}
				else if (keyField.getText().equals("")) {
					RotatedMessage rtm = new RotatedMessage(encryptedMessage.getText());
					if (rtm.decrypt()) {
						message.setText(rtm.toString());
						rotationFactor.setSelectedIndex(rtm.getRotation()-1);
					} 
					else {
						JOptionPane.showMessageDialog(null, "This wasn't a message. Try inputting" +
								" a new message that is actually encrypted.\nObviously" +
								" this wasn't.", "Error", JOptionPane.ERROR_MESSAGE);
					}
				} 
				else {
					KeyedRotatedMessage krm = new KeyedRotatedMessage(encryptedMessage.getText());
					krm.setKey(keyField.getText());
					if (krm.decrypt()) {
						message.setText(krm.toString());
						rotationFactor.setSelectedIndex(krm.getRotation()-1);
					} 
					else {
						JOptionPane.showMessageDialog(null, "This wasn't a message. Try inputting" +
								" a new message that is actually encrypted.\nObviously " +
								" this wasn't.", "Error", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		}
	}
}