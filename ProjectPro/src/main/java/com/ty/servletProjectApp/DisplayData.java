package com.ty.servletProjectApp;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
@WebServlet("/DisplayData")

public class DisplayData extends HttpServlet
{
	Connection con = null;
	PreparedStatement pstmt = null;
	Statement stmt = null;
	ResultSet rs = null;
	
	int eid = 0;
	String name = "";
	String place = "";
	String role = "";
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
		PrintWriter out = resp.getWriter();
		try 
		{
			String fqcn = "com.mysql.jdbc.Driver";
			String url = "jdbc:mysql://localhost:3306?user=root&password=root";
			Class.forName(fqcn);
			con = DriverManager.getConnection(url);
			int eeid = Integer.parseInt(req.getParameter("id"));
			String display = "select * from empinfo.emp where id =?";
			pstmt = con.prepareStatement(display);
			pstmt.setInt(1, eeid);
			rs = pstmt.executeQuery();
		while (rs.next())
		{
			
			out.print("<html><head><link rel=\"stylesheet\" type=\"text/css\" href=\"emp.css\"></head><body> <div id=\"div1\">\r\n"
					+ "			<form action=\"editRec\" method=\"post\" onsubmit=\"\">\r\n"
					+ "			    <h1>Employee's Details</h1>\r\n"
					+ "				<label>Employee ID :  </label><br>\r\n"
					+ "				<input type=\"number\" placeholder=\"Enter employee ID\" name=\"id\" value='"+rs.getInt(1)+"'><br>\r\n"
					+ "				<label>Employee's Name : </label><br>\r\n"
					+ "				<input type=\"text\" placeholder=\"Enter employee's Name\" name=\"nm\" value='"+rs.getString(2)+"'><br>\r\n"
					+ "				<label>Employee's Place : </label><br>\r\n"
					+ "				<input type=\"text\" placeholder=\"Enter employee's location\" name=\"pl\" value='"+rs.getString(3)+"'><br>\r\n"
					+ "				<label>Employee's Role : </label><br>\r\n"
					+ "				<input type=\"text\" placeholder=\"Enter employee's designation\" name=\"rl\" value='"+rs.getString(4)+"'><br>\r\n"
					+ "				\r\n"
					+ "				<button type=\"submit\">Click Me</button>\r\n"
					+ "			</form>\r\n"
					+ "		</div>"
					+ "</body></html>");
			
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			if (rs != null) 
			{
				try 
				{
					rs.close();
				} 
				catch (SQLException e) 
				{
					e.printStackTrace();
				}
			}
			
			if (pstmt != null) 
			{
				try 
				{
					pstmt.close();
				} 
				catch (SQLException e) 
				{
					e.printStackTrace();
				}
			}
			
			if (con != null) 
			{
				try 
				{
					con.close();
				} 
				catch (SQLException e)
				{
					e.printStackTrace();
				}
			}
		}
	}
}
