package ie.dit.miedziejewski.adam.business;

import java.util.List;

public class Patient 
{
	public String firstName;
	public String lastName;
	public List<NextAppointment> historyOfVisits;
	public NextAppointment nextAppointment;
	public String address;
	public int telNo;
	//public String dateTime = null;
	//private boolean booked = false;
	
	public Patient(String first, String last, String address, int tel) {
		this.firstName = first;
		this.lastName = last;
		this.address = address;
		this.telNo = tel;
	}
}
