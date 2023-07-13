package com.ty.servletProjectApp;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/operation")

public class ServletOperations extends HttpServlet
{
	Connection con = null;
	PreparedStatement pstmt = null;
	Statement stmt = null;
	ResultSet rs = null;
	String edit = "";
	String delete = "";
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		PrintWriter out = resp.getWriter();
		try
		{	
			String fqcn = "com.mysql.jdbc.Driver";
			String url = "jdbc:mysql://localhost:3306?user=root&password=root";
			String qry2 = "select * from empinfo.emp";
			Class.forName(fqcn);
			con = DriverManager.getConnection(url);
			pstmt = con.prepareStatement(qry2);
			rs = pstmt.executeQuery();
			
				out.print("<html><head><link rel=\"stylesheet\" type=\"text/css\" href=\"table.css\"></head>"+
							"<table><caption>Employee Table</caption><tr><th id='first'>Employee_ID</th><th>Employee's_Name</th><th>Employee's_Place</th><th>Employee's_Role</th><th>Operations</th></tr>");
							while (rs.next()) 
							{
								out.print("<tr><td >"+rs.getInt(1)+"</td>"+
								"<td>"+rs.getString(2)+"</td>"+
								"<td>"+rs.getString(3)+"</td>"+
								"<td>"+rs.getString(4)+"</td>"+
								"<td> <a href='DisplayData?id="+rs.getInt(1)+"'><input type='submit' value='Edit' name='edit'></a>&nbsp;&nbsp;"
										+ "<a href='deleteServlet?id="+rs.getInt(1)+"'><input type='submit' value='Delete' name='delete'></a></td></tr>");
							}
				out.print("</table></body></html>");
		
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		finally 
		{
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
