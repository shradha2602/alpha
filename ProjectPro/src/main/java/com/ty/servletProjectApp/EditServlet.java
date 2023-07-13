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

@WebServlet("/editRec")
public class EditServlet extends HttpServlet
{
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	String name = "";
	String place = "";
	String role = "";
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
		try
		{
			int eeid = Integer.parseInt(req.getParameter("id"));
			name = req.getParameter("nm");
			place = req.getParameter("pl");
			role = req.getParameter("rl");
			String fqcn = "com.mysql.jdbc.Driver";
			String url = "jdbc:mysql://localhost:3306?user=root&password=root";
			Class.forName(fqcn);
			con = DriverManager.getConnection(url);
			
			String select = "select empname, empplace,emprole from empinfo.emp where id=?";
			pstmt = con.prepareStatement(select);
			pstmt.setInt(1, eeid);
			rs = pstmt.executeQuery();
			rs.next();
//			String empIdOrg = rs.getString("id");
			String empNameorg = rs.getString("empname");
			String empPlaceorg = rs.getString("empplace");
			String empRoleorg = rs.getString("emprole");
			
			String updateQry = "update empinfo.emp set empname=?,empplace=?,emprole=? where id=?";
			pstmt = con.prepareStatement(updateQry);
			pstmt.setString(1, name.isBlank()?empNameorg:name);
			pstmt.setString(2, place.isBlank()?empPlaceorg:place);
			pstmt.setString(3, role.isBlank()?empRoleorg:role);
			pstmt.setInt(4, eeid);
			pstmt.executeUpdate();
			System.out.println("data updated");
			RequestDispatcher rd = req.getRequestDispatcher("operation");
			rd.forward(req, resp);
			
		}
		catch (Exception e) 
		{
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
