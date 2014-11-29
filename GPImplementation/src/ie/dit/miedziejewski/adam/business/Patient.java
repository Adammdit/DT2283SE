package ie.dit.miedziejewski.adam.business;

public class Patient 
{
	public String firstName;
	public String lastName;
	public String address;
	public int telNo;
	
	public Patient(String first, String last, String address, int tel) {
		this.firstName = first;
		this.lastName = last;
		this.address = address;
		this.telNo = tel;
	}
}
