<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h1>Hello, ${lb.users.first_name} ${lb.users.last_name}!</h1>
	<hr>
	<jsp:useBean id="frb" class="com.sunbeam.beans.FindReviewBean" />
	<jsp:setProperty property="*" name="frb"/>
	${frb.fetchReview()}
	<br />
	<form action="updatereview.jsp" method="post">
		<input type="hidden" name="id" value="${frb.reviews.id}" /> 
		Movie: <input type="text" name="movieId" value="${frb.reviews.movie_id}" /><br>
		Rating: <input type="text" name="rating" value="${frb.reviews.rating}" />
		<br> Review: <br>
		<textarea rows="3" cols="40" name="review">${frb.reviews.review}</textarea>
		<br> <input type="submit" value="Save" />
	</form>
</body>
</html>