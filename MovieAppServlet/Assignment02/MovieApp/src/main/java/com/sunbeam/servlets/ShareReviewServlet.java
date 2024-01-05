package com.sunbeam.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sunbeam.daos.ReviewsDao;
import com.sunbeam.daos.UsersDao;
import com.sunbeam.pojos.Users;

@WebServlet("/sharereview")
public class ShareReviewServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id = Integer.parseInt(req.getParameter("id"));
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		out.println("<html>");
		out.println("<head>");
		out.println("<title>Share Review</title>");
		out.println("</head>");
		out.println("<body><center>");
		out.println("<table>");
		out.println("<tbody>");

		HttpSession session = req.getSession();
		Users user = (Users) session.getAttribute("usr");
		out.printf("<h1>Hello, %s_%s!</h1>\r\n<hr/>", user.getFirst_name(), user.getLast_name());

		out.println("<form action='sharereview' method='post'>");
		out.println("<tr>");
		out.println("<td>Review Id: </td>");
		out.printf("<td><input type='text' value='%s' name='reviewId' readonly></td>", id);
		out.println("</tr>");
		out.println("<tr>");
		out.println("<td>User: </td>");
		out.println("<td><select name='userId'>");
		try (UsersDao dao = new UsersDao()) {
			List<Users> list = dao.displayAll();
			for (Users users : list) {
				out.printf("<option value='%s'>%s</option>", users.getId(),
						users.getFirst_name() + ' ' + users.getLast_name());
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServletException(e);
		}
		out.println("</select></td>");
		out.println("</tr>");

		out.println("<tr>");
		out.println("<td></td>");
		out.printf("<td><input type='submit' value='Share Review'></td>");
		out.println("</tr>");
		out.println("</form>");
		out.println("</tbody>");
		out.println("</table>");
		out.println("</center></body>");
		out.println("</html>");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int reviewId = Integer.parseInt(req.getParameter("reviewId"));
		int userId = Integer.parseInt(req.getParameter("userId"));

		try (ReviewsDao dao = new ReviewsDao()) {
			int cnt = dao.shareReviewWith(reviewId, userId);
			if (cnt == 1) {
				resp.sendRedirect("reviews");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServletException(e);
		}
	}
}
