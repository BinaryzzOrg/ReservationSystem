public class CustomCalendar {

	//@formatter:off
	/*
	 * Class Attributes
	 * -reservedDates contains the list of reservedDates that is stored
	 * in the instance of this object.
	 * -MONTHS is for the 12 months of the year it is for accessing every
	 * month in the array using indices that come from certain computations
	 * -DAYS is for the 7 days of the week where every element is accessed
	 * through specific indices.
	 */
	private CustomDate reservedDates = new CustomDate();
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
	
	final private String[] DAYS = {"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"};

	/*
	 * printCalendar method is for printing the calendar with
	 * the reserved dates in each month. There is a originalMonth
	 * and originalYear variable to store the original given year
	 * and month because month and year will be modified to utilize
	 * the modified Zeller's Congruence formula for getting the
	 * first day of the month. It is modified because the day in the
	 * formula has been set to the first day by modifying it and compressing
	 * the formula where the first day is already set to be identified. 
	 * That is how the startingDay is computed then the ending day of the 
	 * month is identified using the returnLastDayOfGivenMonth method. 
	 * If the month is identified as 1 and 2 months needs to add 12 in order 
	 * to utilize the formula because January and February is treated as the 
	 * 13th and 14th month. Then the calendar is finally printed using the 
	 * printCalendarHeader and its reservedDates in that month.
	 */
	public void printCalendar(int month, int year) {
		int originalMonth = month;
		int originalYear = year;
		
		if(month < 3 && month > 0) {
			month += 12;
			year--;
		}
		
		int zMonth = month;
		int zYear = year / 100;
		int zCentury = year % 100;
		
		int startingDay = (((5 + 13 * (zMonth + 1)) / 5)
				+ (-1 * (4 * zYear) + (4 * zCentury) + zCentury + 20) / 4) % 7;
		int endingDay = returnLastDayOfGivenMonth(originalYear, month);

		printCalendarHeader(originalMonth, originalYear, startingDay);
		printReservedDates(startingDay, endingDay, originalMonth, originalYear);
		
	}

	/*
	 * printCalendarHeader is a helper method for the printCalendar method
	 * it prints the header of the calendar where the month and days of the
	 * week are included offset is for creating spaces before the starting
	 * day of the week of that month in that year.
	 */
	private void printCalendarHeader(int month, int year, int startingDay) {
		int day = 0;
		int offset = 0;
		
		System.out.println("\n+=========================================+"
		 				 + "\n\t       " + MONTHS[month - 1] + " "  + year 
		 				 + "\n+=========================================+");

		while(day < DAYS.length) {
			System.out.print((DAYS[day] == "Sat") ? "| " + DAYS[day] + " |" 
					  + "\n+-----------------------------------------+\n" 
					  : "| " + DAYS[day] + " ");
			day++;
		}
		
		while(offset < startingDay) {
			System.out.print("      ");
			offset++;
		}
	}

	/*
	 * printReservedDates is for printing the reserved dates on the specific month
	 * of the year. The currentDay variable keeps track of the current day of that
	 * month and ending day. So the while loop checks every day within the specified 
	 * range that is the starting day and ending day. Then inside the while loop
	 * it checks if the currentDay is within the 7 days of the week if it reaches
	 * seven it should go down to form a new row and reset the week counter. Also
	 * if the month is greater than 12 it should be returned to the original which
	 * is 1 and 2 this is only applicable to January and February since they have
	 * a 13 and 14 value then if the currentDate is occupied using the occupiedContains
	 * method that is located in the CustomDate class it should be marked as occupied
	 * in the calendar printing then to end this method to ensure that there is a line
	 * at the end of the calendar it should check if the weekDayCounter is
	 * not equal to seven or 0 because when the final row of the month is 7 it should
	 * not print another line below.
	 */
	private void printReservedDates(int startingDay, int endingDay, int month, int year) {
		
		int day = 0;
		int currentDay = 0;
		int weekDayCounter = startingDay;
		
		while(currentDay < endingDay) {
			if(weekDayCounter < 7) {
				day++;
				if(month > 12) 
					month -= 12;
				
				CalendarNode currentDate = new CalendarNode(month, day, year);
				currentDate.setEndingDate(currentDate);
				System.out.print((reservedDates.occupiedContains(currentDate)) ? "|  /  " 
							    :(day < 10) ? "|  " + day + "  " : "| " + day + "  ");
			} 
			else
				System.out.print("| ");
			
			weekDayCounter++;
			if(weekDayCounter == 7) {
				System.out.print("|\n+-----------------------------------------+\n");
				weekDayCounter = 0;
			}
			currentDay++;
		}
		
		if(weekDayCounter != 7 && weekDayCounter != 0) {
			System.out.print("|");
			System.out.print("\n+-----------------------------------------+\n");
		}
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
			return year % 4 == 0? 29 : 28;
		}
		return EVEN_MONTHS? 30: 31;
	}

	/*
	 * makeReservation accepts two parameters which is the startingDate and the
	 * endingDate in order to make the reservation it should set the endingDate
	 * of the startingDate the provided endingDate of the user the check the
	 * given date validity using the checkDateValidity method. This ensures that
	 * the given dates is within the month and the startingDate is earlier than
	 * the ending date. If this condition is not satisfied it will print an
	 * error.
	 */
	public void makeReservation(CalendarNode startingDate, CalendarNode endingDate) {
		startingDate.setEndingDate(endingDate);
		
		if(checkDateValidity(startingDate))
			reservedDates.occupy(startingDate);
		else
			System.out.println("\n+============================================================+"
							 + "\n\t\t   [RESERVATION ERROR!]"
							 + "\n POSSIBLE ERRORS:"
							 + "\n-The starting date should be moved later than the ending date."
							 + "\n-The given dates should be within the dates of the month."
							 + "\n\n-DATE INFORMATION-"
							 + "\nStarting Date: " + MONTHS[startingDate.getMonth() - 1] + " " + startingDate.getDate() + ", " + startingDate.getYear()
							 + "\nEnding Date: " + MONTHS[endingDate.getMonth() - 1] + " " + endingDate.getDate() + ", " + endingDate.getYear()
							 + "\n+============================================================+");
	}

	/*
	 * checkDateValidity has 3 conditions
	 * the first if condition is if the given startingDate less than
	 * the starting date of the month which is 1 and if it is also
	 * greater than the ending date of the month it should return false
	 * because it will not be valid. This is also applied to the given
	 * ending date. Then to check whether the given startingDate is before
	 * the given endingDate the values of the date are computed using
	 * multiplication and addition to certain values in order to identify
	 * if the given starting date is earlier than the ending. It the starting
	 * date has a greater value it should return false because it means that
	 * the starting date is much later than the ending date.
	 */
	private boolean checkDateValidity(CalendarNode date) {
		int givenStartingDate = date.getDate();
		int givenEndingDate = date.getEndingDate().getDate();
		
		int startingDateEnd = returnLastDayOfGivenMonth(date.getYear(), date.getMonth());
		int endingDateEnd = returnLastDayOfGivenMonth(date.getEndingDate().getYear(), date.getEndingDate().getMonth());
		
		int startingDateValue = (date.getMonth() * 60) + date.getDate() + (date.getYear() * date.getYear());
		int endingDateValue = (date.getEndingDate().getMonth() * 60) + date.getEndingDate().getDate() + (date.getEndingDate().getYear() * date.getEndingDate().getYear());
		
		if(givenStartingDate < 1 || givenStartingDate > startingDateEnd)
			return false;
		
		if(givenEndingDate < 1 || givenEndingDate > endingDateEnd)
			return false;
		
		if(startingDateValue > endingDateValue)
			return false;
		
		return true;
	}

}
