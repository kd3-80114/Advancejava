package com.sunbeam.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.jasper.tagplugins.jstl.core.ForEach;

import com.sunbeam.daos.MoviesDao;
import com.sunbeam.daos.ReviewsDao;
import com.sunbeam.pojos.Movies;
import com.sunbeam.pojos.Reviews;
import com.sunbeam.pojos.Users;

@WebServlet("/reviews")
public class ReviewsServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String type = req.getParameter("type");
		if (type == null)
			type = "all";
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		out.println("<html>");
		out.println("<head>");
		out.println("<title>Reviews</title>");
		out.println("</head>");
		out.println("<body><center>");

		HttpSession session = req.getSession();
		Users user = (Users) session.getAttribute("usr");

		String uname = "User";
		Cookie[] arr = req.getCookies();
		for (Cookie c : arr) {
			if (c.getName().equals("uname")) {
				uname = c.getValue();
				break;
			}
		}
		out.printf("<h1>Hello, %s!</h1>\r\n<hr/>", uname);

		out.println("<table border='1'>");
		out.println("<tr>");
		out.println("<td><a href='reviews'>All Reviews</a></td>");
		out.println("<td><a href='reviews?type=my'>My Reviews</a></td>");
		out.println("<td><a href='reviews?type=shared'>Shared Reviews</a></td>");
		out.println("</tr>");
		out.println("</table>");
		if (type.equals("my") || type.equals("all")) {
			try (ReviewsDao dao = new ReviewsDao()) {
				List<Reviews> list = null;
				if (type.equals("my")) {
					list = dao.displayReviews(user);
					out.println("<h2>My Reviews<h2>");
				} else if (type.equals("all")) {
					list = dao.displayallReviews();
					out.println("<h2>All Reviews<h2>");
				}
				out.println("<table border = '1'>");
				out.println("<thead>");
				out.println("<th>Id</th>");
				out.println("<th>Movie</th>");
				out.println("<th>Rating</th>");
				out.println("<th>Review</th>");
				out.println("<th>Action</th>");
				out.println("</thead>");
				out.println("<tbody>");
				for (Reviews r : list) {
					out.println("<tr>");
					out.printf("<td>%s</td>", r.getId());
					String mName = "";
					try (MoviesDao mdao = new MoviesDao()) {
						List<Movies> lt = mdao.displayMovies();
						for (Movies movies : lt) {
							if (movies.getId() == r.getMovie_id()) {
								mName = movies.getTitle();
								break;
							}
						}
					}
					out.printf("<td>%s</td>", mName);
					out.printf("<td>%s</td>", r.getRating());
					out.printf("<td>%s</td>", r.getReview());
					if (user.getId() == r.getUsers_id()) {
						out.printf(
								"<td><a href='updatereview?id=%s'><img src='edit.jpg' alt='Update Review' width='24' height='24'/></a> | <a href='delreview?id=%s'><img src='delete.jpg' alt='Delete Review' height='24' width='24'/></a> | <a href='sharereview?id=%s'><img src='share.png' alt='Share Review' height='24' width='24'/></a></td>",
								r.getId(), r.getId(), r.getId());
					} else {
						out.println("<td></td>");
					}
					out.println("</tr>");
				}
				out.println("</tbody>");
				out.println("</table>");

			} catch (Exception e) {
				e.printStackTrace();
				throw new ServletException(e);
			}
		} else {
			try (ReviewsDao dao = new ReviewsDao()) {
				List<Integer> list = dao.sharedReview(user);
				out.println("<h2>Shared Reviews<h2>");
				out.println("<table border = '1'>");
				out.println("<thead>");
				out.println("<th>Id</th>");
				out.println("<th>Movie</th>");
				out.println("<th>Rating</th>");
				out.println("<th>Review</th>");
				out.println("<th>Action</th>");
				out.println("</thead>");
				out.println("<tbody>");
				for (Integer id : list) {
					Reviews r = dao.displayWithId(id);
					out.println("<tr>");
					out.printf("<td>%s</td>", r.getId());
					String mName = "";
					try (MoviesDao mdao = new MoviesDao()) {
						List<Movies> lt = mdao.displayMovies();
						for (Movies movies : lt) {
							if (movies.getId() == r.getMovie_id()) {
								mName = movies.getTitle();
								break;
							}
						}
					}
					out.printf("<td>%s</td>", mName);
					out.printf("<td>%s</td>", r.getRating());
					out.printf("<td>%s</td>", r.getReview());
					if (user.getId() == r.getUsers_id()) {
						out.printf(
								"<td><a href='updatereview?id=%s'><img src='edit.jpg' alt='Update Review' width='24' height='24'/></a> | <a href='delreview?id=%s'><img src='delete.jpg' alt='Delete Review' height='24' width='24'/></a> | <a href='sharereview'><img src='share.png' alt='Share Review' height='24' width='24'/></a></td>",
								r.getId(), r.getId(), r.getId());
					} else {
						out.println("<td></td>");
					}
					out.println("</tr>");
				}
				out.println("</tbody>");
				out.println("</table>");

			} catch (Exception e) {
				e.printStackTrace();
				throw new ServletException(e);
			}
		}
		out.printf("<a href='addreview'>Add Review</a><br/>");
		out.printf("<a href='logout'>SignOut</a>");
		out.println("</center></body>");
		out.println("</html>");
	}
}
