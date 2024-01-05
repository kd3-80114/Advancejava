<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h1>Hello, ${l.users.first_name} ${lb.users.last_name}!</h1>
	<jsp:useBean id="drb" class="com.sunbeam.beans.DelReviewBean"
		scope="request" />
	<jsp:setProperty property="*" name="drb" />
	${drb.delReview()}
	<jsp:forward page="reviews.jsp" />
</body>
</html>