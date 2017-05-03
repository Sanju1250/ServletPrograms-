package com.bridgeLab;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.PreparedStatement;
@WebServlet(name="Login",urlPatterns="/fs")
public class Login extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter printWriter=response.getWriter();
		String name=request.getParameter("username");
		String password=request.getParameter("password");
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		ResultSet resultSet=null;
		String name1=null;
		String password1=null;
		String query="select * from login_validation.Customer where name=? and password=?";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection=DriverManager.getConnection("jdbc:mysql://localhost:3306?user=root&password=sanju");
			preparedStatement=connection.prepareStatement(query);
			preparedStatement.setString(1, name);
			preparedStatement.setString(2, password);
			resultSet=preparedStatement.executeQuery();
			if(resultSet.next())
			{
				 name1=resultSet.getString(1);
				 password1=resultSet.getString(7);
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
		if(name.equals(name1)&&password.equals(password1))
		{
			printWriter.println("<html><head><title>login</title></head><body bgcolor='EE82EE'><h1>Dear "+name+" you are successfully logined... </h1></body></html>");
		}
		else
		{
			response.sendRedirect("ServletLogin.html");
		}

	}

}
