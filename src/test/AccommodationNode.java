package test;

public class AccommodationNode {
	private String chosenType;
	private String amenities;
	private double pricePerNight;
	private AccommodationNode nextAccomodation;
	private GuestNode guestInfo;

	public AccommodationNode(String chosenType, String amenities, double pricePerNight, GuestNode guestInfo) {
		this.chosenType = chosenType;
		this.amenities = amenities;
		this.pricePerNight = pricePerNight;
		this.guestInfo = guestInfo;

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
		return "Type: " + getChosenType() + "\nAmenities: " + getAmenities() + "\nPrice: " + getPricePerNight()
				+ guestInfo;
	}

	public GuestNode getGuestInfo() {
		return guestInfo;
	}

	public void setGuestInfo(GuestNode guestInfo) {
		this.guestInfo = guestInfo;
	}

}
