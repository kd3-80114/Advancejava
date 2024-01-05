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
	<h1>Hello, ${lb.users.first_name} ${lb.users.last_name}!</h1>
	<hr />
	<jsp:useBean id="revb" class="com.sunbeam.beans.ReviewsBean"></jsp:useBean>
	<jsp:setProperty property="users" name="revb" value="${lb.users}" />
	<jsp:setProperty property="type" name="revb" param="type" />
	<table border="1">
		<tr>
			<td><a href="reviews.jsp?type=all">All Reviews</a></td>
			<td><a href="reviews.jsp?type=my">My Reviews</a></td>
			<td><a href="reviews.jsp?type=shared">Shared Reviews</a></td>
		</tr>
	</table>
	${revb.fetchReviews()}
	<br />
	<h3>${revb.msg}</h3>
	<table border="1">
		<thead>
			<tr>
				<th>Review Id</th>
				<th>Movie</th>
				<th>Rating</th>
				<th>Review</th>
				<th>Action</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="review" items="${revb.list}">
				<tr>
					<td>${review.id}</td>
					<td>${review.movie_id}</td>
					<td>${review.rating}</td>
					<td>${review.review}</td>
					<c:if test="${review.users_id == lb.users.id}">
						<td><a href="editreview.jsp?id=${review.id}"><img
								alt="Update Review" src="edit.jpg" width="24" height="24" /></a> |
							<a href="delreview.jsp?id=${review.id}"><img
								alt="Delete Review" src="delete.jpg" width="24" height="24" /></a>
							| <a href="sharereview.jsp?id=${review.id}"><img
								alt="Share Review" src="share.png" width="24" height="24" /></a></td>
					</c:if>
					<c:if test="${review.users_id != lb.users.id}">
						<td></td>
					</c:if>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<h3>${arb.msg}${drb.msg}${param.msg}</h3>
	<h4>
		<a href="logout.jsp">Sign Out</a>
	</h4>
	<h4>
		<a href="newreview.jsp">Add Review</a>
	</h4>
</body>
</html>