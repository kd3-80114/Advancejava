package com.sunbeam.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sunbeam.daos.UsersDao;
import com.sunbeam.pojos.Users;
import com.sunbeam.utils.DateUtil;

@WebServlet("/register")
public class AddUserServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String fname = req.getParameter("fname");
		String lname = req.getParameter("lname");
		String email = req.getParameter("email");
		String password = req.getParameter("pwd");
		String mobno = req.getParameter("mobno");
		String birthDate = req.getParameter("bdate");
		Date udate = DateUtil.parse(birthDate);
		Users user = new Users(fname, lname, email, password, mobno, udate);
		System.out.println(user.toString());

		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		out.println("<html>");
		out.println("<head>");
		out.println("<title>Register</title>");
		out.println("</head>");
		out.println("<body>");
		try (UsersDao dao = new UsersDao()) {
			int cnt = dao.save(user);
			if (cnt == 1) {
				out.println("<h2>User Added Successfully</h2>");
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
