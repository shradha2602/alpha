package com.ty.servletProjectApp;

import java.io.IOException;
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

@WebServlet("/deleteServlet")
public class DeleteServlet extends HttpServlet
{

	Connection con = null;
	PreparedStatement pstmt = null;

	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
		try
		{
			
			String fqcn = "com.mysql.jdbc.Driver";
			String url = "jdbc:mysql://localhost:3306?user=root&password=root";
			
			Class.forName(fqcn);
			con = DriverManager.getConnection(url);
			
			int eeid = Integer.parseInt(req.getParameter("id"));
			String delqry = "delete from empinfo.emp where id=?";
			pstmt = con.prepareStatement(delqry);
			pstmt.setInt(1, eeid);
			pstmt.executeUpdate();
			System.out.println("deleted");
		
			RequestDispatcher rd = req.getRequestDispatcher("operation");
			rd.forward(req, resp);
			
		
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally 
		{
			
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
