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
}