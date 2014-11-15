<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import = "java.util.List"  %>
<%@ page import = "com.example.business.User" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h3>Hello</h3>
<!-- access session defined in ListUsersCommand -->	
<c:set var="users" value="${sessionScope.usersList}"/>
<!-- for loop for "jstl" -->
<c:forEach var="i" begin="0" end="${users.size()-1}">
   Username: <c:out value="${users.get(i).username}"/><p>
</c:forEach>

</body>
</html>