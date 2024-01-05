<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h1>Hello, ${lb.users.first_name} ${lb.users.last_name}</h1>
	<jsp:useBean id="srb" class="com.sunbeam.beans.ShareReviewBean" />
	<jsp:setProperty property="*" name="srb" />
	${srb.shareReview()}
	<jsp:forward page="reviews.jsp">
		<jsp:param value="${srb.msg}" name="msg" />
	</jsp:forward>
</body>
</html>