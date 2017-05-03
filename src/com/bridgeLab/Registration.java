package com.bridgeLab;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class Registration   extends HttpServlet{
	

		
		protected void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
			String name = request.getParameter("name");
			String password = request.getParameter("password");
			Connection connection = null;
			PreparedStatement preparedStatement = null;
			PrintWriter printWriter = response.getWriter();
			String query = "insert into JDBCLogin values(?,?)";
			try {
				Class.forName("com.mysql.jdbc.Driver");
				connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/login_validation?user=root&password=sanju");
				preparedStatement = connection.prepareStatement(query);
				preparedStatement.setString(1, name);
				preparedStatement.setString(2, password);
				
			int n=preparedStatement.executeUpdate();
			if(n==1)
			{
				System.out.println("succesfull");
				response.sendRedirect("login.html");
			}
			else
			{
				response.sendRedirect("Reg.html");
			}
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			} finally {
				if (preparedStatement != null) {
					try {
						preparedStatement.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				if (connection != null) {
					try {
						connection.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}
			printWriter.close();

		}

	}



