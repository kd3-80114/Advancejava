<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<jsp:useBean id="arb" class="com.sunbeam.beans.AddReviewBean"
		scope="request" />
	<jsp:setProperty property="*" name="arb" />
	<jsp:setProperty property="user" name="arb" value="${lb.users}" />
	${arb.addReview()}
	<jsp:forward page="reviews.jsp" />
</body>
</html>