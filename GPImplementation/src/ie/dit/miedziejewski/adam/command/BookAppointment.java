package ie.dit.miedziejewski.adam.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import ie.dit.miedziejewski.adam.business.Patient;
import ie.dit.miedziejewski.adam.service.Gp;

public class BookAppointment implements Command
{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) 
	{
		Gp gp = new Gp();
		String forwardToJsp = "";	

		String firstName = request.getParameter("first");
		String lastName = request.getParameter("last");

		//Check we have a username and password...
		if (firstName != null && lastName != null){
			
			//Make call to the 'Model' using the Gp class to book appointment
			//String newDateTime = gp.bookAppointment(firstName, lastName);
			//Make call to the 'Model' using the UserServive class to login...

			//if (newDateTime != null){
			{

				//If login successful, store the session id for this client...
				HttpSession session = request.getSession();
				String clientSessionId = session.getId();
				session.setAttribute("loggedSessionId", clientSessionId);

				//session.setAttribute("newDateTime", newDateTime);

				forwardToJsp = "/booked.jsp";				
			}
			//else{
				forwardToJsp = "/loginFailure.jsp";	
			}
		//}
		//else {
			//forwardToJsp = "/loginFailure.jsp";	
		//}
		return forwardToJsp;
	}
}
