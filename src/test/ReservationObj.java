package test;

public class ReservationObj {
	private CustomCalendar calendar;
	private CustomDate reservedList;

	final private String[] MONTHS = { "January", "February", "March", "April", "May", "June", "July", "August",
			"September", "October", "November", "December" };

	public ReservationObj() {
		this.calendar = null;
		this.reservedList = null;
	}

	public void setCalendar(CustomCalendar calendar) {
		this.calendar = calendar;
	}

	/*
	 * This method retrieve and display the latest reserved guest info Info Details
	 * Structure -Representative Complete Name -List of Accompanied Guests Names ~
	 * var - reservedList(Obj) - temp var that stores reservedDates ~ temp//
	 * node(Obj) - temp var stores latestOccupied.
	 */
	public void printInfoLatestOccupied() {
		reservedList = calendar.getReservedDates();

		CalendarNode node = reservedList.getLatestOccupied();
		System.out.println(">RESERVATION DETAILS" + node.getAccommodation().getGuestInfo().toString()
				+ "\n+========================================+\n");
	}

	/*
	 * Displays accomodation details along with the start and end dates. Varies from
	 * diff type. ~ Uses a NodeObj to temporary store the list of occupied guests. ~
	 * Traverse in order to print with the use of root var as a head ref
	 */

	public void printAllInfo(String type) {
		reservedList = calendar.getReservedDates();

		CalendarNode root = reservedList.getOccupiedList();
		System.out.println("\n " + "\n+========================================+" + "\n        --  Reserved " + type
				+ "   --      " + "\n+========================================+");
		while (root != null) {
			if (root.isOriginal() && root.getAccommodation().getChosenType() == type) {
				System.out.println("\nRepresentative: " + root.getAccommodation().getGuestInfo().getRep_FirstName()
						+ " " + root.getAccommodation().getGuestInfo().getRep_LastName() + "\nAccommodation Type: "
						+ type + "\n>Starting Date: " + MONTHS[root.getMonth() - 1] + " " + root.getDate() + ", "
						+ root.getYear() + "\n>Ending Date: " + MONTHS[root.getOriginalEndingMonth() - 1] + " "
						+ root.getEndingDate().getDate() + ", " + root.getEndingDate().getYear());
			}
			System.out.println();
			root = root.getNext();
		}
		System.out.println("\n+========================================+\n");
	}
	/*
	 * Method that computes the total revenue with varies in type. ~ double var of
	 * totalPricePerType - used to store and return the totalRevenue. Same process
	 * with with printAllInfo, but will return a summation of the TotalRevenue ~
	 * Formula of - root.getAccommodation().getPricePerNight() *
	 * root.getNumberOfDays(); - that multiplies the price per night with the N-Days
	 * of reservation (Between Check-In/Out Date).
	 */

	public double CalculateRevenuePerType(String type) {
		double totalPricePerType = 0;
		reservedList = calendar.getReservedDates();
		CalendarNode root = reservedList.getOccupiedList();
		while (root != null) {
			if (root.isOriginal() && root.getAccommodation().getChosenType() == type)
				totalPricePerType += root.getAccommodation().getPricePerNight() * root.getNumberOfDays();
			root = root.getNext();
		}
		return totalPricePerType;
	}
}