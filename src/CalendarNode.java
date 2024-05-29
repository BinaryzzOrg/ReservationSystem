public class CalendarNode {

	//@formatter:off
	private CalendarNode next;
	private CalendarNode endingDate;
	private int month;
	private int date;
	private int year;
	
	public CalendarNode(int month, int date, int year) {
		this.next = null;
		this.endingDate = null;
		this.month = month;
		this.date = date;
		this.year = year;
	}

	public CalendarNode getNext() {
		return next;
	}

	public void setNext(CalendarNode next) {
		this.next = next;
	}

	public CalendarNode getEndingDate() {
		return endingDate;
	}

	public void setEndingDate(CalendarNode endingDate) {
		this.endingDate = endingDate;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public int getDate() {
		return date;
	}

	public void setDate(int date) {
		this.date = date;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}
	//check if reserved date are already passed by the current date now
	//boolean method that returns if following properties of date is less than the current properties of date now
	public boolean compareIfEarlier(CalendarNode validateNode) {
		if (this.getMonth() < validateNode.getMonth()) {
			return false;
		} else if (this.getMonth() > validateNode.getMonth()) {
			return true;
		} else {
			if (this.getDate() < validateNode.getDate()) {
				return false;
			} else if (this.getDate() == validateNode.getDate()){
				return true;
			} else if (this.getDate() > validateNode.getDate()) {
				return true;
			} else {
				return this.year > validateNode.getYear();
				}
			
			}
		
		}
}

class DateFormat {
	private int month;
	private int day;
	private int year;
	
	public DateFormat(int month, int day, int year) {
		this.month = month;
		this.day = day;
		this.year = year;
	}

	public String toString() {
		return "Date now: " + month + "/" + day + "/" + year;
	}
	
	public int getMonth() {
		return month;
	}
	public int getDay() {
		return day;
	}
	public int getYear() {
		return year;
	}
}