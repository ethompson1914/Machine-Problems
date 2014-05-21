package travelingSalesman;

public class City extends Coordinate {

	// Instance variable
	protected String nameOfCity;

	// Constructor
	public City(String name, double lat, double lon) {
		super(lat, lon);
		nameOfCity = name;
	}

	// Gets the name of the city
	public String getNameOfCity() {
		return nameOfCity;
	}

	// Sets the name of the city
	public void setNameOfCity(String nameOfCity) {
		this.nameOfCity = nameOfCity;
	}
}
