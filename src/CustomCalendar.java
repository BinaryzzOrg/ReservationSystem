public class CustomCalendar {

	//@formatter:off
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
		
		int startingDay = (((13 * (zMonth + 1)) - (10 * zYear))/5
						+ (zYear + zCentury + (4 * zCentury)) / 4) % 7;
		int endingDay = returnLastDayOfGivenMonth(originalYear, month);

		printCalendarHeader(originalMonth, originalYear, startingDay);
		printReservedDates(startingDay, endingDay, originalMonth, originalYear);
		
	}
	
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
	
	private int returnLastDayOfGivenMonth(int year, int month) {
		final boolean EVEN_MONTHS = month == 4 || month == 6 || month == 9 || month == 11;
		if (month == 14) {
			if (year % 4 == 0) {
				return 29;
			} else return 28;
		}
	
		if (EVEN_MONTHS) {
			return 30;
		} else return 31;
	}
	
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
	
	public void print() {
		CalendarNode root = reservedDates.getOccupiedList();
		
		while(root != null) {
			System.out.println(root.getDate());
			root = root.getNext();
		}
	}
	
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
