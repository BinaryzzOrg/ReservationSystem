public class CustomDate {
	//@formatter:off
	private CalendarNode occupiedList;
	private CalendarNode latestOccupied;
	final private String[] MONTHS = {"January",
			   "February",
			   "March",
			   "April",
			   "May",
			   "June",
			   "July",
			   "August",
			   "September",
			   "October",
			   "November",
			   "December"};
	
	public CustomDate() {
		this.occupiedList = null;
		this.latestOccupied = null;
	}
	
	public CalendarNode getOccupiedList() {
		return occupiedList;
	}

	public CalendarNode getLatestOccupied() {
		return latestOccupied;
	}
	
	public void occupy(CalendarNode date) {
		
		if(occupiedContains(date)) {
			System.out.println("\n+========================================+\n"
						 + ">DENIED RESERVATION!"
						 + "\n-Due to Inavailability of some Dates-"
						 + "\n>Starting Date: " + MONTHS[date.getMonth() - 1] + " " + date.getDate() + ", " + date.getYear()
						 + "\n>Ending Date: " + MONTHS[date.getEndingDate().getMonth() - 1] + " " + date.getEndingDate().getDate() + ", " + date.getEndingDate().getYear()
						 + "\n+========================================+\n");
			return;
		}
		
		if(occupiedList != null) {
			latestOccupied.setNext(date);
			latestOccupied = date;
		} else {
			occupiedList = date;
			latestOccupied = occupiedList;	
		}
		
		System.out.println("\n+========================================+\n"
				 + ">ACCEPTED RESERVATION!" 
				 + "\n>Starting Date: " + MONTHS[date.getMonth() - 1] + " " + date.getDate() + ", " + date.getYear()
				 + "\n>Ending Date: " + MONTHS[date.getEndingDate().getMonth() - 1] + " " + date.getEndingDate().getDate() + ", " + date.getEndingDate().getYear()
				 + "\n+========================================+\n");
		occupyHelper(date.getMonth(), date.getEndingDate().getMonth(), date);
	}
	
	private void occupyHelper(int startingMonth, int endingMonth, CalendarNode date) {
		int originalEndingYear = date.getEndingDate().getYear();
		int countYear = date.getYear();
		
		while(countYear < date.getEndingDate().getYear()) {
			endingMonth += 12;
			countYear++;
		}
		
		if(startingMonth < endingMonth) {

			int originalEndingDate = date.getEndingDate().getDate();		
			int currentMonthEnd = returnLastDayOfGivenMonth(date.getYear(), startingMonth);
			CalendarNode currentMonthEndingDate = new CalendarNode(startingMonth, currentMonthEnd, date.getYear());
			date.setEndingDate(currentMonthEndingDate);
			
			while(startingMonth < endingMonth) {
				
				startingMonth++;
				CalendarNode fillMonth = new CalendarNode(startingMonth, 1, date.getYear());
				int lastDayOfMonth = returnLastDayOfGivenMonth(date.getYear(), startingMonth);
				int endingYear = date.getYear();
				
				if(startingMonth > 12)
					endingYear++;
				
				latestOccupied.setNext(fillMonth);
				latestOccupied = fillMonth;
				latestOccupied.setEndingDate(new CalendarNode(startingMonth, lastDayOfMonth, endingYear));
			}
			latestOccupied.setEndingDate(new CalendarNode(startingMonth, originalEndingDate, originalEndingYear));
		}
	}

	public boolean occupiedContains(CalendarNode date) {
		
		CalendarNode occupiedDates = occupiedList;
		
		while(occupiedDates != null) {
			
			if(occupiedDates.getEndingDate().getYear() == date.getYear() ) {
				if(occupiedDates.getMonth() % 12 == date.getMonth() || occupiedDates.getMonth() % 12 == date.getMonth() - 12) {
					int startingDate = occupiedDates.getDate();
					int endingDate = occupiedDates.getEndingDate().getDate();
					
					int givenStartingDate = date.getDate();
					int givenEndingDate = date.getEndingDate().getDate();
					
					if(givenStartingDate >= startingDate && givenStartingDate <= endingDate)
						return true;
					
					if(givenEndingDate >= startingDate && givenEndingDate <= endingDate)
						return true;

					if(startingDate >= givenStartingDate && startingDate <= givenEndingDate)
						return true;
					
					if(endingDate >= givenStartingDate && endingDate <= givenEndingDate)
						return true;
				}
			}
			occupiedDates = occupiedDates.getNext();
		}
		return false;
	}
	
	private int returnLastDayOfGivenMonth(int year, int month) {
		final boolean EVEN_MONTHS = month == 4 || month == 6 || month == 9 || month == 11;
		if (month == 14) {
			return year % 4 == 0 ? 29 : 28;
		}
	
		return EVEN_MONTHS ? 30 : 31;
	}
}
