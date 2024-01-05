<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<form action="share.jsp" method="post">
		Review Id: <input type="text" name="id" value="${param.id}" readonly /><br>
		User id: <input type="text" name="userId" /> <br>
		<input type="submit" value="Share" />
	</form>
</body>
</html>