package ie.dit.miedziejewski.adam.service;

import ie.dit.miedziejewski.adam.business.NextAppointment;
import ie.dit.miedziejewski.adam.business.Patient;
import ie.dit.miedziejewski.adam.dao.DAOImplementation;
import ie.dit.miedziejewski.adam.exceptions.DaoException;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Gp 
{
	private DAOImplementation dao = new DAOImplementation();
	
	public boolean registerPatient(String first, String last, String address, int tel) {
		
		return true;
	}

	public String bookAppointment(String first, String last) throws DaoException {
		NextAppointment p = null;
		String date = null;
		try {			
			p = dao.findPatient(first, last);
			if (p == null) {
				return "Patient not registered!!!";
			}
		} 
		catch (DaoException e) {
			e.printStackTrace();
		}
		
		// isBooked
		if (!p.isBooked()) {
			date = dao.findDateTimeAvailable();
			date = addToDateTime(date, "MINUTES");
			// updateAppointment
			p.updateAppointment(date, true);
			// fileAppointment
			fileAppointment(p);
			// updatePatient
			dao.updatePatient(p);			
		}
		else {
			return "Patient have booked appointment already!!!";
		}
		return p.firstName + " " + p.lastName + ": " + date;
	}
	
	public static String addToDateTime(String timestampIn, String increment) {

	    // Decompose timestamp.
	    int year = Integer.parseInt(timestampIn.substring(0, 4));
	    int month = Integer.parseInt(timestampIn.substring(5, 7));
	    int day = Integer.parseInt(timestampIn.substring(8, 10));
	    int hours = Integer.parseInt(timestampIn.substring(11, 13));
	    int mins = Integer.parseInt(timestampIn.substring(14, 16));
	    int secs = Integer.parseInt(timestampIn.substring(17, 19));

	    Calendar calendar = new GregorianCalendar(year, month - 1, day, hours, mins, secs);

	    // Increment timestamp.
	    if (increment.equals("HOURLY")) {
	        calendar.add(Calendar.HOUR, 1);
	    }
	    else if (increment.equals("DAILY")) {
	        calendar.add(Calendar.HOUR, 24);
	    }
	    else  if (increment.equals("WEEKLY")) {
	        calendar.add(Calendar.HOUR, 168);
	    }
	    else  if (increment.equals("MINUTES")) {
	        calendar.add(Calendar.MINUTE, 30);
	    }
	    else if (increment.equals("DO NOT POLL")) {

	        // Do nothing.
	    }

	    // Compose new timestamp.
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    String timestampOut = sdf.format(calendar.getTime());
	    return timestampOut;
	}
	
	public boolean cancelAppointment(String first, String last) {
		return false;
	}

	public void fileAppointment(NextAppointment nextAppointment) throws DaoException {
		dao.fileAppointment(nextAppointment);
	}

	public Patient listAppointmentsForDate(String date) {
		return null;
	}

	public String rescheduleAppointment(String name) {
		return null;
	}
}
