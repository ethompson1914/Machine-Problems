package travelingSalesman;

// Imports
import java.util.ArrayList;

import javax.swing.JProgressBar;

public class Salesman implements Runnable {

	// Instance variables
	CityList theList;
	int[] theBestRoute;
	double counter;
	JProgressBar progressBar;
	GUI gui;

	// Constructor
	public Salesman(CityList list, JProgressBar pBar, GUI window) {
		this.gui = window;
		theList = list;
		counter = 0;
		theBestRoute = new int[theList.listOfCities.size() + 1];
		progressBar = pBar;
	}

	// Computes the fastest path
	public ArrayList<City> computeFastest() {
		int[] tempIntArray = new int[theList.listOfCities.size() + 1];
		City city1 = new City(null, 0, 0);
		City city2 = new City(null, 0, 0);
		double totalDistance = 0;
		double distanceBetweenTwoCities = 0;
		double bestTotalDistance = 0;
		PermutationGenerator generator = new PermutationGenerator(theList
				.getListOfCities().size() - 1);

		while (generator.hasMore()) {
			generator.getNext();
			for (int k = 0; k < theList.listOfCities.size() - 1; k++) {
				tempIntArray[k + 1] = generator.a[k] + 1;
			}

			tempIntArray[0] = 0;
			tempIntArray[theList.listOfCities.size()] = 0;

			totalDistance = 0;
			for (int k = 0; k < tempIntArray.length - 1; k++) {
				city1 = theList.listOfCities.get(tempIntArray[k]);
				city2 = theList.listOfCities.get(tempIntArray[k + 1]);
				distanceBetweenTwoCities = Math.sqrt(Math.pow(
						city2.getLatitude() - city1.getLatitude(), 2)
						+ Math.pow(city2.getLongitude() - city1.getLongitude(),
								2));
				totalDistance += distanceBetweenTwoCities;
			}

			if (totalDistance <= bestTotalDistance && totalDistance > 0) {
				bestTotalDistance = totalDistance;
			}

			if (bestTotalDistance == 0) {
				bestTotalDistance = totalDistance;
				for (int n = 0; n < theBestRoute.length; n++) {
					theBestRoute[n] = tempIntArray[n];
				}
			}
			counter++;
			progressBar.setValue((int) (100 * (counter / Double
					.parseDouble(generator.getTotal().toString()))));
		}

		ArrayList<City> cityTemp = new ArrayList<City>();
		for (int n = 0; n < theBestRoute.length; n++) {
			cityTemp.add(theList.getListOfCities().get(theBestRoute[n]));
		}
		GraphingData.setCities(cityTemp);
		gui.paint(gui.getGraphics());
		return cityTemp;
	}

	@Override
	public void run() {
		ArrayList<City> temp = computeFastest();
		for (int k = 0; k < temp.size(); k++) {
			System.out.println(temp.get(k).getNameOfCity() + ", "
					+ temp.get(k).getLatitude() + ", "
					+ temp.get(k).getLongitude());
		}
	}
}
