package test;

import java.util.Scanner;

public class Main {
	// === FIELD VARIABLES === //
	static CustomCalendar RoomCalendarObj = new CustomCalendar();
	static CustomCalendar SuiteCalendarObj = new CustomCalendar();
	static CustomCalendar VillaCalendarObj = new CustomCalendar();
	static CustomCalendar[] calendarObjs = { RoomCalendarObj, SuiteCalendarObj, VillaCalendarObj };
	static GuestNode guests;
	static boolean isBooking = true, isAccommodating = false, isReserved = false;
	static ReservationObj theReservedAccommodation = new ReservationObj();
	private static final String[] TYPE_ROOM = { "Room", "Suite", "Villa" };
	private static final String[] AMENITIES_PER_TYPEROOM = {
			"| 43-LED Wide Screen TV, Air_Conditioned, Internet Access ",
			"| 43-LED Wide Screen TV, Air_Conditioned, Internet Access, \n| Kitchen Accessibility, Large Sofa",
			"| 43-LED Wide Screen TV, Air_Conditioned, Internet Access, \n| Priority Dining Reservations, Luxury Bathroom, \n| Espresso Coffee Machine" };

	static boolean askForDateAgain = true;
	static int monthToStart = 0;
	static DateFormat formatNow;

	static CalendarNode temp;
	private static final double[] PRICEPER_TYPEROOM = { 2199.0f, 12699.0f, 6531.0f };

	// === MAIN METHOD === //
	public static void main(String[] args) {
//		printAccommodationListingMenu();
		Menu();
	}// end class

	/*
	 * The PrintMenuChoices method returns a formatted string for MainMenu. This
	 * formatted string is displayed to the user when they run the code for the
	 * first time. The method is also passed as a parameter to other methods that
	 * use the 'prompt' String.
	 */
	public static String printMenuChoices() {
		String MenuChoicesAsString = """
				\n
				+=============================+
				|       --  MainMenu  --      |
				+=============================+
				| (1) : Book Guests           |
				| (2) : Accommodation Listing |
				| (3) : Admin Panel           |
				| (4) : Exit                  |
				+=============================+
				Select an operation>\s""";
		return MenuChoicesAsString;
	}// end method

	/*
	 * The MenuChoices method contains the following operations: Append, Delete,
	 * Change Value, Display, Node History, List History,and Exit. This method calls
	 * the PrintMenuChoices that prints out the choices for modifying the Persistent
	 * Singly LinkedList that is chosen by the user. MenuChoices method also handles
	 * miss inputs of the user and loops if it detects one.
	 */
	static Scanner sc;

	public static void Menu() {
		// @formatter:off
		
		System.out.print(printMenuChoices());

		switch (checkUserInputInteger(printMenuChoices())) {
		case 1: {// book guest
			if(isBooking) {
				printRegisterUserMenu();
				isAccommodating = true;
				isBooking = false;
			}
			else
				System.out.println(">Finish accommodating the Guest first.");
			break;
		}
		case 2: {// accommodation list
			if(isAccommodating) {
				printAccommodationListingMenu();
				isAccommodating = false;
				isBooking = true;
			}
			else
				System.out.println(">Booking is required before accommodating.");
			break;
		}
		case 3: {// admin panel
			AdminPanel();
			break;
		}
		case 4: {// exit
			System.out.println("Program Terminated.");
			System.exit(0);
			break;
		}
		default:
			// @formatter:off
			System.out.println("""
					\n
					⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃ 
					┇ Error:                                            ┇
					┇ Input is not a valid Menu choice.                 ┇
					⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃
					┇ Msg:                                              ┇
					┇ Please enter only 1 to 7 as input                 ┇
					⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃
					""");
			// @formatter:on
			break;
		}// end method
		System.out.println("\n");
		Menu();
		// @formatter:on
	}// end method

