package com.bridgeLab;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.ProcessBuilder.Redirect;
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
import java.sql.Connection;;
public class FetchDataFromDatabase extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter printWriter=response.getWriter();
		 String password=request.getParameter("password");
		 String name=request.getParameter("username");
		Connection connection=null;
		PreparedStatement preparedStatement=null;
	    String name1 = null;
		ResultSet resultSet=null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection=DriverManager.getConnection("jdbc:mysql://localhost:3306?user=root&password=sanju");
			preparedStatement=connection.prepareStatement("select * from login_validation.JDBCLogin where password=?");
			preparedStatement.setString(1, password);
			resultSet=preparedStatement.executeQuery();
			if(resultSet.next())
			{
			 name1=resultSet.getString(1);
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		finally
		{
			if(resultSet!=null)
			{
				try {
					resultSet.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(preparedStatement!=null)
			{
				try {
					preparedStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(connection!=null)
			{
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		if(name.equals(name1))
		{
			printWriter.println("<html><body bgcolor='#85929e'><center><br><br><h3>Dear "+name+" you have successfully Logined </h3></center></body></html>");

		}
		else
		{
			response.sendRedirect("Reg.html");
		}
		
		
	}
	
	
	
	
}
	

