import java.util.Scanner;

public class Main {
	// === FIELD VARIABLES === //
	static CustomCalendar RoomCalendarObj = new CustomCalendar();
	static CustomCalendar SuiteCalendarObj = new CustomCalendar();
	static CustomCalendar VillaCalendarObj = new CustomCalendar();
	static CustomCalendar[] calendarObjs = { RoomCalendarObj, SuiteCalendarObj, VillaCalendarObj };
	private static final String[] TYPE_ROOM = { "Room", "Suite", "Villa" };
	private static final String[] AMENITIES_PER_TYPEROOM = { "Television, Air_Conditioned, Internet Access",
			"43-LED Wide Screen TV, Air_Conditioned, \n Internet Access, \n Kitchen Accessibility, Large Sofa",
			"Luxury Bathroom, Priority Dining Reservations, \n Espresso Coffee Machine, Internet Access, \n 43-LED Wide Screen TV, Air_Conditioned" };;
	static boolean askForDateAgain = true;
	static int monthToStart = 0;
	static DateFormat formatNow;
	static CalendarNode temp;
	private static final double[] PRICEPER_TYPEROOM = { 2199.0f, 12699.0f, 6531.0f };

	// === MAIN METHOD === //
	public static void main(String[] args) {
		printAccommodationListingMenu();
		// Menu();
	}// end class

