package travelingSalesman;

//Imports
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.util.ArrayList;

import javax.swing.JPanel;

public class GraphingData extends JPanel implements ActionListener {

	// Instance variables
	private static ArrayList<City> cities;
	int PAD = 10;

	// Contructor
	public GraphingData(ArrayList<City> theList) {
		cities = theList;
	}

	//
	public static void setCities(ArrayList<City> cities) {
		GraphingData.cities = cities;
	}

	// Overrides paint
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		// Finds the min latitude point and min longitude point for scaling
		double minX = Integer.MAX_VALUE;
		for (int k = 0; k < cities.size(); k++) {
			if (cities.get(k).getLongitude() < minX)
				minX = cities.get(k).getLongitude();
		}
		double minY = Integer.MAX_VALUE;
		for (int k = 0; k < cities.size(); k++) {
			if (cities.get(k).getLatitude() < minY)
				minY = cities.get(k).getLatitude();
		}

		// Finds the max latitude point and max longitude point for scaling
		double maxX = -Integer.MAX_VALUE;
		for (int k = 0; k < cities.size(); k++) {
			if (cities.get(k).getLongitude() > maxX)
				maxX = cities.get(k).getLongitude();
		}
		double maxY = -Integer.MAX_VALUE;
		for (int k = 0; k < cities.size(); k++) {
			if (cities.get(k).getLatitude() > maxY)
				maxY = cities.get(k).getLatitude();
		}

		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);

		// Scale factors
		double w = getWidth();
		double h = getHeight();
		double scaleX = w / (maxX);
		double scaleY = h / (maxY);

		// Draws lines between the cities in the shortest path
		for (int k = 0; k < cities.size(); k++) {
			g2.setPaint(Color.GREEN);
			if (k == cities.size() - 1) {
				double x1 = w - (cities.get(k).getLongitude() * scaleX) + PAD;
				double y1 = h - (cities.get(k).getLatitude() * scaleY) + PAD;
				double x2 = w - (cities.get(0).getLongitude() * scaleX) + PAD;
				double y2 = h - (cities.get(0).getLatitude() * scaleY) + PAD;
				g2.draw(new Line2D.Double(x1, y1, x2, y2));
			} else {
				double x1 = w - (cities.get(k).getLongitude() * scaleX) + PAD;
				double y1 = h - (cities.get(k).getLatitude() * scaleY) + PAD;
				double x2 = w - (cities.get(k + 1).getLongitude() * scaleX)
						+ PAD;
				double y2 = h - (cities.get(k + 1).getLatitude() * scaleY)
						+ PAD;
				g2.draw(new Line2D.Double(x1, y1, x2, y2));
			}
		}

		// Mark data points.
		for (int k = 0; k < cities.size(); k++) {
			if (k == 0) {
				g2.setPaint(Color.BLUE);
				double x = w - (cities.get(k).getLongitude() * scaleX) + PAD;
				double y = h - (cities.get(k).getLatitude() * scaleY) + PAD;

				g2.fill(new Ellipse2D.Double(x - 2, y - 2, 4, 4));
				g2.setPaint(Color.BLACK);
				g2.drawString(cities.get(k).getNameOfCity(), (int) x, (int) y);
			} else {
				g2.setPaint(Color.RED);
				double x = w - (cities.get(k).getLongitude() * scaleX) + PAD;
				double y = h - (cities.get(k).getLatitude() * scaleY) + PAD;

				g2.fill(new Ellipse2D.Double(x - 2, y - 2, 4, 4));
				g2.setPaint(Color.BLACK);
				g2.drawString(cities.get(k).getNameOfCity(), (int) x, (int) y);
			}
		}

	}

	// Blank method - This method is only needed for the program to compile
	// correctly
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
	}
}
