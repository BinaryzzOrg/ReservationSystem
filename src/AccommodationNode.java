
//Node class used for later for Admin panel
public class AccommodationNode {
	private String chosenType;
	private String amenities;
	private double pricePerNight;
	private AccommodationNode nextAccomodation;

	public AccommodationNode(String chosenType, String amenities, double pricePerNight) {
		this.chosenType = chosenType;
		this.amenities = amenities;
		this.pricePerNight = pricePerNight;
	}

	public AccommodationNode getNextAccomodation() {
		return nextAccomodation;
	}

	public void setNextAccomodation(AccommodationNode nextAccomodation) {
		this.nextAccomodation = nextAccomodation;
	}

	public String getChosenType() {
		return chosenType;
	}

	public String getAmenities() {
		return amenities;
	}

	public double getPricePerNight() {
		return pricePerNight;
	}

	@Override
	public String toString() {
		return "Type: " + getChosenType() + "\nAmenities: " + getAmenities() + "\nPrice: " + getPricePerNight();
	}

}