	/*
	 * The PrintMenuChoices method returns a formatted string for MainMenu. This
	 * formatted string is displayed to the user when they run the code for the
	 * first time. The method is also passed as a parameter to other methods that
	 * use the 'prompt' String.
	 */
	public static String printMenuChoices() {
		//@formatter:off
		String MenuChoicesAsString = "\n" 
				+ "       {MainMenu}\n" 
				+ "+=======================+\n"
				+ "| (1) : Book Guests\n" 
				+ "| (2) : Accommodation Listing\n" 
				+ "| (3) : \n" 
				+ "| (4) : \n" 
				+ "| (5) : \n" 
				+ "| (6) : \n" 
				+ "| (7) : Exit \n" 
				+ "+=======================+\n"
				+ "Select an operation> ";
		//@formatter:on
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
		String error1 = "";
		String error2 = "";
		
		System.out.print(printMenuChoices());

		switch (checkUserInputInteger(printMenuChoices())) {
		case 1: {// Register User
			printRegisterUserMenu();
			break;
		}
		case 2: {// 
			printCheckInAndOutCalendar();
			break;
		}
		case 3: {// 
			break;
		}
		case 4: {// 
			break;
		}
		case 5: {// 
			break;
		}
		case 6: {// 
			break;
		}
		case 7: {// 
			break;
		}
		default:
			// @formatter:off
			System.out.println("\n" 
					+ "⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃\n" 
					+ "┇ Error: \n"
					+ "┇ Input is not a valid Menu choice. \n"
					+ "⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃\n" 
					+ "┇ Msg: \n"
					+ "┇ Please enter only 1 to 7 as input \n"
					+ "⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃");
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
			return sc.nextInt();
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

		boolean contactConitions = (contact.length() > 11 || contact.length() < 11)
				&& (contact.charAt(0) != '0' || contact.charAt(1) != '9');
		// end if
		if (contactConitions) {
			System.out.print("This is not a valid Phillipine contact number");
			checkContact();
		} // end if

		return contact;
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
			System.out.println("Date inputted is earlier to the current date. ");
			printCheckInAndOutCalendar();
			return;
		}

		System.out.print("\nWhat is your Check-Out Date: \n");
		CalendarNode endingDate = setDates();

		if (!endingDate.compareIfEarlier(temp)) {
			System.out.println("Date inputted is earlier to the current date. ");
			printCheckInAndOutCalendar();
			return;
		}

		printPrefferedAccomodationListing();

		int typeAccommodation = checkUserInputInteger("Enter num that corresponds to the table: ") - 1;

		System.out.print("\nAre these dates correct?: \n");
		System.out.print("[Y/n]?");
		switch (checkUserInputChar("[Y/n]?")) {
		case 'Y': {

			AccommodationNode thisAccommodation = new AccommodationNode(TYPE_ROOM[typeAccommodation],
					AMENITIES_PER_TYPEROOM[typeAccommodation], PRICEPER_TYPEROOM[typeAccommodation]);

			ReservationObj theReservedAcccommodation = new ReservationObj(thisAccommodation, startingDate, endingDate);

			System.out.println(theReservedAcccommodation);

			calendarObjs[typeAccommodation].makeReservation(startingDate, endingDate);
			calendarObjs[typeAccommodation].printCalendar(startingDate.getMonth(), startingDate.getYear());
			calendarObjs[typeAccommodation].printCalendar(endingDate.getMonth(), endingDate.getYear());

			printAccommodationListingMenu();

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
		System.out.print("\n" +
				"+=====================================+\n"+
				"|     --  Personal Information  --    |\n"+
				"|           [Representative]          |\n"+
				"+=====================================+\n"+
				"First Name: ");
		String rep_FirstName = checkUserInputString("First Name: ");
		// @formatter:on

		System.out.print("Last Name: ");
		String rep_LastName = checkUserInputString("Last Name: ");

		System.out.print("Email Address: ");

		String rep_Email = checkEmail();

		System.out.print("Contact Number: ");
		String rep_Contact = checkContact();

		System.out.print("Number of Persons: ");
		int numOfPerson = checkUserInputInteger("Number of Persons: ");

		// -1 to not include rep sa count
		String[] otherGuests = new String[numOfPerson - 1];
		for (int i = 0; i < numOfPerson; i++) {
			System.out.print((i + 1) + ".) Name of the Guest: ");
			otherGuests[i] = checkUserInputString("Number of the Guest: ");
		} // end for
		System.out.print("+====================================+");

		GuestNode guest = new GuestNode(rep_FirstName, rep_LastName, rep_Email, rep_Contact, numOfPerson, otherGuests);

		// @formatter:on
	}// end method

	public static void printAccommodationListingMenu() {
		// @formatter:off
		
		if (askForDateAgain) {
			System.out.println("Before proceeding to checking the available accomodation...\n"
					+ "Please input the Date of Inquiring for Reservation: ");
			temp = setDates();
			
			monthToStart = temp.getMonth();
			formatNow = new DateFormat(monthToStart, temp.getDate(), temp.getYear());
			askForDateAgain = false;
			}
		
		printPrefferedAccomodationListing();
				
			while (true) {
				System.out.print("""

						+========================================================+
						|     	  View Availability Calendar?                    |
						|  [1]Yes                                                |
						|  [2]Proceed to Reservation Process                     |
						|  [3]Return to Accommodation Listing                    |
						+========================================================+
						\
						""");
				
				int response = checkUserInputInteger("");
				
				if (response == 2) { 
					ReservationManagement();
					break;
				} else if (response == 1) {
					System.out.print("""

							+========================================================+
							|     	        Check Availability Calendar              |
							|  [1]Room                                               |
							|  [2]Suite                                              |
							|  [3]Villa                                              |
							+========================================================+
							\
							""");

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
				}
				
			}
		
	}//end method
	//display of accommodations choices
	public static void printPrefferedAccomodationListing() {
		System.out.println(formatNow);
		System.out.print("""
			
				+========================================================+
				|     	   --  Accommodation Listing  --     		 |
				|                                     		         |
				+========================================================+
				\
				""");
		
				for (int index = 0; index < TYPE_ROOM.length; index++) {
					System.out.print("|[" + (index + 1)  + "]   " + TYPE_ROOM[index]  + "  ||₱" + PRICEPER_TYPEROOM[index]  + "||\n|" + AMENITIES_PER_TYPEROOM[index]);
					System.out.print("\n+");
					System.out.println("========================================================");
				}
	}
	
	public static void ReservationManagement() {
		System.out.print("""

				+========================================================+
				|     	   --  Reservation Management  --     	 	  |
				|                                     		          |
				+========================================================+
				\
				""");
		
				printCheckInAndOutCalendar();
		
	}
	
}// end class