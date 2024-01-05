package com.sunbeam.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sunbeam.daos.MoviesDao;
import com.sunbeam.daos.ReviewsDao;
import com.sunbeam.pojos.Movies;
import com.sunbeam.pojos.Reviews;

@WebServlet("/updatereview")
public class EditReviewServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id = Integer.parseInt(req.getParameter("id"));
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		out.println("<html>");
		out.println("<head>");
		out.println("<title>Edit Review</title>");
		out.println("</head>");
		out.println("<body><center>");
		out.println("<table>");
		out.println("<tbody>");

		Reviews review = null;
		try (ReviewsDao dao = new ReviewsDao()) {
			review = dao.displayWithId(id);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServletException(e);
		}
		out.println("<form action='updatereview' method='post'>");
		out.println("<tr>");
		out.println("<td>Id</td>");
		out.printf("<td><input type='text' value='%s' name='id' readonly/></td>", review.getId());
		out.println("</tr>");

		out.println("<tr>");
		out.println("<td>Movie</td>");
		out.println("<td><select name='movie_id'>");
		try(MoviesDao dao = new MoviesDao()){
			List<Movies> list = dao.displayMovies();
			for (Movies movies : list) {
				out.printf("<option value='%s'>%s</option>", movies.getId(), movies.getTitle());
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServletException(e);
		}
		out.println("</select></td>");
		out.println("</tr>");
		
		out.println("<tr>");
		out.println("<td>Rating</td>");
		out.printf("<td><input type='number' value='%s' name='rating' max='5' min='1'/></td>", review.getRating());
		out.println("</tr>");
		out.println("<tr>");
		out.println("<td>User Id</td>");
		out.printf("<td><input type='text' value='%s' name='user_id' readonly/></td>", review.getUsers_id());
		out.println("</tr>");
		out.println("<tr>");
		out.println("<td>Review</td>");
		out.printf("<td><textarea name='review' rows='3' cols='40'>%s</textarea></td>", review.getReview());
		out.println("</tr>");
		out.println("<tr>");
		out.println("<td></td>");
		out.println("<td><input type='submit' value='Update'/></td>");
		out.println("</tr>");
		out.println("</form>");
		out.println("</tbody>");
		out.println("</table>");
		out.println("</center></body>");
		out.println("</html>");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id = Integer.parseInt(req.getParameter("id"));
		int movie_id = Integer.parseInt(req.getParameter("movie_id"));
		int rating = Integer.parseInt(req.getParameter("rating"));
		int user_id = Integer.parseInt(req.getParameter("user_id"));
		String review = req.getParameter("review");
		Reviews reviews = new Reviews(id, movie_id, rating, review, user_id, null);
		System.out.println(reviews.toString());
		try (ReviewsDao dao = new ReviewsDao()) {
			int cnt = dao.update(reviews);
			if (cnt == 1) {
				resp.sendRedirect("reviews");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServletException(e);
		}
	}
}
