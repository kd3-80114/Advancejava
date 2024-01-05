<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<jsp:useBean id="urb" class="com.sunbeam.beans.UpdateReviewBean" />
	<jsp:setProperty property="*" name="urb" />
	${urb.updateReview()}
	<jsp:forward page="reviews.jsp">
		<jsp:param value="${urb.msg}" name="msg" />
	</jsp:forward>
</body>
</html>