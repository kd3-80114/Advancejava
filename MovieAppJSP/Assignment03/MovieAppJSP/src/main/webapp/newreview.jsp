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
	<form action="addreview.jsp" method="post">
		Movie: <input type="text" name="movieId" /><br> 
		Rating: <input type="text" name="rating" /> <br> 
		Review: <br> <textarea rows="3" cols="40" name="review"></textarea>
		<br> <input type="submit" value="Save" />
	</form>
</body>
</html>