<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<form action="registration.jsp" method="post">
		<table>
			<tbody>
				<tr>
					<td>First Name:</td>
					<td><input type="text" name="fname"></td>
				</tr>
				<tr>
					<td>Last Name:</td>
					<td><input type="text" name="lname"></td>
				</tr>
				<tr>
					<td>E-mail:</td>
					<td><input type="text" name="email"></td>
				</tr>
				<tr>
					<td>Password:</td>
					<td><input type="text" name="passwd"></td>
				</tr>
				<tr>
					<td>Mobile:</td>
					<td><input type="text" name="mobile"></td>
				</tr>
				<tr>
					<td>Birth:</td>
					<td><input type="text" name="birth"></td>
				</tr>
				<tr>
					<td></td>
					<td><input type="submit" value="Register"> <a
						href="index.jsp">Sign In</a></td>
				</tr>
			</tbody>
		</table>

	</form>
</body>
</html>