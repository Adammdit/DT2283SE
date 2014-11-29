package ie.dit.miedziejewski.adam.business;

public class NextAppointment extends Patient
{
	public String dateTime;
	public boolean booked;
	
	public NextAppointment(String first, String last, String address, int tel) {
		super(first, last,  address, tel);
	}
	
	public String getDateTime() {
		// begin-user-code
		// TODO Auto-generated method stub
		return dateTime;
		// end-user-code
	}
	
	public boolean cancelAppointment() {
		// begin-user-code
		// TODO Auto-generated method stub
		return false;
		// end-user-code
	}
	
	public boolean isBooked() {
		// begin-user-code
		// TODO Auto-generated method stub
		return false;
		// end-user-code
	}
	
	public String updateAppointment(String dateTime, boolean booked) {
		// begin-user-code
		// TODO Auto-generated method stub
		return null;
		// end-user-code
	}
}
