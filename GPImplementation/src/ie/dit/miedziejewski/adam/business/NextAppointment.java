package ie.dit.miedziejewski.adam.business;

public class NextAppointment extends Patient
{
	public String dateTime;
	public boolean booked;
	
	public NextAppointment(String first, String last, String address, int tel) {
		super(first, last,  address, tel);
	}
	
	public String getDateTime() {
		return dateTime;
	}
	
	public boolean cancelAppointment() {
		return false;
	}
	
	public boolean isBooked() {
		if (booked) {
			return true;
		}
		else {
			return false;
		}	
	}
	
	public void updateAppointment(String dateT, boolean book) {
		this.dateTime = dateT;
		this.booked = book;
	}
}
