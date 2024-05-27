
public class GuestNode {
	private String rep_FirstName;
	private String rep_LastName;
	private String rep_Email;
	private String rep_Contact;
	private int NumOfPerson;
	private String[] otherGuests;
	private GuestNode next;

	// Accommodation prefferedAccomodation;
	public GuestNode(String rep_FirstName, String rep_LastName, String rep_Email, String rep_Contact, int NumOfPerson,
			String[] otherGuests) {
		setRep_FirstName(rep_FirstName);
		setRep_LastName(rep_LastName);
		setRep_Email(rep_Email);
		setRep_Contact(rep_Contact);
		setNumOfPerson(NumOfPerson);
		setOtherGuests(otherGuests);
		setNext(null);
	}// end constructor

	public String getRep_FirstName() {
		return rep_FirstName;
	}

	public void setRep_FirstName(String rep_FirstName) {
		this.rep_FirstName = rep_FirstName;
	}

	public String getRep_LastName() {
		return rep_LastName;
	}

	public void setRep_LastName(String rep_LastName) {
		this.rep_LastName = rep_LastName;
	}

	public String getRep_Email() {
		return rep_Email;
	}

	public void setRep_Email(String rep_Email) {
		this.rep_Email = rep_Email;
	}

	public String getRep_Contact() {
		return rep_Contact;
	}

	public void setRep_Contact(String rep_Contact) {
		this.rep_Contact = rep_Contact;
	}

	public int getNumOfPerson() {
		return NumOfPerson;
	}

	public void setNumOfPerson(int numOfPerson) {
		NumOfPerson = numOfPerson;
	}

	public String[] getOtherGuests() {
		return otherGuests;
	}

	public void setOtherGuests(String[] otherGuests) {
		this.otherGuests = otherGuests;
	}

	public GuestNode getNext() {
		return next;
	}

	public void setNext(GuestNode next) {
		this.next = next;
	}

}// end class
