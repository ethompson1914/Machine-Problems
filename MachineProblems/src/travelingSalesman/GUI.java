package travelingSalesman;

// Imports
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.geom.Point2D;
import java.io.IOException;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

public class GUI extends JFrame implements ActionListener {

	// Instance variables
	private final JProgressBar progressBar;
	private MouseAdapter mouse;
	public double x;
	public double y;
	Salesman dude;
	CityList cityList;

	// Main method
	public static void main(String[] args) throws IOException {
		GUI gui = new GUI();
	}

	// Overrides the paint method
	public void Paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		Point2D.Double point = new Point2D.Double(100, 0);
	}

	// Contructor
	public GUI() {
		cityList = new CityList(
				"C:\\Users\\Eric\\Desktop\\CS Programs\\MachineProblems\\bin\\travelingSalesman\\CityTestData12Cities.txt");

		// Creating the container
		this.setTitle("MP12 Traveling Salesman");
		this.setLocation(400, 200);
		this.setPreferredSize(new Dimension(500, 500));
		Container myContainer = this.getContentPane();
		myContainer.setLayout(new BoxLayout(myContainer, BoxLayout.Y_AXIS));
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		// Create the progress bar
		progressBar = new JProgressBar(0, 100);
		progressBar.setValue(0);
		progressBar.setStringPainted(true);
		progressBar.setVisible(true);

		// Create thread to do the calculation
		dude = new Salesman(cityList, progressBar, this);
		final Thread t = new Thread(dude);
		t.start();

		// Create the cancel button
		JButton cancel = new JButton("Cancel");
		cancel.addMouseListener(mouse);
		myContainer.add(cancel);
		cancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				t.stop();
			}
		});

		// Create and add the panel that holds the progress bar and cancel
		// button
		JPanel progressBarAndCancel = new JPanel();
		progressBarAndCancel.add(progressBar);
		progressBarAndCancel.add(cancel);

		// Adds the two components to the container
		myContainer.add(progressBarAndCancel);
		myContainer.add(new GraphingData(cityList.getListOfCities()));

		// Changes the display
		this.pack();
		this.setVisible(true);
		this.paint(getGraphics());

	}

	// Blank method - This method is only needed for the program to compile
	// correctly
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// Blank method
	}
}