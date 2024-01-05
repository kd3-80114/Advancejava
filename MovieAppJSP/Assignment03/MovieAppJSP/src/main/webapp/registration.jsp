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
	<jsp:useBean id="rb" class="com.sunbeam.beans.RegisterBean"></jsp:useBean>
	<jsp:setProperty property="*" name="rb" />
	<jsp:setProperty property="status" name="rb" value="false" />
	${rb.registration()}
	<c:choose>
		<c:when test="${rb.status == true}">
			Congratulations!!!<br />User Added Successfully!<br />
			<a href="index.jsp">Log In</a>
		</c:when>
		<c:otherwise>
			Some Error Occured ....:(<br />
			<a href="register.jsp">SignUp Again</a>
			<br />
			<a href="index.jsp">LogIn</a>
		</c:otherwise>
	</c:choose>
</body>
</html>