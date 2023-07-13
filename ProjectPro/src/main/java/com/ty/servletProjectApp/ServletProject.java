package com.ty.servletProjectApp;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/database")


public class ServletProject extends HttpServlet
{
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String name = "";
		String place = "";
		String role = "";
		
		PrintWriter out = resp.getWriter();
		
		try 
		{
			 int id = 0;
				
			 String fqcn = "com.mysql.jdbc.Driver";
			 String url = "jdbc:mysql://localhost:3306?user=root&password=root";
			 id = Integer.parseInt(req.getParameter("id"));
			 
			 Class.forName(fqcn);
			 con = DriverManager.getConnection(url);
			 name = req.getParameter("nm");
			 place = req.getParameter("pl");
			 role = req.getParameter("rl"); 
			 
			 if(name.isEmpty())
			 {
				 out.print("<html><body><p>Employee name cannot be empty</p></body></html>");
				 return;
			 }
			 
			 String check = "select * from empinfo.emp where id=?";
			 pstmt = con.prepareStatement(check);
			 pstmt.setInt(1, id);
			 rs = pstmt.executeQuery();
			 if(rs.next())
			 {
				 out.print("<html><body style ='background-image: linear-gradient(to right, #870000, #190a05); color:white;'><p>Employee ID already present</p></body></html>");
				 System.out.println("Employee ID already present");
			 }
			 else
			 {
			    String qry = "insert into empinfo.emp values(?,?,?,?)";
				pstmt = con.prepareStatement(qry);
				pstmt.setInt(1, id);
				pstmt.setString(2, name);
				pstmt.setString(3, place);
				pstmt.setString(4, role);
				int x = pstmt.executeUpdate();
			    System.out.println("data inserted");
			    if (x == 1) {
			    	RequestDispatcher rd = req.getRequestDispatcher("operation");
				    rd.forward(req, resp);
				}
			    
			 }
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