	/*
	 * The CheckUserInputMenu method scans the user's input and checks if it is an
	 * integer. If the input is an integer, it is stored in the 'value' variable and
	 * returns it. If the input is not an integer, an error message is displayed,
	 * and the user is prompted to enter an integer value. The 'prompt' parameter is
	 * used for different scenarios of printing.
	 */
	// TLDR - METHOD FOR DEALING WITH INTEGER INPUT
	public static int checkUserInputInteger(String prompt) {
		sc = new Scanner(System.in);
		if (sc.hasNextInt()) {
			int temp = sc.nextInt();
			if (temp > -1) {
				return temp;
			}
		} // end if
		System.out.println(printCustomError("integer"));
		System.out.print(prompt);
		return checkUserInputInteger(prompt);
	}// end if

	/*
	 * The CheckUserInput is an overload of CheckUserMenu method, This method scans
	 * the user's input and checks if it is a string. If the input is a string, it
	 * is stored in the 'input' variable and returns it. If the input is not a
	 * string, an error message is displayed, and the user is prompted to enter a
	 * string value. The 'prompt' parameter is used for different scenarios of
	 * printing
	 */
	// TLDR - METHOD FOR DEALING WITH STRING INPUT
	public static String checkUserInputString(String prompt) {
		sc = new Scanner(System.in);

		if (!sc.hasNextInt()) {
			String input = sc.nextLine();
			return removeWhiteSpace(input);
		} // end if

		System.out.println(printCustomError("string"));

		System.out.print(prompt);
		return checkUserInputString(prompt);
	}// end if

	public static char checkUserInputChar(String prompt) {
		sc = new Scanner(System.in);

		if (!sc.hasNextInt()) {
			char input = sc.next().toUpperCase().charAt(0);
			return input;
		} // end if

		System.out.println(printCustomError("char"));

		System.out.print(prompt);
		return checkUserInputChar(prompt);
	}// end if

	/*
	 * The removeWhiteSpace method, simply removes any extra white spaces on a
	 * string that is passed on to the parameter. Once the method is done with the
	 * operations, it returns a more 'clean' string for the other methods to use.
	 */
	public static String removeWhiteSpace(String str) {

		String fixed = "";
		for (int i = 0; i < str.length(); i++) {
			if (str.charAt(i) == ' ' && str.charAt(i + 1) != ' ') {
				fixed += str.charAt(i);
			}
			if (str.charAt(i) != ' ') {
				fixed += str.charAt(i);
			}
		} // end for
		return fixed;
	}// end method

	public static String checkEmail() {
		String tempEmail = checkUserInputString("Email Address: ");
		boolean isEmailValid = false;
		int indexOfAt = -1;
		int indexOfDot = -1;
		boolean consecDots = false;
		for (int indexAt = 0; indexAt < tempEmail.length(); indexAt++) {
			if (tempEmail.charAt(indexAt) == '@') { // checks if there's @
				indexOfAt = indexAt; // store the index
				break; // if found stop the looping
			}

		} // end for

		// find the index '.'
		for (int indexDot = 0; indexDot < tempEmail.length(); indexDot++) {
			if (tempEmail.charAt(indexDot) == '.') {
				indexOfDot = indexDot;
				break;
			} // end if
		} // end for

		if (!tempEmail.isEmpty() && tempEmail != null && indexOfDot > indexOfAt && indexOfAt > 0
				&& indexOfDot < tempEmail.length() - 1) {
			for (int indexConsec = indexOfAt + 1; indexConsec < tempEmail.length() - 1; indexConsec++) {
				if (tempEmail.charAt(indexConsec) == '.' && tempEmail.charAt(indexConsec - 1) == '.') {
					consecDots = true;
					break;
				} // end if
			} // end for

			if (!consecDots) { // if there's no another dot
				isEmailValid = true; // it's a valid email
			} // end if
		} // end if

		if (tempEmail == null) {
			System.out.println("[Incomplete Entry]");
			System.out.print("Email Address: ");
			checkEmail();
		} else if (!isEmailValid) {
			System.out.println("[This Email is Invalid.]");
			System.out.print("Email Address: ");
			checkEmail();
		} // end if else

		return tempEmail;
	}// end method

