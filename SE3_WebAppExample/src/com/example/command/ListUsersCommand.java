package com.example.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.example.business.User;
import com.example.service.UserService;

public class ListUsersCommand implements Command
{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) 
	{
		UserService userService = new UserService();
		String forwardToJsp = "";	
		
		//Make call to the 'Model' using the UserServive class to login...
		List<User> userList = userService.listUsers();
		
		HttpSession session = request.getSession();
		String clientSessionId = session.getId();
		session.setAttribute("loggedSessionId", clientSessionId);

		session.setAttribute("usersList", userList);
		forwardToJsp = "/usersList.jsp";
		return forwardToJsp;
	}

}
