package com.sunbeam.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sunbeam.daos.UsersDao;
import com.sunbeam.pojos.Users;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String email = req.getParameter("email");
		String password = req.getParameter("pwd");

		try (UsersDao dao = new UsersDao()) {
			Users user = dao.findByEmail(email);
			if (user != null && user.getPassword().equals(password)) {

				HttpSession session = req.getSession();
				session.setAttribute("usr", user);

				String userName = user.getFirst_name() + "_" + user.getLast_name();
				Cookie c = new Cookie("uname", userName);
				c.setMaxAge(3600);
				resp.addCookie(c);
				System.out.println(userName);

				resp.sendRedirect("reviews");
			} else {
				resp.setContentType("text/html");
				PrintWriter out = resp.getWriter();
				out.println("<html>");
				out.println("<head>");
				out.println("<title>LogIn</title>");
				out.println("</head>");
				out.println("<body>");
				out.println("<h1>Invalid Credentials Provided</h1>");
				out.println("<a href='index.html'>Log In</a>");
				out.println("</body>");
				out.println("</html>");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServletException(e);
		}
	}
}