	public static String checkContact() {
		String contact = checkUserInputString("Contact Number: ");

		boolean isInvalidNumber = false;

		one: {
			if (contact.length() < 11 || contact.length() > 11) {
				isInvalidNumber = true;
				break one;
			} // end if

			if (contact.charAt(0) != '0' || contact.charAt(1) != '9') {
				isInvalidNumber = true;
				break one;
			} // end if

			for (int i = 2; i < 10; i++) {
				if (!isInteger(contact.charAt(i))) {
					isInvalidNumber = true;
					break one;
				} // end if
			} // end for

		} // end of one

		if (isInvalidNumber) {
			System.out.print("\nThis is not a valid Phillipine contact number.\n" + "Contact Number: ");
			checkContact();
		} // end if

		return contact;
	}// end method

	public static boolean isInteger(char contact) {
		char[] number = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };
		for (int i = 0; i < 10; i++) {
			if (contact == number[i]) {
				return true;
			} // end if
		} // end for
		return false;
	}// end method

	/*
	 * The printCustomError is exclusively used by checkUserInput, and
	 * checkUserInputMenu for printing their errors, but this method can by used by
	 * other methods if needed. This method has a parameter called 'type' for
	 * specify what data is needed be inputed on a method that calls this.
	 */
	public static String printCustomError(String type) {
		// @formatter:off
		return "\n" +
		"⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃\n" +
		"┇ Warning: Input is not a "+ type +" value. \n" +
		"⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃\n" +
		"┇ Notice: Please only enter a "+ type +" value.\n" +
		"⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃\n";
		// @formatter:on
	}// end method

	/*
	 * reservation used to validate if inputted check in/out already passed through
	 * the current date next is it store the chose accomodation to a new accmodation
	 * node and the node will be encapsulate to the reservation obj to better manage
	 * following reservations Then, it prints the calendar of starting month and
	 * ending month with markings alloted for this reservation
	 */

	public static void printCheckInAndOutCalendar() {
		System.out.print("\nWhat is your Check-In Date: \n");
		CalendarNode startingDate = setDates();

		if (!startingDate.compareIfEarlier(temp)) {
			System.out.println("""
					\n
					⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃
					┇ Error:                                                     ┇
					┇ Date inputted should not be earlier than the current date. ┇
					⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃
					""");
			printCheckInAndOutCalendar();
			return;
		}

		System.out.print("\nWhat is your Check-Out Date: \n");
		CalendarNode endingDate = setDates();

		if (!endingDate.compareIfEarlier(temp)) {
			System.out.println("""
					\n
					⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃
					┇ Error:                                                     ┇
					┇ Date inputted should not be earlier than the current date. ┇
					⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃
					""");
			printCheckInAndOutCalendar();
			return;
		}

		System.out.print("\nAre these dates correct?: \n");
		System.out.print("[Y/n]?");
		switch (checkUserInputChar("[Y/N]? ")) {

		case 'Y': {

			printPrefferedAccomodationListing();
			System.out.print("Enter number that corresponds to the table: ");
			int typeAccommodation = checkUserInputInteger("Enter number that corresponds to the table: ") - 1;

			AccommodationNode thisAccommodation = new AccommodationNode(TYPE_ROOM[typeAccommodation],
					AMENITIES_PER_TYPEROOM[typeAccommodation], PRICEPER_TYPEROOM[typeAccommodation], guests);

			theReservedAccommodation.setCalendar(calendarObjs[typeAccommodation]);

			calendarObjs[typeAccommodation].makeReservation(startingDate, endingDate, thisAccommodation);
			if (isReserved) {
				theReservedAccommodation.printInfoLatestOccupied();

				if (startingDate.getMonth() == endingDate.getMonth()
						&& startingDate.getYear() == endingDate.getYear()) {
					calendarObjs[typeAccommodation].printCalendar(startingDate.getMonth(), startingDate.getYear());
				} else {
					calendarObjs[typeAccommodation].printCalendar(startingDate.getMonth(), startingDate.getYear());
					calendarObjs[typeAccommodation].printCalendar(endingDate.getMonth(), endingDate.getYear());

				}
				isReserved = false;
			}

			break;
		}
		case 'N': {
			System.out.println();
			printCheckInAndOutCalendar();
			break;
		}
		default:
			System.out.println("Input error in printCheckInAndOutCalendar");
			printCheckInAndOutCalendar();
		}
	}// end method

	public static CalendarNode setDates() {
		System.out.println("\n[Enter the date.] Sample: 12/31/2024");
		System.out.print("Month: ");
		int month = checkUserInputInteger("Month: ");
		System.out.print("Day: ");
		int day = checkUserInputInteger("Day: ");
		System.out.print("Year: ");
		int year = checkUserInputInteger("Year: ");

		CalendarNode tempNode = new CalendarNode(month, day, year);
		return tempNode;
	}// end method

	public static void printRegisterUserMenu() {

		// @formatter:off
		System.out.print("""
				\n
				+=====================================+
				|     --  Personal Information  --    |
				|           [Representative]          |
				+=====================================+
				First Name:\s""");
		String rep_FirstName = checkUserInputString("First Name: ");
		// @formatter:on

		System.out.print("Last Name: ");
		String rep_LastName = checkUserInputString("Last Name: ");

		System.out.print("Email Address: ");

		String rep_Email = checkEmail();

		System.out.print("Contact Number: ");
		String rep_Contact = checkContact();

		System.out.print("\nHow many accompanied guests are included in the reservation?"
				+ "\nEnter 0 if single person reservation: ");

		int numOfPerson = checkUserInputInteger("\nHow many accompanied guests are included in the reservation?"
				+ "\nEnter 0 if single person reservation: ");

		String[] otherGuests = new String[numOfPerson];

		if (numOfPerson != 0) {
			System.out.println();
			for (int i = 0; i < numOfPerson; i++) {
				System.out.print((i + 1) + ".) Name of the Guest: ");
				otherGuests[i] = checkUserInputString((i + 1) + ".) Name of the Guest: ");
			} // end for
			System.out.print("+====================================+");
		}

		guests = new GuestNode(rep_FirstName, rep_LastName, rep_Email, rep_Contact, numOfPerson, otherGuests);

		// @formatter:on
	}// end method

	public static void printAccommodationListingMenu() {
		// @formatter:off
		
		if (askForDateAgain) {
			System.out.println("\nBefore proceeding to checking the available accomodation...\n"
					+ "Please input the Date of Inquiring for Reservation: ");
			temp = setDates();
			
			monthToStart = temp.getMonth();
			formatNow = new DateFormat(monthToStart, temp.getDate(), temp.getYear());
			askForDateAgain = false;
			}
		
		printPrefferedAccomodationListing();
				
			while (true) {
				System.out.print("""
						\n
						+========================================+
						|   --  View Availability Calendar  --   |
						+========================================+
						| (1) : Yes                              |
						| (2) : Proceed to Reservation Process   |
						| (3) : Return to Accommodation Listing  |
						+========================================+
						Select an operation>\s""");
				
				int response = checkUserInputInteger("");
				
				if (response == 2) { 
					ReservationManagement();
					break;
				} else if (response == 1) {
					System.out.print("""
							\n
							+=========================================+
							|   --  Check Availability Calendar  --   |
							+=========================================+
							| (1) : Room                              |
							| (2) : Suite                             |
							| (3) : Villa                             |
							+=========================================+
							Select an operation>\s""");

					int choiceAccommodation = checkUserInputInteger("");
					
					/*
					 * prints the months between 2024-2025
					 * nested loop to traverse to months while also controlling the following year to put in.
					 * prints the months between the starting month and ends also with the starting month with a different year.
					 * parameter of monthToStart to determine where to start.
					 * consecutive loops (1st loop traverse to remaining months of 2024 and second, prints the starting months of 2025
					 * until it points to the begin month again.
					 */
					for (int theMonth = monthToStart; theMonth <= 12; theMonth++) {
						calendarObjs[choiceAccommodation-1].printCalendar(theMonth, 2024);
					}
					
					for (int theMonth = 1; theMonth <= monthToStart; theMonth++) {
						calendarObjs[choiceAccommodation-1].printCalendar(theMonth, 2025);
					}
					
				
				} else if (response == 3) {
					printAccommodationListingMenu();
				}//end if else
			}//end while
	}//end method
	
	//display of accommodations choices
	public static void printPrefferedAccomodationListing() {
		System.out.print("""
				\n
				+=========================================================+
				|               --  Accommodation Listing  --             |
				+=========================================================+
				""");
		
				for (int index = 0; index < TYPE_ROOM.length; index++) {
					System.out.print("| Type:(" + (index + 1)  + ")  |" + TYPE_ROOM[index]  + "|    Price Per Night:|₱" + PRICEPER_TYPEROOM[index]  + "|\n| \n| Amenities: \n" + AMENITIES_PER_TYPEROOM[index] + "\n");
					System.out.println("+=========================================================+");
				}//end for
				
	}//end method
	
	public static String printAdminPanelMenu() {
		return """
				\n
				+========================================+
				|           --  Admin Panel  --          |
				+========================================+
				| (1) : View Reservations                |
				| (2) : Generate Revenue Reports         |
				| (3) : Back                             |
				+========================================+
				Select an operation>\s""";
	}
	
	public static void AdminPanel() {
		System.out.print(printAdminPanelMenu());
		
		switch (checkUserInputInteger(printAdminPanelMenu())) {
		case 1: {
			System.out.println("""
						\n
						+========================================+
						|        --  View Reservation  --        |
						+========================================+
						| (1) : Reserved Rooms                   |
						| (2) : Reserved Suites                  |
						| (3) : Reserved Villas                  |
						+========================================+
						Select an operation>\s""");
			int choice = checkUserInputInteger("""
					\n
					+========================================+
					|        --  View Reservation  --        |
					+========================================+
					| (1) : Reserved Rooms                   |
					| (2) : Reserved Suites                  |
					| (3) : Reserved Villas                  |
					+========================================+
					Select an operation>\s""");
			theReservedAccommodation.setCalendar(calendarObjs[choice - 1]);
			theReservedAccommodation.printAllInfo(TYPE_ROOM[choice-1]);
			break;
		}
		case 2: {
			
			theReservedAccommodation.setCalendar(calendarObjs[0]);
			double room = theReservedAccommodation.CalculateRevenuePerType(TYPE_ROOM[0]);
			theReservedAccommodation.setCalendar(calendarObjs[1]);
			double suite = theReservedAccommodation.CalculateRevenuePerType(TYPE_ROOM[1]);
			theReservedAccommodation.setCalendar(calendarObjs[2]);
			double villa = theReservedAccommodation.CalculateRevenuePerType(TYPE_ROOM[2]);
			System.out.println(
					"\n+========================================+" +
					"\n|      --  Total Revenue Reports  --     |" +
					"\n+========================================+" +
					"\n   (1) Rooms : " + room +
					"\n   (2) Suites: "+ suite + 
					"\n   (3) Villas: "+ villa + 
					"\n+========================================+"+
					"\nTOTAL REVENUE: "  + (room + suite + villa));
			break;
		}
		case 3: {
			Menu();
			break;
		}
		default:
			// @formatter:off
			System.out.println("""
					\n
					⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃ 
					┇ Error:                                            ┇
					┇ Input is not a valid Menu choice.                 ┇
					⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃
					┇ Msg:                                              ┇
					┇ Please enter only 1 to 3 as input                 ┇
					⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃
					""");
			// @formatter:on
			break;
		}// end method
		AdminPanel();
		System.out.println("\n");
	}

	public static void ReservationManagement() {
		System.out.print("""

				+====================================+
				|   --  Reservation Management  --   |
				+====================================+
				""");
		printCheckInAndOutCalendar();
	}// end method

}// end class
