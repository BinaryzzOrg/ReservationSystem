
//class only for the purpose of easy navigation of recent reservation
public class ReservationObj {
	private AccommodationNode accommodation;
	private CalendarNode checkInDate;
	private CalendarNode checkOutDate;

	public ReservationObj(AccommodationNode accomodation, CalendarNode checkInDate, CalendarNode checkOutDate) {
		this.accommodation = accomodation;
		this.checkInDate = checkInDate;
		this.checkOutDate = checkOutDate;
	}

	public AccommodationNode getAccomodation() {
		return accommodation;
	}

	public CalendarNode getCheckInDate() {
		return checkInDate;
	}

	public CalendarNode getCheckOutDate() {
		return checkOutDate;
	}

	public String toString() {
		return "\tReservation Details " + "\n" + accommodation + ""
				+ "\t\nCheck-In/Out Date: " + checkInDate.getDate() + " - " +  checkOutDate.getDate();
	}
	
	
	
	

}
