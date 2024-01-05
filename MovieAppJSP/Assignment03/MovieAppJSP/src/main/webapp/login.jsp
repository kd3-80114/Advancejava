<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<jsp:useBean id="lb" class="com.sunbeam.beans.LoginBean"
		scope="session" />
	<jsp:setProperty property="email" name="lb" param="email" />
	<jsp:setProperty property="passwd" name="lb" param="passwd" />
	<jsp:setProperty property="status" name="lb" value="false" />
	${lb.authenticate()}
	<c:choose>
		<c:when test="${lb.status == true}">
			<c:redirect url="reviews.jsp" />
		</c:when>
		<c:otherwise>
			Invalid Credentials<br />
			<a href="index.jsp">Login Again</a>
		</c:otherwise>
	</c:choose>
</body>
</html>