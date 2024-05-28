
//class only for the purpose of easy navigation of recent reservation
public class ReservationObj {
	private AccomodationNode accomodation;
	private CalendarNode checkInDate;
	private CalendarNode checkOutDate;

	public ReservationObj(AccomodationNode accomodation, CalendarNode checkInDate, CalendarNode checkOutDate) {
		this.accomodation = accomodation;
		this.checkInDate = checkInDate;
		this.checkOutDate = checkOutDate;
	}

	public AccomodationNode getAccomodation() {
		return accomodation;
	}

	public CalendarNode getCheckInDate() {
		return checkInDate;
	}

	public CalendarNode getCheckOutDate() {
		return checkOutDate;
	}

	public String toString() {
		return "\tReservation Details " + "\n" + accomodation + ""
				+ "\t\nCheck-In/Out Date: " + checkInDate.getDate() + " - " +  checkOutDate.getDate();
	}
	
	
	
	

}
