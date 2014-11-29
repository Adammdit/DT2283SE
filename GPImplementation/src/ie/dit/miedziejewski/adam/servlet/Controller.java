package ie.dit.miedziejewski.adam.servlet;

import ie.dit.miedziejewski.adam.command.Command;
import ie.dit.miedziejewski.adam.command.CommandFactory;
import ie.dit.miedziejewski.adam.service.Gp;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ie.dit.miedziejewski.adam.exceptions.CommandCreationException;
import ie.dit.miedziejewski.adam.exceptions.DaoException;

/**
 * Servlet implementation class Controller
 */
@WebServlet("/Controller")
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String LOGIN_ACTION = "BookAppointment";  
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Controller() {
        super();
        // TODO Auto-generated constructor stub
        
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Gp gp = new Gp();
		String firstName = request.getParameter("first");
		String lastName = request.getParameter("last");
		String ret = null;
		try {
			ret = gp.bookAppointment(firstName, lastName);
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(ret);
	}
	
	/**
	 * Common method to process all client requests (GET and POST)
	 */
	@SuppressWarnings("unused")
	private void processRequest(HttpServletRequest request, HttpServletResponse response) {
		
		String forwardToJsp = null;		
		String action = request.getParameter("action");
		
		
		/*
		 * NOTE: AS A SPCIAL CASE, THIS SECTION OF THE CODE DEALS WITH CHECKING LOGIN DETAILS...
		 */
		
		//Check if this is not a login request...
		if ( !action.equalsIgnoreCase(LOGIN_ACTION) ){

			//If not a login request then need to check that user is  
			//logged in before processing ANY requests.
			
			//Check to see if the session id coming from the client matches the id stored at login...
			HttpSession session = request.getSession();

			//If user not logged in...
			if ( session.getId() != session.getAttribute("loggedSessionId") ){
				forwardToJsp = "/loginFailure.jsp";
				forwardToPage(request, response, forwardToJsp);
				return;
			}
			forwardToPage(request, response, forwardToJsp);
			return;
		}			
		
		
		//Now we can process whatever the request is...
		//We just create a Command object to handle the request...
		CommandFactory factory = CommandFactory.getInstance();
		Command command = null;
		
		try {
			command = factory.createCommand(action);
			forwardToJsp = command.execute(request, response);
		} catch (CommandCreationException e) {			
			e.printStackTrace();
			forwardToJsp = "/errorPage.jsp";
		}		
		
		forwardToPage(request, response, forwardToJsp);
	}
	
	
	/**
	 * Forward to server to the supplied page
	 */
	private void forwardToPage(HttpServletRequest request, HttpServletResponse response, String page){
		
		//Get the request dispatcher object and forward the request to the appropriate JSP page...
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
		try {
			dispatcher.forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
