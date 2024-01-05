package com.sunbeam.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sunbeam.daos.MoviesDao;
import com.sunbeam.daos.ReviewsDao;
import com.sunbeam.pojos.Movies;
import com.sunbeam.pojos.Users;

@WebServlet("/addreview")
public class AddReviewServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		out.println("<html>");
		out.println("<head>");
		out.println("<title>Add Review</title>");
		out.println("</head>");
		out.println("<body><center>");

		String uname = "User";
		Cookie[] arr = req.getCookies();
		for (Cookie c : arr) {
			if (c.getName().equals("uname")) {
				uname = c.getValue();
				break;
			}
		}
		out.printf("<h1>Hello, %s!</h1>\r\n<hr/>", uname);

		out.println("<form action='addreview' method='post'>");

		out.println("Movie: ");
		out.println("<select name='movie_id'>");
		try(MoviesDao dao = new MoviesDao()){
			List<Movies> list = dao.displayMovies();
			for (Movies movies : list) {
				out.printf("<option value='%s'>%s</option>", movies.getId(), movies.getTitle());
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServletException(e);
		}
		out.println("</select><br/>");

		out.println("Rating: <input type='text' name='rating'><br/>");
		out.println("Review: <br/><textarea name='msg' rows='3' cols='40'></textarea><br/>");
		out.println("<input type='submit' value='Save'/>");
		out.println("</form>");

		out.println("</center></body>");
		out.println("</html>");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int movie_id = Integer.parseInt(req.getParameter("movie_id"));
		int rating = Integer.parseInt(req.getParameter("rating"));
		String review = req.getParameter("msg");

		HttpSession session = req.getSession();
		Users user = (Users) session.getAttribute("usr");

		try (ReviewsDao dao = new ReviewsDao()) {
			int cnt = dao.addReview(movie_id, review, rating, user);
			if (cnt == 1) {
				RequestDispatcher rd = req.getRequestDispatcher("reviews");
				rd.forward(req, resp);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServletException(e);
		}
	}
}
