package travelingSalesman;

public class Coordinate {

	// Instance variables
	protected double latitude;
	protected double longitude;

	// Constructor
	public Coordinate(double lat, double lon) {
		latitude = lat;
		longitude = lon;
	}

	// Returns the latitude
	public double getLatitude() {
		return latitude;
	}

	// Sets the latitude
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	// Gets the longitude
	public double getLongitude() {
		return longitude;
	}

	// Sets the longitude
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
}
