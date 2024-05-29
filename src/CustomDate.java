public class CustomDate {
	//@formatter:off
	/*
	 * Class Attributes
	 * -occupiedList is for storing dates that are occupied
	 * -latestOccupied is a pointer for the last node of the occupiedList
	 * to add nodes in O(1) time complexity
	 * -MONTHS is for the 12 months of the year it is for accessing every
	 * month in the array using indices that come from certain computations
	 */
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

	//Class constructor and getter methods
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

	/*
	 * in the occupy method, in order to occupy a date
	 * it first checks if the given date by the user 
	 * does not exist in the list of already occupied dates.
	 * If the given date does exist print that the reservation
	 * is denied and just end the method immediately. However,
	 * if the given dates does not exist add it at the current list
	 * if the current list is not null setNext the current date to
	 * the end then set it as the new end of the list. Otherwise,
	 * make it as the root then also point the end pointer at the
	 * current root. After all of these conditions print that the
	 * reservation has been accepted. There is also another condition
	 * that only occurs when the given starting and ending date does not
	 * have the same month or year. If this is the case execute
	 * the occupyHelper method to occupy the dates in between in the
	 * given range.
	 */
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

	/*
	 * occupyHelper is for handling the case where the given starting date and ending
	 * date have different month or year. If the given ending date is in a different
	 * year we need to add 12 to the ending month because It is in another year
	 * specially if the ending month is in January or February where it is needed
	 * to be 13 and 14 to be able to be utilized in certain computation in the program.
	 * A month that is greater than 13 & 14 is also fixed by having modulo 12 on other method
	 * that utilizes the correct month. If the year is the same just proceed to the if
	 * condition below the while method. Since it is assured that the starting month is
	 * less than the ending month(not in the same month) this method splits the reservation 
	 * by filling the rest of the month with reserved dates then proceeds to the next months
	 * until it reaches the correct month with the ending date. If it reaches the ending month
	 * it is filled with reservation dates because of the while loop that is why after the
	 * while loop the ending date is needed to be changed to the original ending date
	 * to reserve the correct range of dates that is why the original ending date is kept
	 * in a variable inside the if condition and the ending year is also kept 
	 * in the case of the ending year being different.
	 */
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

	/*
	 * occupiedContains checks if the given dates is already in the list
	 * inside the while loop it first checks if the given date have the same
	 * year as the ending date of the occupied detected if they are not similar
	 * it is assured that the given date is in the range of the year where
	 * there is a possibility that the given date is unoccupied. If the condition
	 * is true then proceed to checking the months of the occupied dates with the
	 * same year. Modulo 12 is for fixing the months where they have month value
	 * higher than 12 in order to get its original month value the OR condition
	 * is special for December because it results to 0 in modulo so if a month
	 * - 12 is equal to 0 it is assured that it is December. The 4 if conditions
	 * that is nested is for checking the given date is within the range of the 
	 * identified month or if the identified occupied dates is within the given
	 * range of the user. If it is within these ranges return true so that it will
	 * be identified as occupied. If the while loop ends just return false meaning
	 * that the given range for reservation is available.
	 */
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

	/*
	 * this method is for identifying the ending dates for each month
	 * the first if condition is for identifying if February is in a leap
	 * year if modulo 4 is 0 meaning it that February is in a leap year
	 * else return 28. The other if condition is identifying if the
	 * ending day of the month is 30 or 31. The EVEN_MONTHS variable
	 * signifies 4 as April, 6 as June, 9 as September, and 11 as November
	 * if the given month is any of these number therefore it has an
	 * ending day of 30 else it has 31.
	 */
	private int returnLastDayOfGivenMonth(int year, int month) {
		final boolean EVEN_MONTHS = month == 4 || month == 6 || month == 9 || month == 11;
		if (month == 14) {
			return year % 4 == 0 ? 29 : 28;
		}
	
		return EVEN_MONTHS ? 30 : 31;
	}
}