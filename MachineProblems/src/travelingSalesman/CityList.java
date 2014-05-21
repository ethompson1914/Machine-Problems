package travelingSalesman;

// Imports
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;

public class CityList {

	File listOfCitiesFile = null;
	RandomAccessFile listOfCitiesFileRA = null;
	ArrayList<City> listOfCities;

	// Constructor
	public CityList(String listOfCitiesFilename) {
		try {
			listOfCitiesFile = new File(listOfCitiesFilename);
			listOfCitiesFileRA = new RandomAccessFile(listOfCitiesFile, "r");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		listOfCities = new ArrayList<City>();
		parseLines();

	}

	// Parses the file
	public void parseLines() {
		try {
			while (true) {
				String line = listOfCitiesFileRA.readLine();
				if (line == null)
					break;
				if (!line.equals("City,Latitude,Longitude")) {
					String[] words = line.split(",");
					listOfCities.add(new City(words[0], Double
							.parseDouble(words[1]), Double
							.parseDouble(words[2])));
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// Returns all of the cities
	public ArrayList<City> getListOfCities() {
		return listOfCities;
	}

	// Returns all of the latitude points
	public int[] getXpoints() {
		parseLines();
		ArrayList<City> temp = getListOfCities();
		int[] tempArray = new int[temp.size()];
		for (int i = 0; i < temp.size(); i++) {
			tempArray[i] = (int) temp.get(i).getLatitude();
		}
		return tempArray;
	}

	// Returns all of the longitude points
	public int[] getYpoints() {
		parseLines();
		ArrayList<City> temp = getListOfCities();
		int[] tempArray = new int[temp.size()];
		for (int i = 0; i < temp.size(); i++) {
			tempArray[i] = (int) temp.get(i).getLongitude();
		}
		return tempArray;
	}
}